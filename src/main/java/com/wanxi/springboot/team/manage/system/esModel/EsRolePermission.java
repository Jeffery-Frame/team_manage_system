package com.wanxi.springboot.team.manage.system.esModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EsRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 角色主键
     */
    private Integer role;

    /**
     * 权限主键
     */
    private Integer permission;


}
