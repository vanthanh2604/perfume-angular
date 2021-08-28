package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Suplier;
import com.thanh.springbootbackend.repository.SuplierRepository;
import com.thanh.springbootbackend.service.serviceI.ISuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SuplierService implements ISuplierService {
    @Autowired
    private SuplierRepository suplierRepository;
    @Override
    public List<Suplier> getAllsupliers() {
        return (List<Suplier>) suplierRepository.findAll();
    }

    @Override
    public Suplier getById(Long id) {
        return suplierRepository.findById(id).get();
    }
}
