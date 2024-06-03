package com.example.server.Repository;

import com.example.server.Pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {

    @Query("SELECT c FROM Category  c WHERE c.name =:name")
    public Category getCategoryByName(@Param("name") String name);
}
