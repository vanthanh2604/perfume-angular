package com.thanh.springbootbackend.model;

public class InputInfoModel {
    private String perfume_code;
    private int amount;
    private double price;
    private Long suplierId;

    public String getPerfume_code() {
        return perfume_code;
    }

    public void setPerfume_code(String perfume_code) {
        this.perfume_code = perfume_code;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Long getSuplierId() {
        return suplierId;
    }

    public void setSuplierId(Long suplierId) {
        this.suplierId = suplierId;
    }
}
