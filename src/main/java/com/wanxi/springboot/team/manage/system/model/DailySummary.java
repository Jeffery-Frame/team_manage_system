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
public class DailySummary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户主键
     */
    private Integer user;

    /**
     * 学习内容
     */
    private String study;

    /**
     * 收获
     */
    private String harvest;

    /**
     * 不足
     */
    private String insufficient;

    /**
     * 创建（提交）时间
     */
    private LocalDateTime createTime;

    /**
     * 日报编辑状态
     */
    private String publishStatus;

    /**
     * 可用标识
     */
    private String enable;

    /**
     * 日报批阅状态
     */
    private String readStatus;


}
