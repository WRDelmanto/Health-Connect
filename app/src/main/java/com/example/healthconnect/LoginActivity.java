package com.example.healthconnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthconnect.home.HomeActivity;
import com.example.healthconnect.utils.FastSharedPreferences;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        AppCompatButton loginButton = findViewById(R.id.login_activity_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (checkLoginCredentials()){
                   startActivity(new Intent(LoginActivity.this, HomeActivity.class));
               } else {
                   Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    boolean checkLoginCredentials(){

        EditText loginEmailEDT = findViewById(R.id.login_activity_email);
        EditText loginPasswordEDT = findViewById(R.id.login_activity_password);

        String loginEmail = loginEmailEDT.getText().toString();
        String loginPassword = loginPasswordEDT.getText().toString();

        String doctorEmail = (String) FastSharedPreferences.get(this, "doctor_email", "");
        String doctorPassword = (String) FastSharedPreferences.get(this, "doctor_password", "");

        return  (doctorEmail.equalsIgnoreCase(loginEmail) && doctorPassword.equals(loginPassword));

    }
}