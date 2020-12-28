package com.wanxi.springboot.team.manage.system.esModel;

import lombok.Data;

import java.io.Serializable;

@Data
public class EsLoginParams implements Serializable {
    private String code;
    private String password;
}