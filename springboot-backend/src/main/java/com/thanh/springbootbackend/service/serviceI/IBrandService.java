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
    List<Brand> getAllBrand();
    Brand getBrandById(long id);
}
