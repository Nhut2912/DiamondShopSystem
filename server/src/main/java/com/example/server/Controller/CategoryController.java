package com.example.server.Controller;

<<<<<<< HEAD
=======

>>>>>>> 308af14b9d231cd737fe4c3dd64cb555c9abde74
import com.example.server.Pojo.Category;
import com.example.server.Services.ICategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/category")
public class CategoryController {

    @Autowired
    ICategoryServices categoryServices;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Category category) {
        return categoryServices.saveCategory(category);
    }

}
