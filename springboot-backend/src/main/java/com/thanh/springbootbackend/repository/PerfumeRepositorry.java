package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Perfume;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PerfumeRepositorry
 * Version 1.0
 *
 * Date: 01-09-2021
 *
 * Copyright
 *
 * Modification Logs:
 *  DATE                 AUTHOR          DESCRIPTION
 *  -----------------------------------------------------------------------
 *   01-09-2021         ThanhNV80            Create
 */

@Repository
public interface PerfumeRepositorry extends CrudRepository<Perfume, String> {
    @Query("SELECT per FROM Perfume per WHERE per.deleteFlag=0 ORDER BY per.id  DESC")
    List<Perfume> findAllDesc();

    @Query("SELECT per FROM Perfume per WHERE per.deleteFlag=0 and per.amount>0 ORDER BY per.id  DESC")
    List<Perfume> findAllPerfumeStocking();

    @Query("SELECT per FROM Perfume per WHERE per.perfume_name=?1")
    Perfume findByName(String perfume_name);

    @Query("SELECT per FROM Perfume per WHERE per.id=?1 and per.deleteFlag=0")
    Perfume findPerfumeByIdAndFlag(String id);
}
