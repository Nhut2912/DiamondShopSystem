package com.example.server.Repository;

import com.example.server.Pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
<<<<<<< HEAD
=======
import org.springframework.stereotype.Repository;
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc

import java.util.Optional;
@Repository
public interface ICategoryRepository extends CrudRepository<Category,Long> {

<<<<<<< HEAD
public interface ICategoryRepository extends CrudRepository<Category,Long> {
=======
>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
}
