package com.example.server.Repository;

import com.example.server.Pojo.Order;
import com.example.server.Pojo.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPromotionRepository extends CrudRepository<Promotion, Long> {

}
