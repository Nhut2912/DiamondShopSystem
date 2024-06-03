package com.example.server.Service.Category;

import com.example.server.Pojo.Category;

public interface ICategoryService {

    public void save(Category category);
    public Category getCategory(String name);
}
