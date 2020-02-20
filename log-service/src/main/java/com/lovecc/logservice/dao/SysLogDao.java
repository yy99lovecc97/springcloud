package com.lovecc.logservice.dao;

import com.lovecc.logservice.domain.SysLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysLogDao extends JpaRepository<SysLog,Long> {
}
