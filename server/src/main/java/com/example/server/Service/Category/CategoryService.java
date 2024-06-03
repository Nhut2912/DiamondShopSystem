package com.example.server.Service.Category;

import com.example.server.Pojo.Category;
import com.example.server.Repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService  implements  ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getCategory(String name) {
        Category category = categoryRepository.getCategoryByName(name);
        if(category == null){
            Category newCategory = new Category();
            newCategory.setName(name);
            save(newCategory);
            return categoryRepository.getCategoryByName(name);
        }else return category;
    }
}
