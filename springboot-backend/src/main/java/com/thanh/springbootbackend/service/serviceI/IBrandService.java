package com.thanh.springbootbackend.service.serviceI;

import java.util.List;

import com.thanh.springbootbackend.entity.Brand;

/**
 * IBrandService
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
public interface IBrandService {
    /**
     * get all brand
     * @return
     */
    List<Brand> getAllBrand();

    /**
     * get brand by id
     * @param id
     * @return
     */
    Brand getBrandById(long id);
}
