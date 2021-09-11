package com.thanh.springbootbackend.repository;

import com.thanh.springbootbackend.entity.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * BrandRepository
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
public interface BrandRepository extends CrudRepository<Brand, Long> {
}
