package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Output;

import java.util.List;

public interface IOutputService {
    List<Output>get_All_Output();
    Output getById(Long id);
    void saveOutput(Output output);
    Output createOutput(Output output);
    Output getByOpCuoi();
}
