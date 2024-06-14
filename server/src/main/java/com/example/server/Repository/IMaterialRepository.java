package com.example.server.Repository;

import com.example.server.Pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IMaterialRepository extends JpaRepository<Material,Long> {

    @Query("SELECT m FROM Material  m WHERE m.name = :name")
    public Material getMaterialByName(@Param("name") String name);

}
