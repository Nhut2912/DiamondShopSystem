package com.example.server.Controller;

import com.example.server.Pojo.Blog;
import com.example.server.Service.Blog.IBlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/blog")
@CrossOrigin(origins = "http://localhost:3000")
public class BlogController {
    IBlogService iBlogService;

    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@RequestBody Blog blog){
        return new ResponseEntity<>(iBlogService.createBlog(blog), HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBlog(@RequestBody Blog blog){
        return new ResponseEntity<>(iBlogService.updateBlog(blog), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteBlog(@RequestBody Blog blog){
        return new ResponseEntity<>(iBlogService.deleteBlog(blog), HttpStatus.OK);
    }

    @PostMapping("/get")
    public ResponseEntity<?> getBlogs(){
        return new ResponseEntity<>(iBlogService.getBlogs(), HttpStatus.OK);
    }
}
