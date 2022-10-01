package com.example.learningsharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    Button register;
    EditText userName, password, confirmPassword;
    TextView backToLogin;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferences = getSharedPreferences("account", MODE_PRIVATE);
        editor = preferences.edit();

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        register = findViewById(R.id.register);
        backToLogin = findViewById(R.id.backToLogin);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _userName = userName.getText().toString();
                String _password = password.getText().toString();
                String _confirmPassword = confirmPassword.getText().toString();
                if(_password.equals(_confirmPassword) && _password.length() >= 8) {
                    saveAccount(_userName, _password);
                } else {
                    Toast.makeText(RegisterActivity.this, "re-enter password and confirm password", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    confirmPassword.setText("");
                }
            }
        });
        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void saveAccount(String userName, String password) {
        editor.putString("USERNAME", userName);
        editor.putString("PASSWORD", password);
        editor.putBoolean("LOGIN_STATUS", true);
        editor.commit();
        finish();
    }
}



