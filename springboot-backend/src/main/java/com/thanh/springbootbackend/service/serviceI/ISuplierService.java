package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Suplier;

import java.util.List;

public interface ISuplierService {
    List<Suplier> getAllsupliers();
    Suplier getById(Long id);
}
