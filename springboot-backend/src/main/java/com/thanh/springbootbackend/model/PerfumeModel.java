package com.thanh.springbootbackend.model;

import com.thanh.springbootbackend.entity.Perfume;

import javax.validation.Valid;

/**
 * PerfumeModel
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
public class PerfumeModel {
    @Valid
    private Perfume perfume;
    private long brandId;
    private long originId;


    public Perfume getPerfume() {
        return perfume;
    }

    public long getOriginId() {
        return originId;
    }

    public void setOriginId(long originId) {
        this.originId = originId;
    }

    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }
}
