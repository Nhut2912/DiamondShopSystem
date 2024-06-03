package com.example.server.Repository;

import com.example.server.Pojo.Cut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICutRepository extends JpaRepository<Cut,Long> {

    @Query("SELECT c FROM Cut c WHERE c.cut = :cut")
    public Cut getCutByCut(@Param("cut") String cut);
}
