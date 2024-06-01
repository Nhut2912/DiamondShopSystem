package com.example.server.Controller;

<<<<<<< HEAD
=======
import com.example.server.Pojo.Category;
import com.example.server.Services.ICategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/category")
public class CategoryController {
<<<<<<< HEAD
=======
    @Autowired
    ICategoryServices categoryServices;
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Category category) {
        return categoryServices.saveCategory(category);
    }
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
}
