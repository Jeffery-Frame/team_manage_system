package com.wanxi.springboot.team.manage.system.esModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EsPermission implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限值
     */
    private String value;

    /**
     * 权限访问路径
     */
    private String uri;

    /**
     * 权限图标
     */
    private String icon;

    /**
     * 权限类型
     */
    private String type;

    /**
     * 父类主键
     */
    private Integer parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 可用标识
     */
    private String enable;


    @Override
    public String getAuthority() {
        return this.value;
    }
}
