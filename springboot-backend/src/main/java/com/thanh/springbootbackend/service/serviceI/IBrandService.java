package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.entity.Brand;

import java.util.List;

public interface IBrandService {
    List<Brand> get_All_Brand();
    Brand get_Brand_by_Id(long id);
}
