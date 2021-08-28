package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.entity.Perfume;

import java.util.List;

public interface IPerfumeService {
    List<Perfume> get_All_Perfume();
    Perfume get_Perfume_by_Id(long id);
    Perfume get_Perfume_by_Id_Flag(long id);
    List<Perfume> search_Perfume(String searchTr);
    Perfume get_Perfume_by_Code(String code);
    void create_Perfume(PerfumeModel perfumeModel);
    void update_Perfume(Long id, PerfumeModel perfumeModel);
    void delete_Perfume(Long id);
}
