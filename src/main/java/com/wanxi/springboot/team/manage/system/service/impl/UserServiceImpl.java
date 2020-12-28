package com.wanxi.springboot.team.manage.system.service.impl;

import com.wanxi.springboot.team.manage.system.api.CommonResult;
import com.wanxi.springboot.team.manage.system.esModel.EsLog;
import com.wanxi.springboot.team.manage.system.esModel.EsUser;
import com.wanxi.springboot.team.manage.system.model.*;
import com.wanxi.springboot.team.manage.system.mapper.UserMapper;
import com.wanxi.springboot.team.manage.system.repository.LogRepository;
import com.wanxi.springboot.team.manage.system.repository.UserRepository;
import com.wanxi.springboot.team.manage.system.service.LogService;
import com.wanxi.springboot.team.manage.system.service.UserRoleService;
import com.wanxi.springboot.team.manage.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanxi.springboot.team.manage.system.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
@Service
@CacheConfig(cacheNames = "teamManageSystem::user")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired(required = false)
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    String tokenHead;
    @Autowired
    RedisServiceImpl redisService;
    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LogService logService;
    @Autowired
    LogRepository logRepository;
    @Autowired
    UserRoleService userRoleService;

    @Override
    public CommonResult getUserByName(String username) {
        User user = null;
        if (!redisService.hasKey("teamManageSystem::user::" + username))
            user = userMapper.getUserByName(username);
        else
            user = (User) redisService.get("teamManageSystem::user::" + username);
        return CommonResult.success(user);
    }

    @Override
    public List<Permission> getPermissionsByUserId(Integer id) {
        return userMapper.getPermissionsByUserId(id);
    }

    @Override
    public CommonResult register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnable("yes");
        user.setStatus("normal");
        int result = userMapper.register(user);
        if (result > 0) {
            if (user.getRole() != null) {
                UserRole userRole = new UserRole();
                userRole.setRole(user.getRole()).setUser(user.getId());
                userRoleService.save(userRole);
            }
            redisService.set("teamManageSystem::user::" + user.getCode(), user);
            redisService.del("teamManageSystem::user::getUsers");
            return CommonResult.success(user, "注册成功！");
        }
        return CommonResult.failed("注册失败");
    }

    @Override
    public CommonResult login(User user) {
        String token = null;
        HashMap<String, String> data = new HashMap<>();
        User usr = null;
        //如果没有该用户冻结标识,则允许进行登录操作
        if (!redisService.hasKey("teamManageSystem::user::" + user.getCode() + "::frozen")) {
            try {
                Assert.notNull(user.getCode(), "账号不能为空");
                Assert.notNull(user.getPassword(), "密码不能为空");
                //判断Redis是否有该用户信息
                if (!redisService.hasKey("teamManageSystem::user::" + user.getCode()))
                    usr = (User) getUserByName(user.getCode()).getData();
                else
                    usr = (User) redisService.get("teamManageSystem::user::" + user.getCode());
                boolean matches = passwordEncoder.matches(user.getPassword(), usr.getPassword());
                if (matches) {
                    token = jwtTokenUtil.generateToken(usr);
                } else
                    token = null;
            } catch (Exception e) {
                e.printStackTrace();
                return CommonResult.validateFailed("用户名或密码错误");
            }
            if (!StringUtils.hasLength(token)) {
                return recordErrorToRedis(user.getCode());
            }
//            打印登录日志
            logging(user.getCode());
            //将token传入前台
            data.put("tokenHead", tokenHead);
            data.put("access_token", token);
            //清除用户的密码错误次数记录
            clearRedisErrorCode(user.getCode());
            //将用户token存入缓存
            redisService.set("teamManageSystem::user::" + user.getCode(), usr, 86400);
            return CommonResult.success(data, "登录成功！");
        } else//如果有该用户冻结标识，则直接反馈冻结信息给用户
            return CommonResult.validateFailed("该账号已被冻结，请12小时后再试");
    }

    @Override
