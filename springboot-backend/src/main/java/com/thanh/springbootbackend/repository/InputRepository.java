package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Input;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InputRepository
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
public interface InputRepository extends CrudRepository<Input,Long> {

    /**
     * find all input order by desc
     * @return
     */
    @Query("SELECT inp FROM Input inp WHERE inp.deleteFlag=0 ORDER BY inp.id  DESC")
    List<Input> findAllDesc();

    /**
     * find by input id
     * @param id
     * @return
     */
    @Query("SELECT inp FROM Input inp WHERE inp.id=?1 and inp.deleteFlag=0")
    Input findByIdIp(Long id);

    /**
     * find by input last
     * @return
     */
    @Query(nativeQuery=true,value="SELECT * FROM Input inp ORDER BY inp.id desc limit 1")
    Input findByIdIpcuoi();

    /**
     * total input
     * @return
     */
    @Query(nativeQuery=true, value="select SUM(total) from input")
    Double totalByInput();
}
