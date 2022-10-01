package com.example.learningsharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button login;
    CheckBox rememberMe;
    EditText userName, password;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView goToRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = getSharedPreferences("account", MODE_PRIVATE);//เก็บค่าคนที่เข้ามา login
        editor = preferences.edit();
        if(preferences.getBoolean("LOGIN_STATUS", false)) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        userName = findViewById(R.id.userName); //ดึงค่าจาก xml มาใส่ในนี้
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        goToRegister = findViewById(R.id.goToRegister);
        rememberMe = findViewById(R.id.rememberMe);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userName = userName.getText().toString();
                String _password = password.getText().toString();
                boolean _rememberMe = rememberMe.isChecked();
                if(_userName.equals("") || _password.equals("")) {
                    Toast.makeText(
                            LoginActivity.this,
                            "Please enter all field!",
                            Toast.LENGTH_SHORT).show();
                } else {
                    login(_userName, _password, _rememberMe);
                }
            }
        });
        // เข้าสู่หน้าลงทะเบียน
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(
                        LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    //เข้าสู่หน้า login เมื่อลงทะเบียนเสร็จ
    private void login(String userName, String password, boolean rememberMe) {
        // if _rememberMe is true and (username and password correct)
        // save to SharedPreference (LOGIN_STATUS = true) and go to MainActivity
        String _userName = preferences.getString("USERNAME","");
        String _password = preferences.getString("PASSWORD","");
        if(rememberMe && userName.equals(_userName) && password.equals(_password)) {
            editor.putBoolean("LOGIN_STATUS", true);
            editor.commit();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if(!rememberMe && userName.equals(_userName) && password.equals(_password)) {
            editor.putBoolean("LOGIN_STATUS", false);
            editor.commit();
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
        } else {
            Toast.makeText(this, "Username or Password Incorrect", Toast.LENGTH_SHORT).show();
        }
    }
}


