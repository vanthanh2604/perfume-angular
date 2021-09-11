package com.thanh.springbootbackend.service.serviceI;

import java.util.List;
import java.util.Map;

/**
 * IStatisticalService
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

public interface IStatisticalService {
    //Doanh thu theo sản phẩm đã bán
    Map<String, Object> revenueByPerfume();
    //Doanh thu theo tháng
    Map<String, Object> revenueByMonth();
    Map<String,Object> inventoryStatistical();
}
