package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.entity.OutputInfo;
import com.thanh.springbootbackend.model.OutputModel;
import com.thanh.springbootbackend.service.serviceI.IOutputInfoService;
import com.thanh.springbootbackend.service.serviceI.IOutputService;
import com.thanh.springbootbackend.service.serviceI.IPerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * OutController
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
public class OutController {
    @Autowired
    private IOutputService outputService;
    @Autowired
    private IOutputInfoService outputInfoService;

    /**
     * get all outputs
     */
    @GetMapping("outputs")
    public List<Output> getAllInput() {
        return outputService.getAllOutput();
    }

    /**
     * get output by id
     * @param id
     */
    @GetMapping("output/{id}")
    public Map<String, Object> getOutput(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        Output op = outputService.getById(id);
        if (op == null) {
            map.put("msg", "Lỗi");
            map.put("status", 500);
        } else {
            map.put("result", op);
            map.put("status", 200);
        }
        return map;
    }

    /**
     * get output detail
     * @param id
     */
    @GetMapping("output-details/{id}")
    public Map<String, Object> getOutputDetails(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        List<OutputInfo> ops = outputInfoService.getALlOutputInfoByIdInput(id);
        map.put("result", ops);
        map.put("status", 200);
        return map;
    }

    /**
     * create output
     * @param outputModel
     */
    @PostMapping("output")
    public Map<String, Object> create(@RequestBody OutputModel outputModel) {
        Map<String, Object> map = new HashMap<>();
        try{
            if(outputModel.getCustomerName()==""|outputModel.getPhone()==""){
                map.put("status",400);
            }else {
                Output out = outputService.createOutput(outputModel);
                outputInfoService.addOutputInfo(outputModel.getOutputinfo(), out);
                map.put("status", 200);
                map.put("output", out);
            }
        }catch (Exception e){
            map.put("status", 500);
        }
        return map;
    }
}
