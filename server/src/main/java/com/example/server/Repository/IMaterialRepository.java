package com.example.server.Repository;

import com.example.server.Pojo.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMaterialRepository extends JpaRepository<Material, Long> {
}
