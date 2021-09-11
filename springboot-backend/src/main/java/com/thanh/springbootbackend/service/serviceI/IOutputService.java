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
    List<Output>getAllOutput();
    Output getById(Long id);
    Output createOutput(OutputModel outputModel);
    Output getByOpCuoi();
}
