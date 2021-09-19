package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.model.OutputModel;

import java.util.List;

/**
 * IOutputService
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
public interface IOutputService {

    /**
     * get all output
     * @return
     */
    List<Output>getAllOutput();

    /**
     * get by id output
     * @param id
     * @return
     */
    Output getById(Long id);

    /**
     * create output
     * @param outputModel
     * @return
     */
    Output createOutput(OutputModel outputModel);

    /**
     * get by output last
     * @return
     */
    Output getByOpCuoi();
}
