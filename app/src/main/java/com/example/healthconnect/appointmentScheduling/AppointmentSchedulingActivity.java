package com.example.healthconnect.appointmentScheduling;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.R;
import com.example.healthconnect.appointment.AppointmentActivity;
import com.example.healthconnect.utils.database.Appointment;
import com.example.healthconnect.utils.database.Database;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AppointmentSchedulingActivity extends AppCompatActivity implements AppointmentSchedulingActivityAppoitmentsAdapter.OnItemClickListener {
    List<Appointment> appointments;
    AppointmentSchedulingActivityAppoitmentsAdapter adapter;

    @SuppressLint({"SourceLockedOrientationActivity", "SetTextI18n", "UseCompatLoadingForColorStateLists"})
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

        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        ImageView statusBackIcon = findViewById(R.id.status_bar_back_arrow_icon);
        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);
        statusBackIcon.setOnClickListener(v -> finish());
        statusBarIcon.setImageResource(R.drawable.scheduling_white);
        statusBarTitle.setText(getString(R.string.appointments_scheduling));

        LinearLayout sundayLayout = findViewById(R.id.vertical_calendar_sunday_layout);
        AppCompatButton sunday = findViewById(R.id.vertical_calendar_sunday);
        LinearLayout mondayLayout = findViewById(R.id.vertical_calendar_monday_layout);
        AppCompatButton monday = findViewById(R.id.vertical_calendar_monday);
        LinearLayout tuesdayLayout = findViewById(R.id.vertical_calendar_tuesday_layout);
        AppCompatButton tuesday = findViewById(R.id.vertical_calendar_tuesday);
        LinearLayout wednesdayLayout = findViewById(R.id.vertical_calendar_wednesday_layout);
        AppCompatButton wednesday = findViewById(R.id.vertical_calendar_wednesday);
        LinearLayout thursdayLayout = findViewById(R.id.vertical_calendar_thursday_layout);
        AppCompatButton thursday = findViewById(R.id.vertical_calendar_thursday);
        LinearLayout fridayLayout = findViewById(R.id.vertical_calendar_friday_layout);
        AppCompatButton friday = findViewById(R.id.vertical_calendar_friday);
        LinearLayout saturdayLayout = findViewById(R.id.vertical_calendar_saturday_layout);
        AppCompatButton saturday = findViewById(R.id.vertical_calendar_saturday);
        TextView monthYear = findViewById(R.id.appointment_scheduling_activity_vertical_calendar_month_year);

        monthYear.setText(months[month] + ", " + year);

        switch (dayOfWeek) {
            case Calendar.SUNDAY:
                sunday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                sunday.setText(dayOfMonth);
                break;
            case Calendar.MONDAY:
                monday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                monday.setText(String.valueOf(dayOfMonth));
                break;
            case Calendar.TUESDAY:
                tuesday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                tuesday.setText(dayOfMonth);
                break;
            case Calendar.WEDNESDAY:
                wednesday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                wednesday.setText(dayOfMonth);
                break;
            case Calendar.THURSDAY:
                thursday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                thursday.setText(dayOfMonth);
                break;
            case Calendar.FRIDAY:
                friday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                friday.setText(dayOfMonth);
                break;
            case Calendar.SATURDAY:
                saturday.setBackgroundTintList(getResources().getColorStateList(R.color.black));
                saturday.setText(dayOfMonth);
                break;
        }

        for (int i = dayOfWeek + 1; i <= 7; i++) {
            switch (i) {
                case Calendar.SUNDAY:
                    sunday.setText(String.valueOf(++dayOfMonth));
                    break;
                case Calendar.MONDAY:
                    monday.setText(String.valueOf(++dayOfMonth));
                    break;
                case Calendar.TUESDAY:
                    tuesday.setText(String.valueOf(++dayOfMonth));
                    break;
                case Calendar.WEDNESDAY:
                    wednesday.setText(String.valueOf(++dayOfMonth));
                    break;
                case Calendar.THURSDAY:
                    thursday.setText(String.valueOf(++dayOfMonth));
                    break;
                case Calendar.FRIDAY:
                    friday.setText(String.valueOf(++dayOfMonth));
                    break;
                case Calendar.SATURDAY:
                    saturday.setText(String.valueOf(++dayOfMonth));
                    break;
            }
        }

        RecyclerView upcomingAppointments = findViewById(R.id.appointment_scheduling_activity_appointments_list);
        ImageView addAppointment = findViewById(R.id.appointment_scheduling_activity_add_appointment);

        appointments = new ArrayList<>();

        adapter = new AppointmentSchedulingActivityAppoitmentsAdapter(appointments, this);
        upcomingAppointments.setLayoutManager(new LinearLayoutManager(this));
        upcomingAppointments.setAdapter(adapter);

        addAppointment.setOnClickListener(v -> {
            Intent intent = new Intent(AppointmentSchedulingActivity.this, AppointmentActivity.class);
            intent.putExtra("isNewAppointment", true);
            startActivity(intent);
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onResume() {
        super.onResume();

        try {
            appointments.clear();
        } catch (Exception e) {
            Log.e("AppointmentSchedulingActivity", "Error clearing appointments: ", e);
        }

        appointments.addAll(Database.getAllNotDoneAppointments());

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(Appointment appointment) {
        Intent intent = new Intent(AppointmentSchedulingActivity.this, AppointmentActivity.class);
        intent.putExtra("appointment", appointment);
        startActivity(intent);
    }
}