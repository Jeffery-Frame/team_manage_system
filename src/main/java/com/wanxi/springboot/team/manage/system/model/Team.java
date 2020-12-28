package com.wanxi.springboot.team.manage.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 小组名称
     */
    private String name;

    /**
     * 小组描述
     */
    private String description;

    /**
     * 小组长
     */
    private Integer leader;

    /**
     * 小组指导老师
     */
    private Integer coach;

    /**
     * 学习阶段
     */
    private String stage;

    /**
     * 所在教室
     */
    private String clazzRoom;

    /**
     * 小组人数
     */
    private Integer crew;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 可用标识
     */
    private String enable;


}
