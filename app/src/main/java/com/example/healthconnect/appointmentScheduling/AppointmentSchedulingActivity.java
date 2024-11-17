package com.example.healthconnect.appointmentScheduling;

import static com.example.healthconnect.MockAppointments.getMockAppointments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.Appointment;
import com.example.healthconnect.R;
import com.example.healthconnect.appointment.AppointmentActivity;

import java.util.List;

public class AppointmentSchedulingActivity extends AppCompatActivity implements AppointmentSchedulingActivityAppoitmentsAdapter.OnItemClickListener {
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
        statusBarIcon.setImageResource(R.drawable.calendar_icon);
        statusBarTitle.setText(getString(R.string.appointments_scheduling));

        // TODO: Set the upcoming appointments list
        List<Appointment> appointments = getMockAppointments();

        AppointmentSchedulingActivityAppoitmentsAdapter adapter = new AppointmentSchedulingActivityAppoitmentsAdapter(appointments, this);
        upcomingAppointments.setLayoutManager(new LinearLayoutManager(this));
        upcomingAppointments.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Appointment appointment) {
        Intent intent = new Intent(AppointmentSchedulingActivity.this, AppointmentActivity.class);
        intent.putExtra("appointment", appointment);
        intent.putExtra("isNewAppointment", true);
        startActivity(intent);
    }
}