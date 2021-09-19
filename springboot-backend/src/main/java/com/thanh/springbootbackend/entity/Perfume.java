package com.thanh.springbootbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * Perfume
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
@Table(name = "perfume",uniqueConstraints=
@UniqueConstraint(columnNames={"perfume_name"}))
public class Perfume {
    @Id
    @GeneratedValue(generator = "my_generator")
    @GenericGenerator(name = "my_generator", strategy = "com.thanh.springbootbackend.MyGenerator")
    private String id;
    @NotBlank(message = "Tên sản phẩm Không được để trống")
    @Size(max = 50, message = "Không được quá 50 ký tự")
    private String perfume_name;
    @Min(value = 0,message = "Giá sản phẩm không được âm")
    private double price;
    @Min(value = 0,message = "Số lượng sản phẩm không được âm")
    private int amount;
    @Size(max = 255,message = "không được quá 255 kí tụ")
    private String description;
    @ColumnDefault("0")
    private int deleteFlag;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date ngayNhap;
    @ManyToOne()
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne()
    @JoinColumn(name = "origin_id")
    private Origin origin;
    @OneToMany(mappedBy = "perfume")
    @JsonIgnore
    List<InputInfo> inputInfoList;
    @OneToMany(mappedBy = "perfume")
    @JsonIgnore
    List<OutputInfo>outputInfoList;

    public Perfume() {
    }
    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public List<InputInfo> getInputInfoList() {
        return inputInfoList;
    }

    public void setInputInfoList(List<InputInfo> inputInfoList) {
        this.inputInfoList = inputInfoList;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public int getAmount() {
        return amount;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPerfume_name() {
        return perfume_name;
    }

    public void setPerfume_name(String perfume_name) {
        this.perfume_name = perfume_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OutputInfo> getOutputInfoList() {
        return outputInfoList;
    }

    public void setOutputInfoList(List<OutputInfo> outputInfoList) {
        this.outputInfoList = outputInfoList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
