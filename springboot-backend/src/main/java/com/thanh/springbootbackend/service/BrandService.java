package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Brand;
import com.thanh.springbootbackend.repository.BrandRepository;
import com.thanh.springbootbackend.service.serviceI.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Override
    public List<Brand> get_All_Brand() {
        return (List<Brand>) brandRepository.findAll();
    }

    @Override
    public Brand get_Brand_by_Id(long id) {
        return brandRepository.findById(id).get();
    }
}
