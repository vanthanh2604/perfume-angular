package com.thanh.springbootbackend.service;

import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.entity.Brand;
import com.thanh.springbootbackend.entity.Perfume;
import com.thanh.springbootbackend.repository.BrandRepository;
import com.thanh.springbootbackend.repository.PerfumeRepositorry;
import com.thanh.springbootbackend.service.serviceI.IPerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PerfumeService implements IPerfumeService {
    @Autowired
    private PerfumeRepositorry perfumeRepositorry;
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Perfume> get_All_Perfume() {
        return perfumeRepositorry.findAllDesc();
    }

    @Override
    public Perfume get_Perfume_by_Id(long id) {
        return null;
    }

    @Override
    public Perfume get_Perfume_by_Id_Flag(long id) {
        return perfumeRepositorry.findPerfumeByIdAndFlag(id);
    }

    @Override
    public List<Perfume> search_Perfume(String searchTr) {
        return perfumeRepositorry.findByNameAndBrand(searchTr);
    }

    @Override
    public Perfume get_Perfume_by_Code(String code) {
        return perfumeRepositorry.findByCode(code);
    }

    @Override
    public void create_Perfume(PerfumeModel perfumeModel) {
        Perfume per = perfumeModel.getPerfume();
        per.setNgayNhap(new Date());
        per.setDeleteFlag(0);
        Brand br = brandRepository.findById(perfumeModel.getBrandId()).get();
        per.setBrand(br);
        perfumeRepositorry.save(per);
    }

    @Override
    public void update_Perfume(Long id, PerfumeModel perfumeModel) {
        Perfume perfume= perfumeRepositorry.findPerfumeByIdAndFlag(id);
        perfume.setPerfume_name(perfumeModel.getPerfume().getPerfume_name());
        perfume.setAmount(perfumeModel.getPerfume().getAmount());
        perfume.setPrice(perfumeModel.getPerfume().getPrice());
        perfume.setDescription(perfumeModel.getPerfume().getDescription());
        Brand br = brandRepository.findById(perfumeModel.getBrandId()).get();
        perfume.setBrand(br);
        perfumeRepositorry.save(perfume);
    }

    @Override
    public void delete_Perfume(Long id) {
        Perfume perfume=perfumeRepositorry.findPerfumeByIdAndFlag(id);
        perfume.setDeleteFlag(1);
        perfumeRepositorry.save(perfume);
    }
}
