package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.entity.OutputInfo;

import java.util.List;

public interface IOutputInfoService {
    List<OutputInfo> get_ALl_OutputInfo_by_idInput(Long id);
    void saveOuputInfo(OutputInfo outputInfo);
    void addOutputInfo(List<InputInfoModel> list, Output output);
}
