package com.example.learningsharedpreference.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learningsharedpreference.R;
public class AddProductActivity extends AppCompatActivity {
    EditText productName, productDetail, productPrice, productQuantity;
    TextView title;
    Button add, clear;
    Bundle bundle; // รับค่า intent
    Product product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        productName = findViewById(R.id.productName);
        productDetail = findViewById(R.id.productDetail);
        productPrice = findViewById(R.id.productPrice);
        productQuantity = findViewById(R.id.productQuantity);
        title = findViewById(R.id.title);
        add = findViewById(R.id.add);
        clear = findViewById(R.id.clear);

        bundle = getIntent().getExtras(); // เอาค่ามา
        if(bundle != null){ // check ว่ามีข้อมูลไหม
            getProduct();
            title.setText("แก้ไขข้อมูลสินค้า");
            // set value to edittext
            add.setText("แก้ไขข้อมูลสินค้า");
            productName.setText(product.getProductName());
            productDetail.setText(product.getProductDetail());
            productPrice.setText(String.valueOf(product.getProductPrice()));
            productQuantity.setText(String.valueOf(product.getProductQuantity()));
        }
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _productName = productName.getText().toString();
                String _productDetail= productDetail.getText().toString();
                String _productPrice = productPrice.getText().toString();
                String _productQuantity = productQuantity.getText().toString();
                if (
                        !_productName.equals("") &&
                                !_productDetail.equals("") &&
                                !_productPrice.equals("") &&
                                !_productQuantity.equals("")) {
                if(bundle != null){
                    ProductDbHelper dbHelper = new ProductDbHelper(getApplicationContext());
                    ContentValues values = new ContentValues();
                    // fet contentValue to new Data
                    values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_NAME, _productName);
                    values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_DETAIL, _productDetail);
                    values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_PRICE, _productPrice);
                    values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_QUANTITY, _productQuantity);
                    dbHelper.update(product.getId(), values);
                }else { // เพิ่มข้อมูล
                    AddProduct(
                            _productName,
                            _productDetail,
                            _productPrice,
                            _productQuantity);
                }
                    finish();

                } else {
                    Toast.makeText(AddProductActivity.this, "กรุณากรอกข้อมูลให้ครบถ้วน", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void AddProduct(String _productName, String _productDetail, String _productPrice, String _productQuantity){
        ProductDbHelper dbHelper = new ProductDbHelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

//        String _productName = productName.getText().toString();
//        String _productDetail = productDetail.getText().toString();
//        String _productPrice = productPrice.getText().toString();
//        String _productQuantity = productQuantity.getText().toString();

        ContentValues values = new ContentValues();

        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_NAME, _productName);
        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_DETAIL, _productDetail);
        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_PRICE, Double.parseDouble(_productPrice));
        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_QUANTITY, Integer.parseInt(_productQuantity));

        long newRowId = db.insert(ProductDB.ProductEntry.TABLE_NAME, null, values); // pk ใหม่ที่ได้มา

        Toast.makeText(this, "เพิ่มข้อมูลสินค้า (ID: " + newRowId + ") สำเร็จ", Toast.LENGTH_SHORT).show();
    }
    private void ContentValues(String _productName, String _productDetail, String _productPrice, String _productQuantity){
        ProductDbHelper dbHelper = new ProductDbHelper(getApplicationContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_NAME, _productName);
        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_DETAIL, _productDetail);
        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_PRICE, Double.parseDouble(_productPrice));
        values.put(ProductDB.ProductEntry.COLUMN_PRODUCT_QUANTITY, Integer.parseInt(_productQuantity));

        long newRowId = db.insert(ProductDB.ProductEntry.TABLE_NAME, null, values);

        Toast.makeText(this, "เพิ่มข้อมูลสินค้า (ID: " + newRowId + ") สำเร็จ", Toast.LENGTH_SHORT).show();
    }
    private void getProduct(){ // get ค่า เอาค่าที่รับมาเก็บไว้ในตัวแปร
        int id = bundle.getInt("id");
        String productName = bundle.getString("productName");
        String productDetail = bundle.getString("productDetail");
        double productPrice = bundle.getDouble("productPrice");
        int productQuantity = bundle.getInt("productQuantity");
        product = new Product(id, productName, productDetail, productPrice, productQuantity);
    }

}