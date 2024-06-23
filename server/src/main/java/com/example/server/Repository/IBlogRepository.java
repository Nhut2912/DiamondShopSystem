package com.example.server.Repository;

import com.example.server.Pojo.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBlogRepository extends CrudRepository<Blog, Long> {
    public List<Blog> findByIsActive(boolean isActive);
}
