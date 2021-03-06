package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Suplier;
import com.thanh.springbootbackend.repository.SuplierRepository;
import com.thanh.springbootbackend.service.serviceI.ISuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * SuplierService
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
public class SuplierService implements ISuplierService {
    @Autowired
    private SuplierRepository suplierRepository;

    /**
     * get all suppliers
     * @return
     */
    @Override
    public List<Suplier> getAllsupliers() {
        return (List<Suplier>) suplierRepository.findAll();
    }

    /**
     * get supplier by id
     * @param id
     * @return
     */
    @Override
    public Suplier getById(Long id) {
        return suplierRepository.findById(id).get();
    }
}
