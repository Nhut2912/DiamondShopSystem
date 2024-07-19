package com.example.server.Repository;

import com.example.server.Pojo.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Image getImageByUrl(String url);
    public void deleteImagesByProduct_Id(Long id);

    public Set<Image> getImagesByProduct_Id(Long id);
}
