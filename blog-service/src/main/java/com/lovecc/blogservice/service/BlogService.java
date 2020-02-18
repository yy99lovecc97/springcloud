package com.lovecc.blogservice.service;

import com.lovecc.blogservice.client.hystrix.UserServiceHystrix;
import com.lovecc.blogservice.dao.BlogDao;
import com.lovecc.blogservice.domain.Blog;
import com.lovecc.blogservice.domain.User;
import com.lovecc.blogservice.dto.BlogDetailDTO;
import com.lovecc.blogservice.util.UserUtils;
import com.lovecc.common.dto.RespDTO;
import com.lovecc.common.exception.CommonException;
import com.lovecc.common.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    UserServiceHystrix userServiceHystrix;
    public Blog postBlog(Blog blog){
        return blogDao.save(blog);
    }
    public List<Blog> findBlogs(String username){
        return blogDao.findByUsername(username);
    }
    public BlogDetailDTO findBlogDetail(Long id){
        Optional<Blog> blogOptional = blogDao.findById(id);
        Blog blog = null;
        if(blogOptional.isPresent()){
            blog=blogOptional.get();
        }
        if (null == blog) {
            throw new CommonException(ErrorCode.BLOG_IS_NOT_EXIST);
        }
        RespDTO<User> respDTO = userServiceHystrix.getUser(UserUtils.getCurrentToken(), blog.getUsername());
        if (respDTO==null) {
            throw new CommonException(ErrorCode.RPC_ERROR);
        }
        BlogDetailDTO blogDetailDTO = new BlogDetailDTO();
        blogDetailDTO.setBlog(blog);
        blogDetailDTO.setUser(respDTO.data);
        return blogDetailDTO;
    }
}
