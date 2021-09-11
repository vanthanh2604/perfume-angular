package com.thanh.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * Output
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

@Entity
public class Output {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Tên khách hàng không được để trống")
    private String customerName;
    private String phone;
    private String address;
    private java.util.Date dateOutput;
    private double total;
    @ColumnDefault("0")
    private int deleteFlag;
    @OneToMany(mappedBy = "output")
    @JsonIgnore
    List<OutputInfo>outputInfos;
    public Output() {
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateOutput() {
        return dateOutput;
    }

    public void setDateOutput(Date dateOutput) {
        this.dateOutput = dateOutput;
    }

    public List<OutputInfo> getOutputInfos() {
        return outputInfos;
    }

    public void setOutputInfos(List<OutputInfo> outputInfos) {
        this.outputInfos = outputInfos;
    }
}
