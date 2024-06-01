package com.example.server.Repository;

import com.example.server.Pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

<<<<<<< HEAD
public interface IImagesRepository extends JpaRepository<Image,Long> {
=======
public interface IImagesRepository extends CrudRepository<Image,Long> {

>>>>>>> 7ecfc1bd0c699532b8f9d339266719ce099652fc
}
