package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Input;

import java.util.List;
import java.util.Optional;

public interface IInputService {
    List<Input>get_ALl_Input();
    Input getById(Long id);
    void addInput(Input input);
    Input createInput(Long suplierId);
    Input getInputCuoi();
}
