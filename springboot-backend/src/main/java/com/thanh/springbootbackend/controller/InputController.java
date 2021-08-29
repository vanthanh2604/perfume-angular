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
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @Autowired
    private PerfumeService perfumeService;

    @GetMapping("inputs")
    public List<Input>getAllInput(){
        return inputService.get_ALl_Input();
    }

    @GetMapping("input/{id}")
    public Map<String,Object>getInpuId(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        try{
            Input input = inputService.getById(id);
            if(input==null){
                map.put("msg","Lỗi");
                map.put("status",false);
            }
            else {
                map.put("result", input);
                map.put("status",200);
            }
        }catch (Exception e){
            map.put("status",500);
        }

        return map;
    }

    @GetMapping("input-details/{id}")
    public Map<String,Object> getInputInfo(@PathVariable Long id){
        Map<String,Object> map=new HashMap<>();
        List<InputInfo> list=inputInfoService.get_ALl_InputInfo_by_idInput(id);
        map.put("result",list);
        return map;
    }

    @GetMapping("supliers")
    public Map<String,Object>getAllSuplier(){
        Map<String,Object>map =new HashMap<>();
        List<Suplier>supliers=suplierService.getAllsupliers();
        map.put("result",supliers);
        return map;
    }

    @PostMapping("input-create/{id}")
    public Map<String,Object>create(@RequestBody List<InputInfoModel> list, @PathVariable long id){
        Map<String,Object>map=new HashMap<>();
        try {
            Input input=inputService.createInput(id);
            inputInfoService.addInputInfo(list,input);
            map.put("result",list);
            map.put("status",200);
        } catch (Exception ex) {
            map.put("status",500);
        }
        return map;
    }
}
