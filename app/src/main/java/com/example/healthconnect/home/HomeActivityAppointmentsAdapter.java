package com.example.healthconnect.home;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.R;
import com.example.healthconnect.utils.database.Appointment;

import java.util.List;

public class HomeActivityAppointmentsAdapter extends RecyclerView.Adapter<HomeActivityAppointmentsAdapter.AppointmentViewHolder> {

    private final List<Appointment> appointmentList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Appointment appointment);
    }

    public HomeActivityAppointmentsAdapter(List<Appointment> appointmentList, OnItemClickListener listener) {
        this.appointmentList = appointmentList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_home_item, parent, false);
        return new AppointmentViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment appointment = appointmentList.get(position);

        holder.patientName.setText(appointment.getPatient().getName());
        holder.patientAge.setText(appointment.getPatient().getAge() + " years-old");
        holder.appointmentType.setText(appointment.getAppointmentType());

        holder.appointmentDate.setText(appointment.getAppointmentTime());
        if (position % 2 == 0) {
            holder.appointmentDate.setBackgroundTintList(AppCompatResources.getColorStateList(holder.itemView.getContext(), R.color.pastel_green));
        } else {
            holder.appointmentDate.setBackgroundTintList(AppCompatResources.getColorStateList(holder.itemView.getContext(), R.color.bondi_blue)); // Assuming pastel_blue is defined in colors.xml
        }

        holder.patientImage.setImageResource(R.drawable.default_profile_picture);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(appointment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {
        ImageView patientImage;
        TextView patientName;
        TextView patientAge;
        TextView appointmentType;
        TextView appointmentDate;

        public AppointmentViewHolder(View itemView) {
            super(itemView);

            patientImage = itemView.findViewById(R.id.activity_home_item_patient_picture);
            patientName = itemView.findViewById(R.id.activity_home_item_patient_name);
            patientAge = itemView.findViewById(R.id.activity_home_item_patient_age);
            appointmentType = itemView.findViewById(R.id.activity_home_item_appointment_type);
            appointmentDate = itemView.findViewById(R.id.activity_home_item_appointment_date);
        }
    }
}

