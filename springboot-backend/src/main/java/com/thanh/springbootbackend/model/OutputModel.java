package com.thanh.springbootbackend.model;

import java.util.List;

public class OutputModel {
    private String customerName;
    private String phone;
    private String address;
    private List<InputInfoModel> outputinfo;

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

    public List<InputInfoModel> getOutputinfo() {
        return outputinfo;
    }

    public void setOutputinfo(List<InputInfoModel> outputinfo) {
        this.outputinfo = outputinfo;
    }
}
