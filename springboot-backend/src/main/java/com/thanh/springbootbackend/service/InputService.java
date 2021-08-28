package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.Suplier;
import com.thanh.springbootbackend.repository.InputRepository;
import com.thanh.springbootbackend.repository.SuplierRepository;
import com.thanh.springbootbackend.service.serviceI.IInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InputService implements IInputService {
    @Autowired
    private InputRepository inputRepository;
    @Autowired
    private SuplierRepository suplierRepository;
    @Override
    public List<Input> get_ALl_Input() {
        return inputRepository.findAllDesc();
    }

    @Override
    public Input getById(Long id) {
        return inputRepository.findByIdIp(id);
    }

    @Override
    public void addInput(Input input) {
        inputRepository.save(input);
    }

    @Override
    public Input createInput(Long suplierId) {
        Suplier suplier=suplierRepository.findById(suplierId).get();
        Input input = new Input();
        input.setDateInput(new Date());
        input.setSuplier(suplier);
        inputRepository.save(input);
        Input input1=inputRepository.findByIdIpcuoi();
        return input1;
    }

    @Override
    public Input getInputCuoi() {
        return inputRepository.findByIdIpcuoi();
    }

}
