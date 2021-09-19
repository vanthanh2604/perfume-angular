package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.InputInfo;

import java.util.List;

/**
 * IInputInfoService
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
public interface IInputInfoService {

    /**
     * get aLl input info by id input
     * @param id
     * @return
     */
    List<InputInfo> getALlInputInfoByIdInput(Long id);

    /**
     * add input info
     * @param infoDto
     * @param input
     */
    void addInputInfo(List<InputInfoModel> infoDto, Input input);
}
