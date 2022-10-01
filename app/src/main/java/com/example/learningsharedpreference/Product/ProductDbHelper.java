package com.example.learningsharedpreference.Product;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.learningsharedpreference.Product.ProductDB.ProductEntry;

import java.util.ArrayList;

public class ProductDbHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" +
                    ProductEntry._ID + " INTEGER PRIMARY KEY, " +
                    ProductEntry.COLUMN_PRODUCT_NAME + " TEXT, " +
                    ProductEntry.COLUMN_PRODUCT_DETAIL + " TEXT, " +
                    ProductEntry.COLUMN_PRODUCT_PRICE + " REAL, " +
                    ProductEntry.COLUMN_PRODUCT_QUANTITY + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + ProductEntry.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATAbASE_NAME = "Product.db";
    public ProductDbHelper(Context context){
        super(context, DATAbASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
    public ArrayList<Product> getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                ProductEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Product> products = new ArrayList<>();
        while ((cursor.moveToNext())){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry._ID));
            String productName = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_NAME));
            String productDetail = cursor.getString(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_DETAIL));
            double productPrice = cursor.getDouble(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_PRICE));
            int productQuantity = cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry.COLUMN_PRODUCT_QUANTITY));
            Product product = new Product(id, productName, productDetail, productPrice, productQuantity);
            products.add(product);
        }
        cursor.close();
        db.close();

        return products;
    }
    public int update(int id, ContentValues product) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define 'where' part of query.
        String selection = ProductEntry._ID + " = ?"; // ? ต่อพารามิเตอร์
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        int count = db.update( // count แก้ไขและอัปเดตได้หลายแถว (count = 0 ไม่มีข้อมูล)
                ProductEntry.TABLE_NAME,
                product,
                selection,
                selectionArgs);
        return count;
    }
    public int delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Define 'where' part of query.
        String selection = ProductEntry._ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        int deletedRows = db.delete(ProductEntry.TABLE_NAME, selection, selectionArgs);
        return deletedRows;
    }
}
