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
    List<OutputInfo> getALlOutputInfoByIdInput(Long id);
    void saveOuputInfo(OutputInfo outputInfo);
    void addOutputInfo(List<InputInfoModel> list, Output output);
}
