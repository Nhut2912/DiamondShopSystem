package com.example.server.Repository;

import com.example.server.Pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<Category,Long> {
    Category findByName(String name);
}
