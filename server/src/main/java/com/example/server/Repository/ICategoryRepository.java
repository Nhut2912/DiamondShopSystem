package com.example.server.Repository;

import com.example.server.Pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;


import java.util.Optional;
@Repository
public interface ICategoryRepository extends CrudRepository<Category,Long> {

}
