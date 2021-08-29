package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.entity.Brand;
import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.service.serviceI.IBrandService;
import com.thanh.springbootbackend.service.serviceI.IPerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/")
public class PerfumeController {
    @Autowired
    private IPerfumeService perfumeService;
    @Autowired
    private IBrandService brandService;


    @GetMapping("perfumes")
    public List<Perfume> getAllPerfume(){
        return perfumeService.get_All_Perfume();
    }
    @GetMapping("perfumes/search")
    public List<Perfume> getSearchPerfume(@RequestParam String stringSearch ) {
        return perfumeService.search_Perfume(stringSearch);
    }
    @GetMapping("brand")
    public List<Brand> getAllBrand(){
        return brandService.get_All_Brand();
    }

    @PostMapping("perfume")
    public Map<String, Object> createPerfume(@Valid @RequestBody PerfumeModel perfumeModel, Errors e)
    {
        Map<String, Object> map = new HashMap<>();
        if(e.hasErrors()){
            for(int i = 0 ; i<e.getAllErrors().size() ; i++){
                map.put("msg" , e.getAllErrors().get(i).getDefaultMessage());
                return map;
            }
        }else{
            if(perfumeService.get_Perfume_by_Code(perfumeModel.getPerfume().getPerfume_code())!=null) {
                map.put("msg","Mã sản phẩm đã tồn tại!");
                map.put("status", false);
                return map;
            }
            else {
                perfumeService.create_Perfume(perfumeModel);
                map.put("msg","Thành công!");
                map.put("status", true);
                return map;
            }
        }return  map;
    }

    @GetMapping("perfume/{id}")
    public Map<String, Object>getPerfumeById(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        try{
            Perfume p=perfumeService.get_Perfume_by_Id_Flag(id);
            if (p == null) {
                map.put("msg", "Không tìm thấy sản phẩm này!");
                map.put("status", false);
                return map;
            }else{
                map.put("result",p);
                map.put("status", true);
            }
        }catch (Exception e){
            map.put("status",500);
        }

        return map;
    }
    @GetMapping("perfume-code/{code}")
    public Map<String, Object>getPerfumeByCode(@PathVariable String code) {
        Map<String, Object> map = new HashMap<>();
        try{
            Perfume p=perfumeService.get_Perfume_by_Code(code);
            if (p == null) {
                map.put("msg", "Không tìm thấy sản phẩm này!");
                map.put("status", false);
                return map;
            }else{
                map.put("result",p);
                map.put("status", true);
            }
        }catch (Exception e){
            map.put("status",500);
        }

        return map;
    }

    @PutMapping("perfume/{id}")
    public Map<String, Object> updatePerfume(@PathVariable Long id, @Valid @RequestBody PerfumeModel perfumeModel){
        Map<String, Object> map = new HashMap<>();
        try{
            Perfume perfume=perfumeService.get_Perfume_by_Id_Flag(id);
            if(perfume==null) {
                map.put("msg","Cập nhật thất bại! Do sản phẩm đã được xóa!");
                map.put("status", false);
                return map;
            }
            else {
                perfumeService.update_Perfume(id, perfumeModel);
                map.put("msg","Cập nhật thành công!");
                map.put("status", true);
            }
        }catch (Exception e){
           map.put("status",500);
        }
        return  map;
    }

    @DeleteMapping("perfume/{id}")
    public Map<String, Object> deletePerfume(@PathVariable Long id){
        Map<String, Object> map = new HashMap<>();
        try{
            Perfume perfume = perfumeService.get_Perfume_by_Id_Flag(id);
            if(perfume==null){
                map.put("msg","Sản phẩm đã được người khác xóa!");
                map.put("status",false);
                return map;
            }else {
                perfumeService.delete_Perfume(id);
                map.put("msg","Xóa thành công!");
                map.put("status",true);
                return map;
            }
        }catch (Exception ex){
            map.put("status",500);
        }
        return map;
    }
    @DeleteMapping("products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            int a=5;
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseEntity<String>("Lỗi", HttpStatus.CREATED));
        }
//        return ResponseEntity.ok(new ResponseEntity<String>("Delete", HttpStatus.OK));
        ResponseEntity<?> objectResponseEntity = new ResponseEntity<>(HttpStatus.CREATED);
        return objectResponseEntity;
    }

}
