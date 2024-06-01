package com.example.server.Services;

import com.example.server.Pojo.Category;
import org.springframework.http.ResponseEntity;

public interface ICategoryServices {
    public ResponseEntity<?> saveCategory(Category category);
}
