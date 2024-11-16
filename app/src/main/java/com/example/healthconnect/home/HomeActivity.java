package com.example.healthconnect.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.Patient;
import com.example.healthconnect.R;
import com.example.healthconnect.appointmentscheduling.AppointmentSchedulingActivity;
import com.example.healthconnect.doctorprofile.DoctorProfileActivity;
import com.example.healthconnect.patientrecords.PatientRecordsActivity;
import com.example.healthconnect.utils.FastSharedPreference;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements HomeActivityPatientAdapter.OnItemClickListener {
    ImageView doctorImage;
    TextView doctorName;
    TextView appointmentsCounter;
    TextView nextAppointment;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
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
        LinearLayout consultationHistory = findViewById(R.id.home_activity_consultation_history_layout);

        // Upcoming Appointments
        RecyclerView upcomingAppointments = findViewById(R.id.home_activity_upcoming_appointments_list);

        doctorImage.setOnClickListener(v -> {
            // TODO: Handle the click event
        });

        editButton.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, DoctorProfileActivity.class)));

        appointmentsScheduling.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, AppointmentSchedulingActivity.class)));

        patientsRecords.setOnClickListener(v -> startActivity(new Intent(HomeActivity.this, PatientRecordsActivity.class)));

        consultationHistory.setOnClickListener(v -> {
            // TODO: Handle the click event
        });

        // TODO: Set the upcoming appointments list
        // Mock patients data
        List<Patient> patients = new ArrayList<>();

        // TODO: Replace the mock data with the actual data
        patients.add(new Patient("Mr. Adam Smith", 72, "Male", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "09:30"));
        patients.add(new Patient("Ms. Alice Chang", 12, "Female", "(604) 555 - 5555", "smith.adam@gmail.com", "New patient", "10:00"));
        patients.add(new Patient("Ms. Emma Liu ", 56, "Female", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "10:20"));
        patients.add(new Patient("Mr.  Daniel  Grant", 35, "Male", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "10:40"));
        patients.add(new Patient("Mr. Adam Smith", 72, "Male", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "09:30"));
        patients.add(new Patient("Ms. Alice Chang", 12, "Female", "(604) 555 - 5555", "smith.adam@gmail.com", "New patient", "10:00"));
        patients.add(new Patient("Ms. Emma Liu ", 56, "Female", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "10:20"));
        patients.add(new Patient("Mr.  Daniel  Grant", 35, "Male", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "10:40"));
        patients.add(new Patient("Mr. Adam Smith", 72, "Male", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "09:30"));
        patients.add(new Patient("Ms. Alice Chang", 12, "Female", "(604) 555 - 5555", "smith.adam@gmail.com", "New patient", "10:00"));
        patients.add(new Patient("Ms. Emma Liu ", 56, "Female", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "10:20"));
        patients.add(new Patient("Mr.  Daniel  Grant", 35, "Male", "(604) 555 - 5555", "smith.adam@gmail.com", "Regular appointment", "10:40"));

        HomeActivityPatientAdapter adapter = new HomeActivityPatientAdapter(patients, this);
        upcomingAppointments.setLayoutManager(new LinearLayoutManager(this));
        upcomingAppointments.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: Set the doctor's image
        doctorImage.setImageResource(R.drawable.default_profile_picture);

        doctorName.setText("Dr. " + FastSharedPreference.get(this, "doctor_name", ""));

        // TODO: Set the number of appointments
        appointmentsCounter.setText("Appointments today: " + "12");

        // TODO: Set the next appointment
        nextAppointment.setText("Next appointment: " + "09:30 AM");
    }

    @Override
    public void onItemClick(Patient patient) {
        // TODO: Handle the click event
        String message = "Clicked on " + patient.getName() + "'s appointment at " + patient.getAppointmentTime();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}