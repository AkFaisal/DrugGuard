package com.example.drugguard.MyOrder.Model;

import com.example.drugguard.MyProduct.Model.Product_Model;

import java.util.ArrayList;

public class Order_Model {

    ArrayList <Product_Model>drugs;
    //Product_Model product_model;
    String  address;
    String  paymentType;
    public ArrayList<Product_Model> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<Product_Model> drugs) {
        this.drugs = drugs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
