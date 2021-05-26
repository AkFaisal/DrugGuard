package com.example.drugguard.MyProduct.Model;

public class Product_Model {


    String drugName;
    String price;
    String image;

    //.......mycart
   // public int imagePath;
    int totalCash,productQuantity;
    private Integer stocks;


    public Product_Model() {
    }

    public Product_Model(String drugName, String price, String image) {
        this.drugName = drugName;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return drugName;
    }

    public String getPrice() {
        return price;
    }

    public String getImg() {
        return image;
    }

    public void setName(String drugName) {
        this.drugName = drugName;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImg(String image) {
        this.image = image;
    }


    //mycart.......
//    public int getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(int imagePath) {
//        this.imagePath = imagePath;
//    }

    public int getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }


    public Integer getStocks() {
        return stocks;
    }

}
