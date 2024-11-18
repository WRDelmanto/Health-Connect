package com.example.healthconnect.patientProfile;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.annotation.SuppressLint;
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

import com.example.healthconnect.utils.database.Patient;
import com.example.healthconnect.R;

public class EditablePatientProfileActivity extends AppCompatActivity {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editable_patient_profile);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        // Setting the icon and text for the status bar
        statusBarIcon.setImageResource(R.drawable.patient_records_icon);
        statusBarTitle.setText(getString(R.string.patient_profile));

        ImageView patientPicture = findViewById(R.id.editable_patient_profile_activity_patient_picture);
        EditText patientName = findViewById(R.id.editable_patient_profile_activity_patient_name);
        EditText patientGender = findViewById(R.id.editable_patient_profile_activity_patient_gender);
        EditText patientEmail = findViewById(R.id.editable_patient_profile_activity_patient_email);
        EditText patientDateOfBirth = findViewById(R.id.editable_patient_profile_activity_patient_date_of_birth);
        EditText patientPhoneNumber = findViewById(R.id.editable_patient_profile_activity_patient_phone_number);
        EditText patientHeight = findViewById(R.id.editable_patient_profile_activity_patient_height);
        EditText patientWeight = findViewById(R.id.editable_patient_profile_activity_patient_weight);
        AppCompatButton saveButton = findViewById(R.id.editable_patient_profile_activity_save_button);

        Patient patient = (Patient) getIntent().getSerializableExtra("patient");
        patientName.setText(patient != null ? patient.getName() : "");
        patientGender.setText(patient != null ? patient.getGender() : "");
        patientEmail.setText(patient != null ? patient.getEmail() : "");
        patientDateOfBirth.setText(patient != null ? String.valueOf(patient.getAge()) : "");
        patientPhoneNumber.setText(patient != null ? patient.getPhoneNumber() : "");
        patientHeight.setText(patient != null ? String.valueOf(patient.getHeight()) : "");
        patientWeight.setText(patient != null ? String.valueOf(patient.getWeight()) : "");

        patientPicture.setImageResource(R.drawable.default_profile_picture);

        saveButton.setOnClickListener(v -> {
            // TODO: Save patient to the database
            finish();
        });
    }
}