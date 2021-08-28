package com.thanh.springbootbackend.model;

public class RevenuePerfumeModel {
    private String perfume_code;
    private String perfume_name;
    private Double revenue;
    private Double profit;
    private int amount;

    public String getPerfume_code() {
        return perfume_code;
    }

    public void setPerfume_code(String perfume_code) {
        this.perfume_code = perfume_code;
    }

    public String getPerfume_name() {
        return perfume_name;
    }

    public void setPerfume_name(String perfume_name) {
        this.perfume_name = perfume_name;
    }

    public Double getRevenue() {
        return revenue;
    }

    public void setRevenue(Double revenue) {
        this.revenue = revenue;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
