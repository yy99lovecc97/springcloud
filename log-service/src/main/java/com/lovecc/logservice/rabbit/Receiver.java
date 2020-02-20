package com.lovecc.logservice.rabbit;

import com.alibaba.fastjson.JSON;
import com.lovecc.logservice.domain.SysLog;
import com.lovecc.logservice.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    //CountDownLatch是一个同步工具类，它允许一个或多个线程一直等待，直到其他线程执行完后再执行。
    //用给定的计数初始化CountDownLatch，其含义是要被等待执行完的线程个数。
    //每次调用CountDown()，计数减1
    //主程序执行到await()函数会阻塞等待线程的执行，直到计数为0
    private CountDownLatch latch = new CountDownLatch(1);
    @Autowired
    SysLogService sysLogService;
    public void receiveMessage(String message){
        System.out.println("Received <" + message + ">");
        SysLog sysLog = JSON.parseObject(message,SysLog.class);
        sysLogService.saveLog(sysLog);
        latch.countDown();
    }
}
