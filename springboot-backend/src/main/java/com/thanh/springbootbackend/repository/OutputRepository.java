package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.Output;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * OutputRepository
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
public interface OutputRepository extends CrudRepository<Output,Long> {

    /**
     * find all desc
     * @return
     */
    @Query("SELECT op FROM Output op WHERE op.deleteFlag=0 ORDER BY op.id  DESC")
    List<Output> findAllDesc();

    /**
     * find by id output
     * @param id
     * @return Output
     */
    @Query("SELECT outp FROM Output outp WHERE outp.id=?1 and outp.deleteFlag=0")
    Output findByIdOp(Long id);

    /**
     * find by output last
     * @return Output
     */
    @Query(nativeQuery=true,value="SELECT * FROM Output op ORDER BY op.id desc limit 1")
    Output findByOpcuoi();

    /**
     * total by output
     * @return
     */
    @Query(nativeQuery=true,value="select SUM(op.output_price*amount)" +
            "from  output_info as op join output as o on op.output_id=o.id")
    Double totalByOutput();

    /**
     * count output
     * @return
     */
    @Query(nativeQuery=true,value="SELECT count(id) from output")
    int countOutput();


}
