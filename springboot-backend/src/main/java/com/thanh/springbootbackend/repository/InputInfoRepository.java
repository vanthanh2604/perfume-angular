package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.InputInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InputInfoRepository
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
public interface InputInfoRepository extends CrudRepository<InputInfo, Long> {

    /**
     * find all by input id
     * @param id
     * @return
     */
    @Query("SELECT inp FROM InputInfo inp WHERE inp.input.id=?1")
    List<InputInfo>findAllByInputId(Long id);

    /**
     * sum by amount
     * @return
     */
    @Query(nativeQuery=true,value="select sum(amount) from input_info")
    int sumByAmount();
}
