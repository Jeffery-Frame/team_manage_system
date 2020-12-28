package com.wanxi.springboot.team.manage.system.mapper;

import com.wanxi.springboot.team.manage.system.model.Code;
import com.wanxi.springboot.team.manage.system.model.Permission;
import com.wanxi.springboot.team.manage.system.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserByName(String username);

    List<Permission> getPermissionsByUserId(Integer id);

    int register(User user);

    List<User> getUsers(Map map);

    long getUserCount(Map map);

    List<User> getUserList();

    int deleteUser(Integer id);

    int updateUser(User user);

    List<Code> getCodeList();
}
