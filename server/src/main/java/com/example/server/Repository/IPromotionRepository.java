package com.example.server.Repository;

import com.example.server.Pojo.Promotion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionRepository extends CrudRepository<Promotion, Long> {
}
