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

import com.example.healthconnect.R;
import com.example.healthconnect.utils.database.Database;
import com.example.healthconnect.utils.database.Patient;

public class PatientProfileActivity extends AppCompatActivity {
    ImageView patientPicture;
    TextView patientName;
    TextView patientEmail;
    TextView patientPhoneNumber;
    TextView patientBirthDate;
    TextView patientHeight;
    TextView patientWeight;
    TextView patientGender;

    Patient patient;

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

        patientPicture = findViewById(R.id.patient_profile_activity_patient_picture);
        patientName = findViewById(R.id.patient_profile_activity_patient_name);
        patientEmail = findViewById(R.id.patient_profile_activity_patient_email);
        patientPhoneNumber = findViewById(R.id.patient_profile_activity_patient_phone_number);
        patientBirthDate = findViewById(R.id.patient_profile_activity_patient_birth_date);
        patientHeight = findViewById(R.id.patient_profile_activity_patient_height);
        patientWeight = findViewById(R.id.patient_profile_activity_patient_weight);
        patientGender = findViewById(R.id.patient_profile_activity_patient_gender);

        LinearLayout appointmentHistoryButton = findViewById(R.id.patient_profile_activity_patient_appointment_history_layout);

        patient = (Patient) getIntent().getSerializableExtra("patient");

        editButton.setOnClickListener(v -> {
            Intent intent = new Intent(PatientProfileActivity.this, EditablePatientProfileActivity.class);
            intent.putExtra("patient", patient);
            startActivity(intent);
        });

        appointmentHistoryButton.setOnClickListener(v -> {
            // TODO: Start the appointment history activity
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        patient = Database.getPatientById(patient.getId());

        patientPicture.setImageResource(R.drawable.default_profile_picture);
        patientName.setText(patient.getName());
        patientEmail.setText(patient.getEmail());
        patientPhoneNumber.setText(patient.getPhoneNumber());
        patientBirthDate.setText(patient.getAge() + " years-old");
        patientHeight.setText("Height\n" + String.format("%.2f", patient.getHeight()) + " cm");
        patientWeight.setText("Weight\n" + String.format("%.2f", patient.getWeight()) + " Kg");
        patientGender.setText("Gender\n" + patient.getGender());
    }
}