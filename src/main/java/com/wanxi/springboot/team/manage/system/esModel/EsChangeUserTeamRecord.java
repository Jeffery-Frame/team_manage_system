package com.wanxi.springboot.team.manage.system.esModel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

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
@Document(indexName = "change_user_team_record",shards = 2,replicas = 2)
public class EsChangeUserTeamRecord implements Serializable {

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
     * 之前的小组
     */
    private Integer previousTeam;

    /**
     * 现在的小组
     */
    private Integer nowTeam;

    /**
     * 记录生成时间
     */
    private LocalDateTime createTime;


}
