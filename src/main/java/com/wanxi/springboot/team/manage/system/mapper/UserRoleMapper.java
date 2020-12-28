package com.wanxi.springboot.team.manage.system.mapper;

import com.wanxi.springboot.team.manage.system.model.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    int updateUserRole(UserRole userRole);
}
