package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.service.serviceI.IStatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * StatisticalController
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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/")
public class StatisticalController {
    @Autowired
    private IStatisticalService statisticalService;

    /**
     * statistical
     */
    @GetMapping("statistical")
    public Map<String, Object> getStatistical(){
        return statisticalService.revenueByPerfume();
    }

    /**
     * statistical month
     */
    @GetMapping("statistical/month")
    public Map<String, Object> getStatisticalMonth(){
        return statisticalService.revenueByMonth();
    }

    /**
     * statistical inventory
     */
    @GetMapping("statistical/inventory")
    public Map<String, Object> getStatisticalInventory(){
        return statisticalService.inventoryStatistical();
    }
}
