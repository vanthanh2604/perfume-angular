package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.model.OutputModel;
import com.thanh.springbootbackend.repository.OutputRepository;
import com.thanh.springbootbackend.service.serviceI.IOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * OutputService
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
public class OutputService implements IOutputService {
    @Autowired
    private OutputRepository outputRepository;

    /**
     * get all output
     */
    @Override
    public List<Output> getAllOutput() {
        return outputRepository.findAllDesc();
    }

    /**
     * get output by id
     * @param id
     */
    @Override
    public Output getById(Long id) {
        return outputRepository.findByIdOp(id);
    }

    /**
     * create output
     * @param outputModel
     */
    @Override
    public Output createOutput(OutputModel outputModel) {
        Output output=new Output();
        output.setCustomerName(outputModel.getCustomerName());
        output.setPhone(outputModel.getPhone());
        output.setAddress(outputModel.getAddress());
        output.setDateOutput(new Date());
        outputRepository.save((output));
        return outputRepository.findByOpcuoi();
    }

    /**
     * get output last
     */
    @Override
    public Output getByOpCuoi() {
        return outputRepository.findByOpcuoi();
    }
}
