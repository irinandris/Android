package com.example.learningsharedpreference.Product;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningsharedpreference.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<Product> products;

    public CustomAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.getProductName().setText(product.getProductName());
        holder.getProductDetail().setText(product.getProductDetail());
        holder.getProductPrice().setText(String.valueOf(product.getProductPrice()));
        holder.getProductQuantity().setText(String.valueOf(product.getProductQuantity()));
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddProductActivity.class);
                intent.putExtra("id", product.getId());
                intent.putExtra("productName", product.getProductName());
                intent.putExtra("productDetail", product.getProductDetail());
                intent.putExtra("productPrice", product.getProductPrice());
                intent.putExtra("productQuantity", product.getProductQuantity());
                view.getContext().startActivity(intent);
            }
        });
        holder.getView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ProductDbHelper dbHelper = new ProductDbHelper(view.getContext());
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext()); // ใช้ context ที่ถูกส่งมา
                builder
                        .setTitle("คำเตือน!")
                        .setMessage("คุณต้องการลบข้อมูลหรือไม่?")
                        .setNegativeButton("ไม่ใช่", new DialogInterface.OnClickListener() { // ปุ่มยกเลิก
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // statement to delete data
                                products.remove(position);
                                notifyItemRemoved(position);
                                dbHelper.delete(product.getId());
                                ProductDbHelper dbHelper = new ProductDbHelper(view.getContext());
                            }
                        })
                        .setNeutralButton("แก้ไขข้อมูล", new DialogInterface.OnClickListener() { // ปุ่มแบบเด้งให้แสดง
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // start activity to edit data
                                Intent intent = new Intent(view.getContext(), AddProductActivity.class);
                                intent.putExtra("id", product.getId());
                                intent.putExtra("productName", product.getProductName());
                                intent.putExtra("productDetail", product.getProductDetail());
                                intent.putExtra("productPrice", product.getProductPrice());
                                intent.putExtra("productQuantity", product.getProductQuantity());
                                view.getContext().startActivity(intent);
                            }
                        }).show();
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView productName;
        private final TextView productDetail;
        private final TextView productPrice;
        private final TextView productQuantity;
        private View view;
        public ViewHolder(View view){
            super(view);//ส่งค่าให้ class ที่ extend มา
            productName = view.findViewById(R.id.productName);
            productDetail = view.findViewById(R.id.productDetail);
            productPrice = view.findViewById(R.id.productPrice);
            productQuantity = view.findViewById(R.id.productQuantity);
            this.view = view;
        }

        public TextView getProductName() {
            return productName;
        }

        public TextView getProductDetail() {
            return productDetail;
        }

        public TextView getProductPrice() {
            return productPrice;
        }

        public TextView getProductQuantity() { return productQuantity; }

        public View getView() {
            return view;
        }
    }
}
