package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.Suplier;
import com.thanh.springbootbackend.repository.InputRepository;
import com.thanh.springbootbackend.repository.SuplierRepository;
import com.thanh.springbootbackend.service.serviceI.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * InputService
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
public class InputService implements IInputService {
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private SuplierRepository suplierRepository;

    /**
     * get all input
     */
    @Override
    public List<Input> getALlInput() {
        return inputRepository.findAllDesc();
    }

    /**
     * get input by id
     * @param id
     */
    @Override
    public Input getById(Long id) {
        return inputRepository.findByIdIp(id);
    }

    /**
     * get all suppliers
     */
    @Override
    public Input createInput(Long suplierId) {
        Suplier suplier=suplierRepository.findById(suplierId).get();
        Input input = new Input();
        input.setDateInput(new Date());
        input.setSuplier(suplier);
        inputRepository.save(input);
        Input input1=inputRepository.findByIdIpcuoi();
        return input1;
    }

    /**
     * get input last
     */
    @Override
    public Input getInputCuoi() {
        return inputRepository.findByIdIpcuoi();
    }

}
