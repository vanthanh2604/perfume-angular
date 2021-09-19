package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Origin;
import com.thanh.springbootbackend.repository.OriginRepository;
import com.thanh.springbootbackend.service.serviceI.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * OriginService
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
public class OriginService implements IOriginService {
    @Autowired
    private OriginRepository originRepository;

    /**
     * get all origin
     * @return
     */
    @Override
    public List<Origin> getAll() {
        return (List<Origin>) originRepository.findAll();
    }
}
