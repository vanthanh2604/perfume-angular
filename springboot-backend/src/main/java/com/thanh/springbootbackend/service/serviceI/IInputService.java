package com.thanh.springbootbackend.service.serviceI;

import java.util.List;
import java.util.Optional;
import com.thanh.springbootbackend.entity.Input;

/**
 * IInputService
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

public interface IInputService {
    List<Input>getALlInput();
    Input getById(Long id);
    Input createInput(Long suplierId);
    Input getInputCuoi();
}
