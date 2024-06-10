package com.example.server.Repository;

import com.example.server.Pojo.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPromotionRepository extends JpaRepository<Promotion, Long> {
}
