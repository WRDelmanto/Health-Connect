package com.example.healthconnect.appointmenthistory;

import static com.example.healthconnect.MockAppointments.getMockAppointments;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
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
import com.example.healthconnect.appointmentscheduling.AppointmentSchedulingActivity;
import com.example.healthconnect.appointmentscheduling.AppointmentSchedulingActivityAppoitmentsAdapter;
import com.example.healthconnect.home.HomeActivityAppointmentsAdapter;

import java.util.List;

public class AppointmentHistoryActivity extends AppCompatActivity implements AppointmentHistoryActivityAppointmentsAdapter.OnItemClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_appointment_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        statusBarIcon.setImageResource(R.drawable.appointment_history_icon);
        statusBarTitle.setText(getString(R.string.appointment_history));

        EditText filterInput = findViewById(R.id.appointment_history_activity_filter_input);
        RecyclerView patientList = findViewById(R.id.appointment_history_activity_patient_list);

        // TODO: Set the upcoming appointments list
        List<Appointment> appointments = getMockAppointments();

        AppointmentHistoryActivityAppointmentsAdapter adapter = new AppointmentHistoryActivityAppointmentsAdapter(appointments, this);
        patientList.setLayoutManager(new LinearLayoutManager(this));
        patientList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Appointment appointment) {
        // TODO: Go to Patient Profile Activity
    }
}