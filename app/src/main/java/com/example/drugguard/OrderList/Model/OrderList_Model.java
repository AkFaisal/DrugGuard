package com.example.drugguard.OrderList.Model;

public class OrderList_Model {


    String status;
    String _id;
    String createdAt;
    String dar;


//
   int totalCash;
  // int pos,totalCash,productQuantity;
//    private Integer stocks;


    public OrderList_Model() {
    }

    public OrderList_Model(String status, String _id, String createdAt, String dar, int totalCash) {
        this.status = status;
        this._id = _id;
        this.createdAt = createdAt;
        this.dar = dar;
        this.totalCash = totalCash;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDar() {
        return dar;
    }

    public void setDar(String dar) {
        this.dar = dar;
    }

    //
//    public String getName() {
//        return drugName;
//    }
//
//    public String getPrice() {
//        return price;
//    }
//
//    public String getImg() {
//        return image;
//    }
//
//    public void setName(String drugName) {
//        this.drugName = drugName;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public void setImg(String image) {
//        this.image = image;
//    }
//
//
//    //mycart.......
//    public int getImagePath() {
//        return imagePath;
//    }
//
//    public void setImagePath(int imagePath) {
//        this.imagePath = imagePath;
//    }
//
    public int getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(int totalCash) {
        this.totalCash = totalCash;
    }
//
//    public int getProductQuantity() {
//        return productQuantity;
//    }
//
//    public void setProductQuantity(int productQuantity) {
//        this.productQuantity = productQuantity;
//    }
//
//    public int getPos() {
//        return pos;
//    }
//
//    public void setPos(int pos) {
//        this.pos = pos;
//    }
//
//    public Integer getStocks() {
//        return stocks;
//    }
//
//    public void setStocks(Integer stocks) {
//        this.stocks = stocks;
//    }
}
