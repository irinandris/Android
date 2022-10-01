package com.example.learningsharedpreference.Product;

public class Product {
    private int id;
    private String productName;
    private String ProductDetail;
    private double productPrice;
    private int productQuantity;

    public Product() {
    }

    public Product(int id, String productName, String productDetail, double productPrice, int productQuantity) {
        this.id = id;
        this.productName = productName;
        ProductDetail = productDetail;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetail() {
        return ProductDetail;
    }

    public void setProductDetail(String productDetail) {
        ProductDetail = productDetail;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

}
