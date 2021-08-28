package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.repository.OutputRepository;
import com.thanh.springbootbackend.service.serviceI.IOutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OutputService implements IOutputService {
    @Autowired
    private OutputRepository outputRepository;
    @Override
    public List<Output> get_All_Output() {
        return outputRepository.findAllDesc();
    }

    @Override
    public Output getById(Long id) {
        return outputRepository.findByIdOp(id);
    }

    @Override
    public void saveOutput(Output output) {
        output.setDateOutput(new Date());
        outputRepository.save((output));
    }

    @Override
    public Output createOutput(Output output) {
        output.setDateOutput(new Date());
        outputRepository.save((output));
        return outputRepository.findByOpcuoi();
    }

    @Override
    public Output getByOpCuoi() {
        return outputRepository.findByOpcuoi();
    }
}
