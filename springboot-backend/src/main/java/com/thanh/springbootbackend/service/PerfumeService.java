package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.entity.Origin;
import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.entity.Brand;
import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.repository.BrandRepository;
import com.thanh.springbootbackend.repository.OriginRepository;
import com.thanh.springbootbackend.repository.PerfumeRepositorry;
import com.thanh.springbootbackend.service.serviceI.IPerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * PerfumeService
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

@Service
public class PerfumeService implements IPerfumeService {
    @Autowired
    private PerfumeRepositorry perfumeRepositorry;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private OriginRepository originRepository;
    @Override
    public List<Perfume> getAllPerfume() {
        return perfumeRepositorry.findAllDesc();
    }

    @Override
    public List<Perfume> getAllPerfumeStocking() {
        return perfumeRepositorry.findAllPerfumeStocking();
    }

    @Override
    public Perfume getPerfumeByIdFlag(String id) {
        return perfumeRepositorry.findPerfumeByIdAndFlag(id);
    }
    @Override
    public Perfume getPerfumeByName(String name) {
        return perfumeRepositorry.findByName(name);
    }

    @Override
    public void createPerfume(PerfumeModel perfumeModel) {
        Perfume per = perfumeModel.getPerfume();
        per.setNgayNhap(new Date());
        per.setDeleteFlag(0);
        Brand br = brandRepository.findById(perfumeModel.getBrandId()).get();
        per.setBrand(br);
        Origin origin=originRepository.findById(perfumeModel.getOriginId()).get();
        per.setOrigin(origin);
        perfumeRepositorry.save(per);
    }

    @Override
    public void updatePerfume(String id, PerfumeModel perfumeModel) {
        Perfume perfume= perfumeRepositorry.findPerfumeByIdAndFlag(id);
        perfume.setPerfume_name(perfumeModel.getPerfume().getPerfume_name());
        perfume.setDescription(perfumeModel.getPerfume().getDescription());
        Brand br = brandRepository.findById(perfumeModel.getBrandId()).get();
        Origin origin=originRepository.findById(perfumeModel.getOriginId()).get();
        perfume.setBrand(br);
        perfume.setOrigin(origin);
        perfumeRepositorry.save(perfume);
    }

    @Override
    public void deletePerfume(String id) {
        Perfume perfume=perfumeRepositorry.findPerfumeByIdAndFlag(id);
        perfume.setDeleteFlag(1);
        perfumeRepositorry.save(perfume);
    }
}
