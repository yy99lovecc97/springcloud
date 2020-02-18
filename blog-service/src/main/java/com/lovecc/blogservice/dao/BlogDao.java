package com.lovecc.blogservice.dao;

import com.lovecc.blogservice.domain.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogDao extends JpaRepository<Blog,Long> {

    //在Spring Data JPA中提供了一种衍生查询，只要函数的声明有findBy,getBy,readBy,他就会去读读取。
    List<Blog> findByUsername(String username);
}
