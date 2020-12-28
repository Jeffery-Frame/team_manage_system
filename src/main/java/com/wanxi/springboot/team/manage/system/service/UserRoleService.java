package com.wanxi.springboot.team.manage.system.service;

import com.wanxi.springboot.team.manage.system.model.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
public interface UserRoleService extends IService<UserRole> {

    int updateUserRole(UserRole userRole);
}
