package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Perfume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PerfumeRepositorry extends CrudRepository<Perfume, String> {
    @Query("SELECT per FROM Perfume per WHERE per.deleteFlag=0 ORDER BY per.id  DESC")
    List<Perfume> findAllDesc();

    @Query("SELECT per FROM Perfume per WHERE per.perfume_name=?1")
    Perfume findByCode(String perfume_name);

    @Query("SELECT per FROM Perfume per WHERE per.id=?1 and per.deleteFlag=0")
    Perfume findPerfumeByIdAndFlag(String id);
}
