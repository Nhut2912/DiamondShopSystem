package com.example.server.Repository;

import com.example.server.Pojo.Warranty;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarrantyRepository extends CrudRepository<Warranty, Long> {
}
