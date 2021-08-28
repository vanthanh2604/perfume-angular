package com.thanh.springbootbackend.model;

import com.thanh.springbootbackend.entity.Perfume;

import javax.validation.Valid;

public class PerfumeModel {
    @Valid
    private Perfume perfume;
    private long brandId;


    public Perfume getPerfume() {
        return perfume;
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
