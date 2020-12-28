package com.wanxi.springboot.team.manage.system.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = {""})
public class WeeklyReceiver {




    @RabbitHandler
    public void process(String msg) {

        try {
//            Weekly weekly = JSONObject.parseObject(msg, Weekly.class);
//            System.out.println(weekly);
            System.out.println(msg);
        }catch (Exception e){
            e.printStackTrace();
        }

        return;
    }
}