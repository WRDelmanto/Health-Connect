package com.example.healthconnect.patientProfile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthconnect.Patient;
import com.example.healthconnect.R;

public class PatientProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient_profile);
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

        ImageView editButton = findViewById(R.id.patient_profile_activity_edit_patient_info);

        ImageView patientPicture = findViewById(R.id.patient_profile_activity_patient_picture);
        TextView patientName = findViewById(R.id.patient_profile_activity_patient_name);
        TextView patientEmail = findViewById(R.id.patient_profile_activity_patient_email);
        TextView patientPhoneNumber = findViewById(R.id.patient_profile_activity_patient_phone_number);
        TextView patientBirthDate = findViewById(R.id.patient_profile_activity_patient_birth_date);
        TextView patientHeight = findViewById(R.id.patient_profile_activity_patient_height);
        TextView patientWeight = findViewById(R.id.patient_profile_activity_patient_weight);
        TextView patientGender = findViewById(R.id.patient_profile_activity_patient_gender);

        LinearLayout appointmentHistoryButton = findViewById(R.id.patient_profile_activity_patient_appointment_history_layout);

        Patient patient = (Patient) getIntent().getSerializableExtra("patient");
        patientPicture.setImageResource(R.drawable.default_profile_picture);
        patientName.setText(patient != null ? patient.getName() : "");
        patientEmail.setText(patient != null ? patient.getEmail() : "");
        patientPhoneNumber.setText(patient != null ? patient.getPhoneNumber() : "");
        patientBirthDate.setText(patient != null ? patient.getAge() + " years-old" : "");
        patientHeight.setText(patient != null ? "Height\n" + patient.getAge() + " cm" : "");
        patientWeight.setText(patient != null ? "Weight\n" + patient.getAge() + " Kg" : "");
        patientGender.setText(patient != null ? "Gender\n" + patient.getGender() : "");

        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(PatientProfileActivity.this, EditablePatientProfileActivity.class);
            intent.putExtra("patient", patient);
            startActivity(intent);
        });

        appointmentHistoryButton.setOnClickListener(v -> {
            // TODO: Start the appointment history activity
        });
    }
}