//    @Cacheable(keyGenerator = "keyGenerator")
    public CommonResult getUsers(Map map) {
        int page = (Integer.parseInt((String) map.get("page")) - 1) * Integer.parseInt((String) map.get("limit"));
        map.put("page", page);
        map.put("limit", Integer.parseInt((String) map.get("limit")));
        List<User> users = userMapper.getUsers(map).stream().map(u -> {
            if (redisService.hasKey("teamManageSystem::user::" + u.getCode() + "::frozen"))
                u.setStatus("<input type='checkbox' value='frozen' id='status' name='status' lay-skin='switch' lay-text='正常|冻结'>");
            else
                u.setStatus("<input type='checkbox' value='normal' id='status' name='status' lay-skin='switch' lay-text='正常|冻结' checked>");
//            File file = new File("E:/IdeaWorksplace/team_manage_system/src/main/resources/static" + u.getIcon());
//            if (!file.exists())
//                u.setIcon("http://t.cn/RCzsdCq");
            if (u.getIcon() != null && u.getIcon() != "") {
                //判断是否是base64
                int index = u.getIcon().indexOf(",");
                if (index == -1)
                    u.setIcon("http://t.cn/RCzsdCq");
                else {
                    String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
                    boolean match = Pattern.matches(base64Pattern, u.getIcon().substring(index + 1));
                    if (!match)
                        u.setIcon("http://t.cn/RCzsdCq");
                }
            } else
                u.setIcon("http://t.cn/RCzsdCq");
            return u;
        }).collect(Collectors.toList());
        long count = userMapper.getUserCount(map);
        return CommonResult.success(users, count);
    }

    @Override
    public CommonResult deleteUser(Integer id) {
        int result = userMapper.deleteUser(id);
        if (result > 0)
            return CommonResult.success(result, "删除成功");
        return CommonResult.failed("删除失败");
    }

    @Override
    public CommonResult updateUser(User user) {
        if (user.getPassword() != "") {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setEnable("yes");
        user.setStatus("normal");
        int result = userMapper.updateUser(user);
        if (result > 0) {
            if (user.getRole() != null) {
                UserRole userRole = new UserRole();
                userRole.setRole(user.getRole()).setUser(user.getId());
                userRoleService.updateUserRole(userRole);
            }
            redisService.set("teamManageSystem::user::" + user.getCode(), user);
            redisService.del("teamManageSystem::user::getUsers");
            return CommonResult.success(user, "修改成功！");
        }
        return CommonResult.failed("修改失败");
    }

    @Override
    public CommonResult getCodeList() {
        List<Code> codeList = userMapper.getCodeList();
        Map data = new HashMap();
        List<Code> sex = new ArrayList<>();
        List<Code> graduate = new ArrayList<>();
        List<Code> enable = new ArrayList<>();
        List<Code> userStatus = new ArrayList<>();
        List<Code> permissionType = new ArrayList<>();
        List<Code> signStatus = new ArrayList<>();
        List<Code> dailySummaryEditStatus = new ArrayList<>();
        List<Code> dailySummaryReadStatus = new ArrayList<>();
        for (Code code : codeList) {
            if (code.getType().equals("sex"))
                sex.add(code);
            else if (code.getType().equals("graduate"))
                graduate.add(code);
            else if (code.getType().equals("enable"))
                enable.add(code);
            else if (code.getType().equals("user_status"))
                userStatus.add(code);
            else if (code.getType().equals("permission_type"))
                permissionType.add(code);
            else if (code.getType().equals("sign_status"))
                signStatus.add(code);
            else if (code.getType().equals("daily_summary_edit_status"))
                dailySummaryEditStatus.add(code);
            else if (code.getType().equals("daily_summary_read_status"))
                dailySummaryReadStatus.add(code);
        }
        data.put("sex",sex);
        data.put("graduate",graduate);
        data.put("enable",enable);
        data.put("user_status",userStatus);
        data.put("permission_type",permissionType);
        data.put("sign_status",signStatus);
        data.put("daily_summary_edit_status",sex);
        data.put("daily_summary_read_status",dailySummaryReadStatus);
        return CommonResult.success(data);
    }

    public CommonResult recordErrorToRedis(String username) {
        //判断用户是否有输入错误次数
        if (redisService.hasKey("teamManageSystem::user::" + username + "::errorCount")) {
            //如果有，则获取输入错误的次数
            int count = (int) redisService.get("teamManageSystem::user::" + username + "::errorCount");
            count++;//将输入错误次数加1
            if (count >= 3) {//如果加1后的值大于或等于3，则生成一个冻结标识到Redis，并设定失效时间为12小时
                redisService.set("teamManageSystem::user::" + username + "::frozen", true, 43200);
                redisService.expire("teamManageSystem::user::" + username + "::frozen", 43200);
                redisService.del("teamManageSystem::user::" + username + "::errorCount");
                return CommonResult.validateFailed("您已输入错误三次，账号已被冻结，请12小时后再试");
            } else {
                redisService.expireAt("teamManageSystem::user::" + username + ":errorCount", count, 10800);
            }
        } else {//如果之前没有，则生成一个错误次数缓存到Redis，并设定失效时间为3小时内
            redisService.set("teamManageSystem::user:" + username + "::errorCount", 1, 10800);
            redisService.expire("teamManageSystem:user::" + username + "::errorCount", 10800);
//                redisService.expireAt("teamManageSystem:user:" + username + ":errorCount", 1, 10800);
        }
        return CommonResult.validateFailed("用户名或密码错误");
    }

    private void clearRedisErrorCode(String username) {
        redisService.del("teamManageSystem::user::" + username + "::errorCount", "teamManageSystem::user::" + username + "::frozen");
    }

    /**
     * 将用户存入到elastic search
     */
    private void saveUserToEs() {
        List<EsUser> users = userMapper.getUserList().stream().map(u -> {
            File file = new File("E:/IdeaWorksplace/team_manage_system/src/main/resources/static" + u.getIcon());
            if (!file.exists())
                u.setIcon("http://t.cn/RCzsdCq");
            EsUser esUser = new EsUser();
            BeanUtils.copyProperties(u, esUser);
            return esUser;
        }).collect(Collectors.toList());
        userRepository.saveAll(users);
    }

    /**
     * 存储用户登录日志
     *
     * @param username
     */
    private void logging(String username) {
        Log log = new Log();
        //格式化登录时间
        String sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //获取IP地址
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ip = addr.getHostAddress();//获得本机IP
        String address = addr.getHostName();//获得本机名称
        log.setUserCode(username).setLoginTime(sdf).setIpAddress(ip + " " + address);
//        将日志存入数据库
        logService.save(log);
        EsLog esLog = new EsLog();
        BeanUtils.copyProperties(log, esLog);//深拷贝一份到esLog
//        将登录日志存入到elasticsearch
        try {
            logRepository.save(esLog);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("elasticsearch未开启");
        }

    }
}
