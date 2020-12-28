package com.wanxi.springboot.team.manage.system.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginParams implements Serializable {
    private String code;
    private String password;
}