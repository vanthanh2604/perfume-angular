package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.repository.InputRepository;
import com.thanh.springbootbackend.repository.OutInfoRepository;
import com.thanh.springbootbackend.repository.OutputRepository;
import com.thanh.springbootbackend.service.serviceI.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService implements IDashboardService {
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private OutInfoRepository outInfoRepository;
    @Autowired
    private InputRepository inputRepository;
    @Override
    public Double sum_total_output() {
        return outputRepository.totalByOutput();
    }

    @Override
    public int count_perfume_output() {
        return outInfoRepository.countByPerfumeOutPut();
    }

    @Override
    public int count_output() {
        return outputRepository.countOutput();
    }

    @Override
    public double profit() {
        return outInfoRepository.profit();
    }
}
