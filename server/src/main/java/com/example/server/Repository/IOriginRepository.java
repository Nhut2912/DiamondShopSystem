package com.example.server.Repository;

import com.example.server.Pojo.Origin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOriginRepository extends JpaRepository<Origin,Long> {
    @Query("SELECT o FROM Origin o WHERE o.origin = :origin")
    public Origin getOriginByOrigin(@Param("origin") String origin);
}
