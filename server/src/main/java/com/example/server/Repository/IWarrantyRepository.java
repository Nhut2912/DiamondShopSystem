package com.example.server.Repository;

import com.example.server.Pojo.Warranty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWarrantyRepository extends JpaRepository<Warranty,Long> {
}
