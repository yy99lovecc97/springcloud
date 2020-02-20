package com.lovecc.uaaservice.dao;

import com.lovecc.uaaservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
