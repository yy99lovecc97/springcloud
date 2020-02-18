package com.lovecc.blogservice.aop;

import com.alibaba.fastjson.JSON;
import com.lovecc.blogservice.domain.SysLog;
import com.lovecc.blogservice.service.LoggerService;
import com.lovecc.blogservice.util.HttpUtils;
import com.lovecc.blogservice.util.UserUtils;
import com.lovecc.common.annotation.SysLogger;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;
@Aspect
@Component
public class SysLoggerAspect {
    @Autowired
    private LoggerService loggerService;
    @Pointcut("@annotation(com.lovecc.common.annotation.SysLogger)")
    public void loggerPointCut(){}
    @Before("loggerPointCut()")
    public void saveSyslog(JoinPoint joinPoint){
        /**
         * java.lang.Object[] getArgs()：获取连接点方法运行时的入参列表
         * Signature getSignature() ：获取连接点的方法签名对象
         * java.lang.Object getTarget() ：获取连接点所在的目标对象
         * java.lang.Object getThis() ：获取代理对象本身
         */
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SysLog sysLog = new SysLog();
        SysLogger sysLogger = method.getAnnotation(SysLogger.class);
        if (sysLogger != null){
            sysLog.setOperation(sysLogger.value());
        }
        //请求的方法名
        String className =joinPoint.getTarget().getClass().getName();
        String methodName = method.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求的参数
        Object[] args =joinPoint.getArgs();
        String params = "";
        for(Object o:args){
            params+= JSON.toJSONString(o);
        }
        if(!StringUtils.isEmpty(params)) {
            sysLog.setParams(params);
        }
        //设置IP地址
        sysLog.setIp(HttpUtils.getIpAddress());
        //用户名
        String username = UserUtils.getCurrentPrinciple();
        if(!StringUtils.isEmpty(username)) {
            sysLog.setUsername(username);
        }
        sysLog.setCreateDate(new Date());
        //保存系统日志
        loggerService.log(sysLog);
    }
}
