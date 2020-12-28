package com.wanxi.springboot.team.manage.system.service.impl;

import com.wanxi.springboot.team.manage.system.model.UserRole;
import com.wanxi.springboot.team.manage.system.mapper.UserRoleMapper;
import com.wanxi.springboot.team.manage.system.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired(required = false)
    UserRoleMapper userRoleMapper;
    @Override
    public int updateUserRole(UserRole userRole) {
        return userRoleMapper.updateUserRole(userRole);
    }
}
