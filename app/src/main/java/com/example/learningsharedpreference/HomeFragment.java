package com.example.learningsharedpreference;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learningsharedpreference.Product.AddProductActivity;
import com.example.learningsharedpreference.Product.CustomAdapter;
import com.example.learningsharedpreference.Product.ProductDbHelper;

public class HomeFragment extends Fragment {
    RecyclerView productList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        view.findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddProductActivity.class));
            }
        });
        productList = view.findViewById(R.id.productList);
        return view;
    }

//    @Override //ทำครั้งเดียว
//    public void onStart() {
//        super.onStart();
//        Toast.makeText(getContext(), "Start", Toast.LENGTH_SHORT).show();
//    }

    @Override
    public void onResume() { //
        super.onResume();
//        Toast.makeText(getContext(), "Resume", Toast.LENGTH_SHORT).show();
        ProductDbHelper dbHelper = new ProductDbHelper(getContext());
        CustomAdapter adapter = new CustomAdapter(dbHelper.getAll());
        LinearLayoutManager layoutManager = new LinearLayoutManager((getContext()));
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        productList.setLayoutManager(layoutManager);
        productList.setAdapter(adapter);
    }
}
