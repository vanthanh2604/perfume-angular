package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.repository.InputInfoRepository;
import com.thanh.springbootbackend.repository.OutInfoRepository;
import com.thanh.springbootbackend.service.serviceI.IStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StatisticalService
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
public class StatisticalService implements IStatisticalService {
    @Autowired
    private OutInfoRepository outInfoRepository;
    @Autowired
    private InputInfoRepository inputInfoRepository;

    /**
     * revenue by perfume
     * @return
     */
    @Override
    public Map<String, Object> revenueByPerfume() {
        Map<String, Object> map=new HashMap<>();
        try{
            List<?> list= outInfoRepository.revenue_by_perfume();
            map.put("result", list);
            map.put("status",200);
        }catch (Exception e){
            map.put("status", 500);
        }
        return map;
    }

    /**
     * revenue by month
     * @return
     */
    @Override
    public Map<String, Object> revenueByMonth() {
        Map<String, Object> map=new HashMap<>();
        try{
            List<?> list= outInfoRepository.revenue_by_month();
            map.put("result", list);
            map.put("status",200);
        }catch (Exception e){
            map.put("status", 500);
        }
        return map;
    }

    /**
     * inventory statistical
     * @return
     */
    @Override
    public Map<String, Object> inventoryStatistical() {
        Map<String, Object>map=new HashMap<>();
        List<Integer>list=new ArrayList<>();
        try {
            list.add(inputInfoRepository.sumByAmount());
            list.add(outInfoRepository.sumByAmount());
            map.put("result",list);
            map.put("status",200);
        }catch (Exception e) {
            map.put("status",500);
        }
        return map;
    }
}
