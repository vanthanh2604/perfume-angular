package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Brand;
import com.thanh.springbootbackend.repository.BrandRepository;
import com.thanh.springbootbackend.service.serviceI.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BrandService
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

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;

    /**
     * get all brand
     */
    @Override
    public List<Brand> getAllBrand() {
        return (List<Brand>) brandRepository.findAll();
    }

    /**
     * get brand by id
     * @param id
     */
    @Override
    public Brand getBrandById(long id) {
        return brandRepository.findById(id).get();
    }
}
