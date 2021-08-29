package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.service.serviceI.IStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/")
public class StatisticalController {
    @Autowired
    private IStatisticalService statisticalService;

    @GetMapping("statistical")
    public Map<String, Object> getStatistical(){
        return statisticalService.revenue_By_Perfume();
    }

    @GetMapping("statistical/month")
    public Map<String, Object> getStatisticalMonth(){
        return statisticalService.revenue_By_Month();
    }

    @GetMapping("statistical/inventory")
    public Map<String, Object> getStatisticalInventory(){
        return statisticalService.inventory_statistical();
    }
}
