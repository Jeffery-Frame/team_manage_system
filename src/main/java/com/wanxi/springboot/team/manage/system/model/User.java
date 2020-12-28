package com.wanxi.springboot.team.manage.system.model;

import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDate;

import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * <p>
 *
 * </p>
 *
 * @author 蒋帆
 * @since 2020-12-21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户实体类")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @ApiModelProperty(value = "用户主键，自动递增")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String name;

    /**
     * 用户学号
     */
    @ApiModelProperty(value = "用户学号")
    private String code;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String icon;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 入学时间
     */
    @ApiModelProperty(value = "入学时间")
    private String entranceTime;

    /**
     * 入学批次
     */
    @ApiModelProperty(value = "入学批次")
    private String batch;

    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private String sex;
    @ApiModelProperty(value = "用户性别名")
    private String sexName;

    /**
     * 用户住址
     */
    @ApiModelProperty(value = "用户住址")
    private String address;

    /**
     * 学历
     */
    @ApiModelProperty(value = "学历")
    private String graduate;
    @ApiModelProperty(value = "学历名")
    private String graduateName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 用户账号状态
     */
    @ApiModelProperty(value = "用户账号状态")
    private String status;
    @ApiModelProperty(value = "用户账号状态名")
    private String statusName;
    /**
     * 是否可用标识
     */
    @ApiModelProperty(value = "是否可用标识")
    private String enable;
    @ApiModelProperty(value = "是否可用标识名")
    private String enableName;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "用户角色")
    private Integer role;
    @ApiModelProperty(value = "用户角色名")
    private String roleName;

    @ApiModelProperty(value = "用户权限")
    private Set<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.code;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    }

    @Override
    public boolean isEnabled() {
        if (this.enable == null) {
            return false;
        }
        return this.enable == "yes";
    }

    public void setEnabled(boolean enable) {
    }
}
