package com.lovecc.userservice.dao;

import com.lovecc.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
