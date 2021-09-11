package com.thanh.springbootbackend.service.serviceI;

import com.thanh.springbootbackend.model.PerfumeModel;
import com.thanh.springbootbackend.entity.Perfume;

import java.util.List;

/**
 * IPerfumeService
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

public interface IPerfumeService {
    List<Perfume> getAllPerfume();
    List<Perfume> getAllPerfumeStocking();
    Perfume getPerfumeByIdFlag(String id);
    Perfume getPerfumeByName(String perfume_name);
    void createPerfume(PerfumeModel perfumeModel);
    void updatePerfume(String id, PerfumeModel perfumeModel);
    void deletePerfume(String id);
}
