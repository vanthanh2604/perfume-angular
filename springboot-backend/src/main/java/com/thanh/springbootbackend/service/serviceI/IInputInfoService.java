package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.InputInfo;

import java.util.List;

public interface IInputInfoService {
    List<InputInfo> get_ALl_InputInfo_by_idInput(Long id);
    void addInputInfo(List<InputInfoModel> infoDto, Input input);
}
