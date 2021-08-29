package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.model.InputInfoModel;
import com.thanh.springbootbackend.entity.Output;
import com.thanh.springbootbackend.entity.OutputInfo;
import com.thanh.springbootbackend.service.serviceI.IOutputInfoService;
import com.thanh.springbootbackend.service.serviceI.IOutputService;
import com.thanh.springbootbackend.service.serviceI.IPerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/")
public class OutController {
    @Autowired
    private IOutputService outputService;
    @Autowired
    private IOutputInfoService outputInfoService;
    @Autowired
    private IPerfumeService perfumeService;
    @GetMapping("outputs")
    public List<Output> getAllInput(){
        return outputService.get_All_Output();
    }

    @GetMapping("output/{id}")
    public Map<String, Object>get_Output(@PathVariable Long id){
        Map<String, Object> map=new HashMap<>();
        Output op=outputService.getById(id);
        if(op==null){
            map.put("msg","Lỗi");
            map.put("status", 500);
        }else{map.put("result",op);
            map.put("status",200);
        }return map;
    }

    @GetMapping("output-details/{id}")
    public Map<String, Object>getInputDetails(@PathVariable Long id){
        Map<String,Object>map=new HashMap<>();
        List<OutputInfo> ops= outputInfoService.get_ALl_OutputInfo_by_idInput(id);
        map.put("result",ops);
        map.put("status",200);
        return map;
    }

    @PostMapping("output")
    public Map<String, Object>createOutput(@RequestBody Output output){
        Map<String, Object>map=new HashMap<>();
        Output out=outputService.createOutput(output);
        map.put("status",200);
        map.put("output",out);
        return map;
    }

    @PostMapping("outputInfo/{idOp}")
    public Map<String, Object>createInputInfo(@RequestBody List<InputInfoModel> infoDto, @PathVariable Long idOp){
        Map<String,Object>map=new HashMap<>();
        Output output= outputService.getById(idOp);
        //=====Thêm sản phẩm vào outputInfo============
        outputInfoService.addOutputInfo(infoDto,output);
        map.put("status",200);
        return map;
    }
}
