package com.example.server.Service.Blog;

import com.example.server.Pojo.Blog;
import com.example.server.Repository.IBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    IBlogRepository iBlogRepository;

    public boolean createBlog(Blog blog) {
        try {
            iBlogRepository.save(blog);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean updateBlog(Blog blog) {
        try {
            Optional<Blog> blogFind = iBlogRepository.findById(blog.getId());
            if (blogFind.isPresent()) {
                iBlogRepository.save(blog);
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean deleteBlog(Blog blog) {
        try {
            Optional<Blog> blogFind = iBlogRepository.findById(blog.getId());
            if (blogFind.isPresent()) {
                blog.setActive(false);
                iBlogRepository.save(blog);
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public List<Blog> getBlogs() {
        List<Blog> blogList = iBlogRepository.findByIsActive(true);
        if(blogList == null){
            return blogList = new ArrayList<>();
        }else return blogList;
    }

}
