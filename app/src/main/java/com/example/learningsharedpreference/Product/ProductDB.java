package com.example.learningsharedpreference.Product;

import android.provider.BaseColumns;

public class ProductDB {
    private ProductDB(){}
    public static class ProductEntry implements BaseColumns{
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_PRODUCT_NAME= "productName";
        public static final String COLUMN_PRODUCT_DETAIL= "productDetail";
        public static final String COLUMN_PRODUCT_PRICE = "productPrice";
        public static final String COLUMN_PRODUCT_QUANTITY = "productQuantity";
    }
}
