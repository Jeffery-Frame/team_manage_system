package com.wanxi.springboot.team.manage.system.esModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
@Document(indexName = "user",shards = 2,replicas = 2)
public class EsUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户学号
     */
    private String code;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户头像
     */
    private String icon;

    /**
     * 电话
     */
    private String phone;

    /**
     * 入学时间
     */
    private Date entranceTime;

    /**
     * 入学批次
     */
    private String batch;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户住址
     */
    private String address;

    /**
     * 学历
     */
    private String graduate;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 是否可用标识
     */
    private String enable;

    /**
     * 备注
     */
    private String note;


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
