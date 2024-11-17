package com.example.healthconnect.currentappointment;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthconnect.Appointment;
import com.example.healthconnect.R;

public class CurrentAppointmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_current_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        statusBarIcon.setImageResource(R.drawable.current_appointment_icon);
        statusBarTitle.setText(getString(R.string.current_appointment));

        ConstraintLayout patientInfo = findViewById(R.id.current_appointment_activity_patient_info_layout);
        ImageView patientPicture = findViewById(R.id.current_appointment_activity_appointment_item_image);
        TextView patientName = findViewById(R.id.current_appointment_activity_appointment_item_patient_name);
        TextView patientAge = findViewById(R.id.current_appointment_activity_appointment_item_patient_age);
        TextView appointmentType = findViewById(R.id.current_appointment_activity_appointment_item_appointment_type);
        TextView appointmentTime = findViewById(R.id.current_appointment_activity_appointment_item_appointment_time);
        EditText notes = findViewById(R.id.current_appointment_activity_notes);
        EditText medicines = findViewById(R.id.current_appointment_activity_medicines);
        EditText exams = findViewById(R.id.current_appointment_activity_exams);
        AppCompatButton save = findViewById(R.id.current_appointment_activity_save_button);

        Appointment appointment = (Appointment) getIntent().getSerializableExtra("appointment");
        patientPicture.setImageResource(R.drawable.default_profile_picture);
        patientName.setText(appointment != null ? appointment.getPatient().getName() : "");
        patientAge.setText(appointment != null ? appointment.getPatient().getAge() + " years-old" : "");
        appointmentType.setText(appointment != null ? appointment.getAppointmentType() : "");
        appointmentTime.setText(appointment != null ? appointment.getAppointmentTime() : "");
        notes.setText(appointment != null ? appointment.getNotes() : "");
        medicines.setText(appointment != null ? appointment.getMedicines() : "");
        exams.setText(appointment != null ? appointment.getExams() : "");

        patientInfo.setOnClickListener(v -> {
            // TODO: Open patient activity
        });

        save.setOnClickListener(v -> {
            // TODO: Save to database
            // TODO: Update appointment to database (isDone = true)

            finish();
        });
    }
}