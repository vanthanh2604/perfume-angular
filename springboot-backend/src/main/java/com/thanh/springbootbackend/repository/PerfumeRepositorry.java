package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Perfume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PerfumeRepositorry extends CrudRepository<Perfume, Long> {
    @Query("SELECT per FROM Perfume per WHERE per.deleteFlag=0 ORDER BY per.id  DESC")
    List<Perfume> findAllDesc();

    @Query("SELECT per FROM Perfume per WHERE per.perfume_code=?1")
    Perfume findByCode(String code);

    @Query("SELECT per FROM Perfume per WHERE per.id=?1 and per.deleteFlag=0")
    Perfume findPerfumeByIdAndFlag(Long id);

    @Query("SELECT per FROM Perfume per WHERE per.deleteFlag=0 and per.id=?1")
    Perfume findPerfumeById(Long id);

    @Query("SELECT per FROM Perfume per JOIN Brand br ON per.brand.id=br.id WHERE per.deleteFlag=0 and " +
            "(per.perfume_code LIKE %?1% OR per.perfume_name like %?1% OR br.brand_name LIKE %?1%) ORDER BY per.id  DESC")
    List<Perfume> findByNameAndBrand(String name);
}
