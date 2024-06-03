package com.example.server.Repository;

import com.example.server.Pojo.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IColorRepository extends JpaRepository<Color,Long> {

    @Query("SELECT c FROM Color c WHERE c.color =:color")
    public Color getColorByColor(@Param("color") String color);
}
