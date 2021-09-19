package com.thanh.springbootbackend.service.serviceI;

/**
 * IDashboardService
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
public interface IDashboardService {

    /**
     * sum total output
     * @return
     */
    Double sumTotalOutput();

    /**
     * count perfume output
     * @return
     */
    int countPerfumeOutput();

    /**
     * count output
     * @return
     */
    int countOutput();

    /**
     * profit
     * @return
     */
    double profit();
}
