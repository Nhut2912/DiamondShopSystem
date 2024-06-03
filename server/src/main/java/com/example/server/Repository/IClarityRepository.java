package com.example.server.Repository;

import com.example.server.Pojo.Clarity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClarityRepository extends JpaRepository<Clarity,Long> {

    @Query("SELECT c FROM Clarity  c WHERE c.clarity =:clarity")
    public Clarity getClarityByClarity(@Param("clarity") String clarity);
}
