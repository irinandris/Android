package com.example.learningsharedpreference;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;

public class ListFragment extends Fragment {
    Button logout;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup contain, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.list_fragment, contain, false);
        logout = view.findViewById(R.id.logout);
        preferences = getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        editor = preferences.edit();
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                editor.putBoolean("LOGIN_STATUS", false);
                editor.commit();
                Intent intent = new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}
