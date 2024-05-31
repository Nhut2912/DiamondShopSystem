package com.example.server.Repository;

import com.example.server.Pojo.Origin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOriginRepository extends JpaRepository <Origin,Long> {
    Origin findOneByCode(String Id);
}
