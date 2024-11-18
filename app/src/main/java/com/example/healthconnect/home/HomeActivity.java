package com.example.healthconnect.home;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static com.example.healthconnect.utils.database.MockAppointments.getMockAppointments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.utils.database.Appointment;
import com.example.healthconnect.R;
import com.example.healthconnect.appointment.AppointmentActivity;
import com.example.healthconnect.appointmentHistory.AppointmentHistoryActivity;
import com.example.healthconnect.appointmentScheduling.AppointmentSchedulingActivity;
import com.example.healthconnect.doctorProfile.DoctorProfileActivity;
import com.example.healthconnect.patientRecords.PatientRecordsActivity;
import com.example.healthconnect.utils.FastSharedPreferences;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeActivityAppointmentsAdapter.OnItemClickListener {
    ImageView doctorImage;
    TextView doctorName;
    TextView appointmentsCounter;
    TextView nextAppointment;

    List<Appointment> appointments;

    @SuppressLint({"SetTextI18n", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Summary
        doctorImage = findViewById(R.id.home_activity_doctor_picture);
        ImageButton editButton = findViewById(R.id.home_activity_edit_doctor_info);
        doctorName = findViewById(R.id.home_activity_doctor_name);
        appointmentsCounter = findViewById(R.id.home_activity_doctor_appointments_counter);
        nextAppointment = findViewById(R.id.home_activity_doctor_next_appointment);

        // Main Categories
        LinearLayout appointmentsScheduling = findViewById(R.id.home_activity_appointments_scheduling_layout);
        LinearLayout patientsRecords = findViewById(R.id.home_activity_patients_records_layout);
        LinearLayout appointmentHistory = findViewById(R.id.home_activity_appointment_history_layout);

        // Upcoming Appointments
        RecyclerView upcomingAppointments = findViewById(R.id.home_activity_upcoming_appointments_list);

        editButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, DoctorProfileActivity.class)));

        appointmentsScheduling.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AppointmentSchedulingActivity.class)));

        patientsRecords.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PatientRecordsActivity.class)));

        appointmentHistory.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AppointmentHistoryActivity.class)));

        appointments = getMockAppointments();

        HomeActivityAppointmentsAdapter adapter = new HomeActivityAppointmentsAdapter(appointments, this);
        upcomingAppointments.setLayoutManager(new LinearLayoutManager(this));
        upcomingAppointments.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Set the doctor's image
        doctorImage.setImageResource(R.drawable.default_profile_picture);
        doctorName.setText("Dr. " + FastSharedPreferences.get(this, "doctor_name", ""));

        appointmentsCounter.setText("Appointments today: " + appointments.size());
        nextAppointment.setText("Next appointment: " + appointments.get(0).getAppointmentTime());
    }

    @Override
    public void onItemClick(Appointment appointment) {
        Intent intent = new Intent(HomeActivity.this, AppointmentActivity.class);
        intent.putExtra("appointment", appointment);
        startActivity(intent);
    }
}