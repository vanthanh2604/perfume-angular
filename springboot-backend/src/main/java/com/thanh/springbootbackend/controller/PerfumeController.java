package com.thanh.springbootbackend.controller;

import com.thanh.springbootbackend.entity.Brand;
import com.thanh.springbootbackend.entity.Origin;
import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.service.serviceI.IBrandService;
import com.thanh.springbootbackend.service.serviceI.IOriginService;
import com.thanh.springbootbackend.service.serviceI.IPerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PerfumeController
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
public class PerfumeController {
    @Autowired
    private IPerfumeService perfumeService;
    @Autowired
    private IBrandService brandService;
    @Autowired
    private IOriginService originService;

    /**
     * get all perfume
     * @return
     */
    @GetMapping("perfumes")
    public List<Perfume> getAllPerfume() {
        return perfumeService.getAllPerfume();
    }

    /**
     * get all perfume stocking
     * @return
     */
    @GetMapping("perfumes/stocking")
    public List<Perfume> getAllPerfumeStocking() {
        return perfumeService.getAllPerfumeStocking();
    }

    /**
     * get all brand
     * @return
     */
    @GetMapping("brand")
    public List<Brand> getAllBrand() {
        return brandService.getAllBrand();
    }

    /**
     * get all origin
     * @return
     */
    @GetMapping("origin")
    public List<Origin> getAllOrigin() {
        return originService.getAll();
    }

    /**
     * create new perfume
     * @param perfumeModel
     * @param e
     * @return
     */
    @PostMapping("perfume")
    public Map<String, Object> createPerfume(@Valid @RequestBody PerfumeModel perfumeModel, Errors e) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (e.hasErrors()) {
                for (int i = 0; i < e.getAllErrors().size(); i++) {
                    map.put("msg", e.getAllErrors().get(i).getDefaultMessage());
                    return map;
                }
            } else {
                if (perfumeService.getPerfumeByName(perfumeModel.getPerfume().getPerfume_name()) != null) {
                    map.put("status", false);
                    return map;
                } else {
                    perfumeService.createPerfume(perfumeModel);
                    map.put("status", 200);
                    return map;
                }
            }
        } catch (NullPointerException ex) {
            map.put("status",500);
        }
        return map;
    }

    /**
     * get perfume by id
     * @param id
     * @return
     */
    @GetMapping("perfume/{id}")
    public Map<String, Object> getPerfumeById(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Perfume p = perfumeService.getPerfumeByIdFlag(id);
            if (p == null) {
                map.put("status", false);
                return map;
            } else {
                map.put("result", p);
                map.put("status", true);
            }
        } catch (Exception e) {
            map.put("status", 500);
        }
        return map;
    }

    /**
     * get perfume by code
     * @param id
     * @return
     */
    @GetMapping("perfume-code/{id}")
    public Map<String, Object> getPerfumeByCode(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Perfume p = perfumeService.getPerfumeByIdFlag(id);
            if (p == null) {
                map.put("status", false);
                return map;
            } else {
                map.put("result", p);
                map.put("status", true);
            }
        } catch (Exception e) {
            map.put("status", 500);
        }
        return map;
    }

    /**
     * get perfume by name
     * @param name
     * @return
     */
    @GetMapping("perfume-name/{name}")
    public Map<String, Object> getPerfumeByName(@PathVariable String name) {
        Map<String, Object> map = new HashMap<>();
        try {
            Perfume p = perfumeService.getPerfumeByName(name);
            if (p == null) {
                map.put("status", false);
                return map;
            } else {
                map.put("result", p);
                map.put("status", 200);
            }
        } catch (Exception e) {
            map.put("status", 500);
    }
    return map;

    }

    /**
     * update perfume
     * @param id
     * @param perfumeModel
     * @param e
     * @return
     */
    @PutMapping("perfume/{id}")
    public Map<String, Object> updatePerfume(@PathVariable String id, @Valid @RequestBody PerfumeModel perfumeModel,Errors e) {
        Map<String, Object> map = new HashMap<>();
        try {
            if (e.hasErrors()) {
                for (int i = 0; i < e.getAllErrors().size(); i++) {
                    map.put("msg", e.getAllErrors().get(i).getDefaultMessage());
                    return map;
                }
            } else {
                Perfume perfume = perfumeService.getPerfumeByIdFlag(id);
                if (perfume == null) {
                    map.put("status", false);
                    return map;
                } else {
                    perfumeService.updatePerfume(id, perfumeModel);
                    map.put("status", 200);
                }
            }
        } catch (Exception ex) {
            map.put("status", 500);
        }
        return map;
    }

    /**
     * delete perfume
     * @param id
     * @return
     */
    @DeleteMapping("perfume/{id}")
    public Map<String, Object> deletePerfume(@PathVariable String id) {
        Map<String, Object> map = new HashMap<>();
        try {
            Perfume perfume = perfumeService.getPerfumeByIdFlag(id);
            if (perfume == null) {
                map.put("status", false);
                return map;
            } else {
                perfumeService.deletePerfume(id);
                map.put("status", 200);
                return map;
            }
        } catch (Exception ex) {
            map.put("status", 500);
        }
        return map;
    }
}
