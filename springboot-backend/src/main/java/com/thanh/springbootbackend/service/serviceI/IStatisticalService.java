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

    /**
     * revenue by perfume
     * @return
     */
    Map<String, Object> revenueByPerfume();

    /**
     * revenue by month
     * @return
     */
    Map<String, Object> revenueByMonth();

    /**
     * inventory statistical
     * @return
     */
    Map<String,Object> inventoryStatistical();
}
