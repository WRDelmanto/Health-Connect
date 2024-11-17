package com.example.healthconnect.doctorprofile;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthconnect.R;
import com.example.healthconnect.utils.FastSharedPreferences;

public class DoctorProfileActivity extends AppCompatActivity {
    String doctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        // Setting the icon and text for the status bar
        statusBarIcon.setImageResource(R.drawable.doctor_profile_icon);
        statusBarTitle.setText(getString(R.string.doctor_profile_activity_doctor_profile));

        ImageView doctorPicture = findViewById(R.id.doctor_profile_activity_doctor_picture);
        EditText doctorNameInput = findViewById(R.id.doctor_profile_activity_doctor_name);
        AppCompatButton saveButton = findViewById(R.id.doctor_profile_activity_save_button);

        doctorPicture.setImageResource(R.drawable.default_profile_picture);

        // Load doctor name from shared preferences
        doctorName = (String) FastSharedPreferences.get(this, "doctor_name", "");
        doctorNameInput.setText(doctorName);

        saveButton.setOnClickListener(v -> {
            // TODO: Save picture to database

            doctorName = doctorNameInput.getText().toString();
            FastSharedPreferences.put(this, "doctor_name", doctorName);

            // Exit to home screen
            finish();
        });
    }
}