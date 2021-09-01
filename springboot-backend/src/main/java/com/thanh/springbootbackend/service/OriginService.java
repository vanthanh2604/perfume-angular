package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Origin;
import com.thanh.springbootbackend.repository.OriginRepository;
import com.thanh.springbootbackend.service.serviceI.IOriginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OriginService implements IOriginService {
    @Autowired
    private OriginRepository originRepository;
    @Override
    public List<Origin> get_All() {
        return (List<Origin>) originRepository.findAll();
    }
}
