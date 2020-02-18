package com.lovecc.userservice.service;

import com.alibaba.fastjson.JSON;
import com.lovecc.userservice.config.RabbitConfig;
import com.lovecc.userservice.domain.SysLog;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    public void log(SysLog sysLog){
        amqpTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(sysLog));
    }
}
