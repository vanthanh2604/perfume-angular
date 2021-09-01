package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.model.OutputModel;

import java.util.List;

public interface IOutputService {
    List<Output>get_All_Output();
    Output getById(Long id);
    void saveOutput(Output output);
    Output createOutput(OutputModel outputModel);
    Output getByOpCuoi();
}
