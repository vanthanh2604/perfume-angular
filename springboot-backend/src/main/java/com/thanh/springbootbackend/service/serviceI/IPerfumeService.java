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

    /**
     * get all perfume
     * @return
     */
    List<Perfume> getAllPerfume();

    /**
     * get all perfume stocking
     * @return
     */
    List<Perfume> getAllPerfumeStocking();

    /**
     * get perfume by id flag
     * @param id
     * @return
     */
    Perfume getPerfumeByIdFlag(String id);

    /**
     * get perfume by name
     * @param perfume_name
     * @return
     */
    Perfume getPerfumeByName(String perfume_name);

    /**
     * create perfume
     * @param perfumeModel
     */
    void createPerfume(PerfumeModel perfumeModel);

    /**
     * update perfume
     * @param id
     * @param perfumeModel
     */
    void updatePerfume(String id, PerfumeModel perfumeModel);

    /**
     * delete perfume by id
     * @param id
     */
    void deletePerfume(String id);
}
