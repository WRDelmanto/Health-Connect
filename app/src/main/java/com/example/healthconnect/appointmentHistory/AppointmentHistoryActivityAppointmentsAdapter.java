package com.example.healthconnect.appointmentHistory;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.utils.database.Appointment;
import com.example.healthconnect.R;

import java.util.List;

public class AppointmentHistoryActivityAppointmentsAdapter extends RecyclerView.Adapter<AppointmentHistoryActivityAppointmentsAdapter.AppointmentViewHolder> {

    private List<Appointment> appointmentList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Appointment appointment);
    }

    // Constructor to pass in the patient data
    public AppointmentHistoryActivityAppointmentsAdapter(List<Appointment> appointmentList, OnItemClickListener listener) {
        this.appointmentList = appointmentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_appointment_history_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        // Get the current patient object from the list
        Appointment appointment = appointmentList.get(position);

        // Set data for the patient item view
        holder.patientName.setText(appointment.getPatient().getName());
        holder.patientGenderAge.setText(appointment.getPatient().getGender() + ", " + appointment.getPatient().getAge() + " years-old");
        holder.appointmentTypeDateTime.setText("Last appointment: " + appointment.getAppointmentDate() + " - " + appointment.getAppointmentTime());

        // Set default or profile image (here you could use a profile image, or default)
        holder.patientImage.setImageResource(R.drawable.default_profile_picture);

        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(appointment);  // Trigger the click listener with the clicked patient
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    // ViewHolder class for the individual item in the RecyclerView
    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        // Declare the views we will use to bind data
        ImageView patientImage;
        TextView patientName;
        TextView patientGenderAge;
        TextView appointmentTypeDateTime;

        public AppointmentViewHolder(View itemView) {
            super(itemView);

            // Initialize the views
            patientImage = itemView.findViewById(R.id.activity_appointment_history_item_patient_picture);
            patientName = itemView.findViewById(R.id.activity_appointment_history_item_patient_name);
            patientGenderAge = itemView.findViewById(R.id.activity_appointment_history_item_patient_gender_age);
            appointmentTypeDateTime = itemView.findViewById(R.id.activity_appointment_history_item_appointment_type_date_time);
        }
    }
}

