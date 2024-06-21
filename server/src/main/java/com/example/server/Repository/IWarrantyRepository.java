package com.example.server.Repository;

import com.example.server.Pojo.Warranty;
import com.example.server.Pojo.WarrantyPolicy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWarrantyRepository extends CrudRepository<Warranty, Long> {
    List<Warranty> findByStatus(boolean status);
    Warranty findByProduct_Id(Long id);
}
