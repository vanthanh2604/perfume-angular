package com.thanh.springbootbackend.service.serviceI;

import java.util.List;
import java.util.Map;

public interface IStatisticalService {
    //Doanh thu theo sản phẩm đã bán
    Map<String, Object> revenue_By_Perfume();
    //Doanh thu theo tháng
    Map<String, Object> revenue_By_Month();
    Map<String,Object> inventory_statistical();
}
