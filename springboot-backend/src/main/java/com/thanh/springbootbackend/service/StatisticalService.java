package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.model.RevenuePerfumeModel;
import com.thanh.springbootbackend.repository.OutInfoRepository;
import com.thanh.springbootbackend.service.serviceI.IStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticalService implements IStatisticalService {
    @Autowired
    private OutInfoRepository outInfoRepository;
    // Doanh thu, lợi nhuận theo sản phẩm
    @Override
    public Map<String, Object> revenue_By_Perfume() {
        Map<String, Object> map=new HashMap<>();
        try{
            List<?> list= outInfoRepository.revenue_by_perfume();
            map.put("result", list);
            map.put("status",200);
        }catch (Exception e){
            map.put("status", 400);
        }
        return map;
    }

    @Override
    public Map<String, Object> revenue_By_Month() {
        Map<String, Object> map=new HashMap<>();
        try{
            List<?> list= outInfoRepository.revenue_by_month();
            map.put("result", list);
            map.put("status",200);
        }catch (Exception e){
            map.put("status", 400);
        }
        return map;
    }
}
