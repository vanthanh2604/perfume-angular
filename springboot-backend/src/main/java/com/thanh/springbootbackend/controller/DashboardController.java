package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.service.serviceI.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/")
public class DashboardController {
    @Autowired
    private IDashboardService dashboardService;

    @GetMapping("dashboard")
    public Map<String, Object> revenue(){
        Map<String,Object> map=new HashMap<>();
        try {
            double sum_total=dashboardService.sum_total_output();
            map.put("revenue",sum_total);
            int count_perfume_output=dashboardService.count_perfume_output();
            map.put("count_perfume",count_perfume_output);
            int count_output=dashboardService.count_output();
            map.put("count_output",count_output);
            double profit = dashboardService.profit();
            map.put("profit",profit);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",500);
        }
        return map;
    }

    @GetMapping("dashboard/countamount")
    public Map<String, Object> count_Perfume_Output(){
        Map<String,Object> map=new HashMap<>();
        try {
            int total=dashboardService.count_perfume_output();
            map.put("result",total);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",500);
        }
        return map;
    }
}
