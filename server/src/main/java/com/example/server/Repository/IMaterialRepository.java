package com.example.server.Repository;

import com.example.server.Pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMaterialRepository extends CrudRepository<Material, Long> {
}
