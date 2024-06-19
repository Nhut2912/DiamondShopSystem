package com.example.server.Repository;

import com.example.server.Pojo.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IImageRepository extends CrudRepository<Image, Long> {
    Image getImageByUrl(String url);
}
