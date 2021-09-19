package com.thanh.springbootbackend.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * InputInfo
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
public class InputInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Min(value = 0,message = "Số lượng sản phẩm không được âm")
    private int amount;
    @Min(value = 0,message = "Giá nhập không được âm")
    private double inputPrice;
    @ManyToOne()
    @JoinColumn(name = "inputid")
    private Input input;
    @ManyToOne()
    @JoinColumn(name = "perfume_id")
    private Perfume perfume;
    public InputInfo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getInputPrice() {
        return inputPrice;
    }

    public void setInputPrice(double inputPrice) {
        this.inputPrice = inputPrice;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Perfume getPerfume() {
        return perfume;
    }

    public void setPerfume(Perfume perfume) {
        this.perfume = perfume;
    }
}
