package com.wanxi.springboot.team.manage.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.wanxi.springboot.team.manage.system.mapper")
@SpringBootApplication
public class TeamManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeamManageSystemApplication.class, args);
    }

}
