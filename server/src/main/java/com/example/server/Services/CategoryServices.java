package com.example.server.Services;

import com.example.server.Pojo.Category;
import com.example.server.Repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CategoryServices implements ICategoryServices {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> saveCategory(Category category) {
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }
}
