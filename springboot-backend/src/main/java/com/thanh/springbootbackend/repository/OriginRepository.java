package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Origin;
import com.thanh.springbootbackend.entity.OutputInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * OriginRepository
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
public interface OriginRepository extends CrudRepository<Origin,Long> {
}
