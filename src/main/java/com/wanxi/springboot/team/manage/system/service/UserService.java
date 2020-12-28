package com.wanxi.springboot.team.manage.system.service;

import com.wanxi.springboot.team.manage.system.api.CommonResult;
import com.wanxi.springboot.team.manage.system.model.Permission;
import com.wanxi.springboot.team.manage.system.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
public interface UserService extends IService<User> {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    CommonResult getUserByName(String username);

    /**
     * 获取用户权限
     * @param id
     * @return
     */
    List<Permission> getPermissionsByUserId(Integer id);

    /**
     * 注册
     * @param user
     * @return
     */
    CommonResult register(User user);

    /**
     * 登录
     * @param user
     * @return
     */
    CommonResult login(User user);

    /**
     * 获取用户列表
     * @param map
     * @return
     */
    CommonResult getUsers(Map map);

    CommonResult deleteUser(Integer id);

    CommonResult updateUser(User user);

    CommonResult getCodeList();
}
