package com.example.healthconnect.appointmentscheduling;

import android.os.Bundle;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.List;

public class AppointmentSchedulingActivity extends AppCompatActivity implements AppointmentSchedulingActivityPatientAdapter.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointment_scheduling);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        // Upcoming Appointments
        RecyclerView upcomingAppointments = findViewById(R.id.appointment_scheduling_activity_appointments_list);

        // Setting the icon and text for the status bar
        statusBarIcon.setImageResource(R.drawable.appointments_scheduling_icon);
        statusBarTitle.setText(getString(R.string.home_activity_appointments_scheduling_content_description));

        // TODO: Set the upcoming appointments list
        // Mock patients data
        List<Patient> patients = new ArrayList<>();

        // TODO: Replace the mock data with the actual data
        patients.add(new Patient("Mr. Adam Smith", 72, "Regular appointment", "09:30"));
        patients.add(new Patient("Ms. Alice Chang", 12, "New patient", "10:00"));
        patients.add(new Patient("Ms. Emma Liu ", 56, "Regular appointment", "10:20"));
        patients.add(new Patient("Mr.  Daniel  Grant", 35, "Regular appointment", "10:40"));
        patients.add(new Patient("Mr. Adam Smith", 72, "Regular appointment", "09:30"));
        patients.add(new Patient("Ms. Alice Chang", 12, "New patient", "10:00"));
        patients.add(new Patient("Ms. Emma Liu ", 56, "Regular appointment", "10:20"));
        patients.add(new Patient("Mr.  Daniel  Grant", 35, "Regular appointment", "10:40"));
        patients.add(new Patient("Mr. Adam Smith", 72, "Regular appointment", "09:30"));
        patients.add(new Patient("Ms. Alice Chang", 12, "New patient", "10:00"));
        patients.add(new Patient("Ms. Emma Liu ", 56, "Regular appointment", "10:20"));
        patients.add(new Patient("Mr.  Daniel  Grant", 35, "Regular appointment", "10:40"));

        AppointmentSchedulingActivityPatientAdapter adapter = new AppointmentSchedulingActivityPatientAdapter(patients, this);
        upcomingAppointments.setLayoutManager(new LinearLayoutManager(this));
        upcomingAppointments.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Patient patient) {
        // TODO: Handle the click event
        String message = "Clicked on " + patient.getName() + "'s appointment at " + patient.getAppointmentTime();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}