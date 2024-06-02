package com.example.server.Repository;

import com.example.server.Pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface IImagesRepository extends CrudRepository<Image,Long> {

}
