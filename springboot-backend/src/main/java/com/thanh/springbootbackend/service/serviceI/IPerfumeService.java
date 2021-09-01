package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.entity.Perfume;

import java.util.List;

public interface IPerfumeService {
    List<Perfume> get_All_Perfume();
    Perfume get_Perfume_by_Id_Flag(String id);
    Perfume get_Perfume_by_Name(String perfume_name);
    void create_Perfume(PerfumeModel perfumeModel);
    void update_Perfume(String id, PerfumeModel perfumeModel);
    void delete_Perfume(String id);
}
