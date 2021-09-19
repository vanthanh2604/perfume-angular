package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Suplier;

import java.util.List;

/**
 * ISuplierService
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
public interface ISuplierService {

    /**
     * get all supplier
     * @return
     */
    List<Suplier> getAllsupliers();

    /**
     * get supplier by id
     * @param id
     * @return
     */
    Suplier getById(Long id);
}
