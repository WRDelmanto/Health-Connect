package com.example.healthconnect.appointmentScheduling;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.annotation.SuppressLint;
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

import com.example.healthconnect.R;
import com.example.healthconnect.appointment.AppointmentActivity;
import com.example.healthconnect.utils.database.Appointment;
import com.example.healthconnect.utils.database.Database;

import java.util.List;

public class AppointmentSchedulingActivity extends AppCompatActivity implements AppointmentSchedulingActivityAppoitmentsAdapter.OnItemClickListener {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointment_scheduling);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBackIcon = findViewById(R.id.status_bar_back_arrow_icon);
        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);
        statusBackIcon.setOnClickListener(v -> finish());
        statusBarIcon.setImageResource(R.drawable.scheduling_white);
        statusBarTitle.setText(getString(R.string.appointments_scheduling));

        RecyclerView upcomingAppointments = findViewById(R.id.appointment_scheduling_activity_appointments_list);
        ImageView addAppointment = findViewById(R.id.appointment_scheduling_activity_add_appointment);

        List<Appointment> appointments = Database.getAllAppointments();

        AppointmentSchedulingActivityAppoitmentsAdapter adapter = new AppointmentSchedulingActivityAppoitmentsAdapter(appointments, this);
        upcomingAppointments.setLayoutManager(new LinearLayoutManager(this));
        upcomingAppointments.setAdapter(adapter);

        addAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(AppointmentSchedulingActivity.this, AppointmentActivity.class);
            intent.putExtra("isNewAppointment", true);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(Appointment appointment) {
        Intent intent = new Intent(AppointmentSchedulingActivity.this, AppointmentActivity.class);
        intent.putExtra("appointment", appointment);
        startActivity(intent);
    }
}