package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.entity.OutputInfo;

import java.util.List;

/**
 * IOutputInfoService
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
public interface IOutputInfoService {

    /**
     * get all output info by id input
     * @param id
     * @return
     */
    List<OutputInfo> getALlOutputInfoByIdInput(Long id);

    /**
     * save output info
     * @param outputInfo
     */
    void saveOuputInfo(OutputInfo outputInfo);

    /**
     * add output info
     * @param list
     * @param output
     */
    void addOutputInfo(List<InputInfoModel> list, Output output);
}
