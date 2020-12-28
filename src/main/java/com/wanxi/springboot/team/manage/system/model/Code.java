package com.wanxi.springboot.team.manage.system.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Code {
    private Integer id;
    private String code;
    private String name;
    private String type;
}
