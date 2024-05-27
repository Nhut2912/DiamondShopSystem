package com.example.server.Repository;

import com.example.server.Pojo.Diamond;
import com.example.server.Pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface IDiamondRepository extends JpaRepository <Diamond,Long> {
}
