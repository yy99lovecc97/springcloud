package com.lovecc.blogservice.dto;

import com.lovecc.blogservice.domain.Blog;
import com.lovecc.blogservice.domain.User;

public class BlogDetailDTO {
    private Blog blog;
    private User user;

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
