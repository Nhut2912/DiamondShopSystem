package com.example.server.Repository;

import com.example.server.Pojo.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISizeRepository extends JpaRepository<Size,Long> {

    @Query("SELECT s FROM Size s WHERE s.size =:size")
    public Size getSizeBySize(@Param("size") int size);
}
