package com.example.server.Service.Blog;

import com.example.server.Pojo.Blog;

import java.util.List;

public interface IBlogService {
    public boolean createBlog(Blog blog);
    public boolean updateBlog(Blog blog);
    public boolean deleteBlog(Blog blog);
    public List<Blog> getBlogs();
}
