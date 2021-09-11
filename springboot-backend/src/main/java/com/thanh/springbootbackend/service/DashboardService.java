package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.repository.InputRepository;
import com.thanh.springbootbackend.repository.OutInfoRepository;
import com.thanh.springbootbackend.repository.OutputRepository;
import com.thanh.springbootbackend.service.serviceI.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DashboardService
 * Version 1.0
 *
 * Date: 01-09-2021
 *
 * Copyright
 *
 * Modification Logs:
 *  DATE                 AUTHOR          DESCRIPTION
 *  -----------------------------------------------------------------------
 *   01-09-2021         ThanhNV80            Create
 */

@Service
public class DashboardService implements IDashboardService {
    @Autowired
    private OutputRepository outputRepository;
    @Autowired
    private OutInfoRepository outInfoRepository;
    @Autowired
    private InputRepository inputRepository;

    /**
     * sum total output
     */
    @Override
    public Double sumTotalOutput() {
        return outputRepository.totalByOutput();
    }

    /**
     * count perfume output
     */
    @Override
    public int countPerfumeOutput() {
        return outInfoRepository.countByPerfumeOutPut();
    }

    /**
     * count output
     */
    @Override
    public int countOutput() {
        return outputRepository.countOutput();
    }

    /**
     * profit
     */
    @Override
    public double profit() {
        return outInfoRepository.profit();
    }
}
