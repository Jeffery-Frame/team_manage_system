package com.wanxi.springboot.team.manage.system.esModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EsWords implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，自动递增
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 单词英文释义
     */
    private String english;

    /**
     * 单词中文释义
     */
    private String chinese;

    /**
     * 提交者
     */
    private Integer user;

    /**
     * 提交时间
     */
    private LocalDateTime createTime;

    /**
     * 点赞数
     */
    private Long likesCount;

    /**
     * 可用标识
     */
    private String enable;


}
