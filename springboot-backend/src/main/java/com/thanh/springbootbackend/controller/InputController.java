package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Input;
import com.thanh.springbootbackend.entity.InputInfo;
import com.thanh.springbootbackend.entity.Suplier;
import com.thanh.springbootbackend.service.PerfumeService;
import com.thanh.springbootbackend.service.serviceI.IInputInfoService;
import com.thanh.springbootbackend.service.serviceI.IInputService;
import com.thanh.springbootbackend.service.serviceI.ISuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

/**
 * InputController
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
public class InputController {
    @Autowired
    private IInputService inputService;
    @Autowired
    private IInputInfoService inputInfoService;
    @Autowired
    private ISuplierService suplierService;

    /**
     * get all inpur
     */
    @GetMapping("inputs")
    public List<Input> getAllInput() {
        return inputService.getALlInput();
    }

    /**
     * get input by id
     * @param id
     */
    @GetMapping("input/{id}")
    public Map<String, Object> getInpuId(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Input input = inputService.getById(id);
            if (input == null) {
                map.put("msg", "Lỗi");
                map.put("status", false);
            } else {
                map.put("result", input);
                map.put("status", 200);
            }
        } catch (Exception e) {
            map.put("status", 500);
        }
        return map;
    }

    /**
     * get input detail
     * @param id
     */
    @GetMapping("input-details/{id}")
    public Map<String, Object> getInputInfo(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<InputInfo> list = inputInfoService.getALlInputInfoByIdInput(id);
            map.put("result", list);
        } catch (Exception e) {
            map.put("status", false);
        }
        return map;
    }

    /**
     * get all suppliers
     */
    @GetMapping("supliers")
    public Map<String, Object> getAllSupplier() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Suplier> supliers = suplierService.getAllsupliers();
            map.put("status", 200);
            map.put("result", supliers);
        } catch (Exception e) {
            map.put("status", false);
        }
        return map;
    }

    /**
     * create input
     * @param list
     * @param id
     */
    @Transactional(rollbackFor = {SQLException.class})
    @PostMapping("input-create/{id}")
    public Map<String, Object> create(@RequestBody List<InputInfoModel> list, @PathVariable long id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Input input = inputService.createInput(id);//Tạo phiếu nhập
            inputInfoService.addInputInfo(list, input);// tạo chi tiết phiếu nhập
            map.put("result", list);
            map.put("status", 200);
        } catch (Exception ex) {
            map.put("status", 500);
        }
        return map;
    }
}
