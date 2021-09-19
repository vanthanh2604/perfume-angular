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

    /**
     * get all input
     * @return
     */
    List<Input>getALlInput();

    /**
     * get input by id
     * @param id
     * @return
     */
    Input getById(Long id);

    /**
     * create input
     * @param suplierId
     * @return
     */
    Input createInput(Long suplierId);

    /**
     * get input last
     * @return
     */
    Input getInputCuoi();
}
