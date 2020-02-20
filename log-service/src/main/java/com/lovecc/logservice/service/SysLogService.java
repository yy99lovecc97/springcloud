package com.lovecc.logservice.service;

import com.lovecc.logservice.dao.SysLogDao;
import com.lovecc.logservice.domain.SysLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogService {
    @Autowired
    SysLogDao sysLogDao;
    public void saveLog(SysLog sysLog){
        sysLogDao.save(sysLog);
    }
}
