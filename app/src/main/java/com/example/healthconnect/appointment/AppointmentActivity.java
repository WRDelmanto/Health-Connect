package com.example.healthconnect.appointment;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
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

import com.example.healthconnect.Appointment;
import com.example.healthconnect.R;
import com.example.healthconnect.currentAppointment.CurrentAppointmentActivity;

public class AppointmentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        statusBarIcon.setImageResource(R.drawable.calendar_icon);
        statusBarTitle.setText(getString(R.string.appointment));

        EditText patientNameInput = findViewById(R.id.appointment_activity_patient_name);
        AppCompatButton date = findViewById(R.id.appointment_activity_appointment_date);
        AppCompatButton time = findViewById(R.id.appointment_activity_appointment_time);
        AppCompatButton cancelAppointment = findViewById(R.id.appointment_activity_cancel_appointment);
        AppCompatButton startAppointment = findViewById(R.id.appointment_activity_start_appointment);
        AppCompatButton cancelButton = findViewById(R.id.appointment_activity_cancel_button);
        AppCompatButton saveButton = findViewById(R.id.appointment_activity_save_button);

        Appointment appointment = (Appointment) getIntent().getSerializableExtra("appointment");
        patientNameInput.setText(appointment != null ? appointment.getPatient().getName() : "");

        // TODO: Get the appointment date and time from the database or date (today) and time (now)
        date.setText(appointment != null ? appointment.getAppointmentDate() : "Today");
        time.setText(appointment != null ? appointment.getAppointmentTime() : "Now");

        boolean isNewAppointment = getIntent().getBooleanExtra("isNewAppointment", false);

        if (isNewAppointment) {
            cancelAppointment.setVisibility(GONE);
            cancelButton.setVisibility(VISIBLE);
        } else {
            cancelAppointment.setVisibility(VISIBLE);
            cancelButton.setVisibility(GONE);
        }

        date.setOnClickListener(v -> {
            // TODO: Show a date picker dialog
        });

        time.setOnClickListener(v -> {
            // TODO: Show a time picker dialog
        });

        cancelAppointment.setOnClickListener(v -> {
            // TODO: Open dialog to delete the appointment from the database
        });

        startAppointment.setOnClickListener(v -> {
            // TODO: Update the appointment info to the database
            String patientName = patientNameInput.getText().toString();

            Intent intent = new Intent(AppointmentActivity.this, CurrentAppointmentActivity.class);
            intent.putExtra("appointment", appointment);
            startActivity(intent);

            finish();
        });

        saveButton.setOnClickListener(v -> {
            // TODO: Save / Update the appointment to the database
            String patientName = patientNameInput.getText().toString();

            finish();
        });

        cancelButton.setOnClickListener(v -> {
            // TODO: Open dialog to cancel the appointment creation
        });
    }
}