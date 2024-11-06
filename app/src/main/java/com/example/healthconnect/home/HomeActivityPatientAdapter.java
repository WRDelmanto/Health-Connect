package com.example.healthconnect.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.Patient;
import com.example.healthconnect.R;

import java.util.List;

public class HomeActivityPatientAdapter extends RecyclerView.Adapter<HomeActivityPatientAdapter.PatientViewHolder> {

    private List<Patient> patientList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    // Constructor to pass in the patient data
    public HomeActivityPatientAdapter(List<Patient> patientList, OnItemClickListener listener) {
        this.patientList = patientList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_activity_patient_item, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        // Get the current patient object from the list
        Patient patient = patientList.get(position);

        // Set data for the patient item view
        holder.patientName.setText(patient.getName());
        holder.patientAge.setText(patient.getAge() + " years-old");
        holder.appointmentType.setText(patient.getAppointmentType());

        // TODO: Adjust background color for even or odd items
        holder.appointmentDate.setText(patient.getAppointmentTime());
        if (position % 2 == 0) {
            holder.appointmentDate.setBackgroundTintList(AppCompatResources.getColorStateList(holder.itemView.getContext(), R.color.pastel_green));
        } else {
            holder.appointmentDate.setBackgroundTintList(AppCompatResources.getColorStateList(holder.itemView.getContext(), R.color.bondi_blue)); // Assuming pastel_blue is defined in colors.xml
        }

        // Set default or profile image (here you could use a profile image, or default)
        holder.patientImage.setImageResource(R.drawable.default_profile_picture);

        // Set item click listener
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(patient);  // Trigger the click listener with the clicked patient
            }
        });
    }

    @Override
    public int getItemCount() {
        return patientList.size();
    }

    // ViewHolder class for the individual item in the RecyclerView
    public static class PatientViewHolder extends RecyclerView.ViewHolder {

        // Declare the views we will use to bind data
        ImageView patientImage;
        TextView patientName;
        TextView patientAge;
        TextView appointmentType;
        TextView appointmentDate;

        public PatientViewHolder(View itemView) {
            super(itemView);

            // Initialize the views
            patientImage = itemView.findViewById(R.id.home_activity_patient_item_image);
            patientName = itemView.findViewById(R.id.home_activity_patient_item_patient_name);
            patientAge = itemView.findViewById(R.id.home_activity_patient_item_patient_age);
            appointmentType = itemView.findViewById(R.id.home_activity_patient_item_appointment_type);
            appointmentDate = itemView.findViewById(R.id.home_activity_patient_item_appointment_date);
        }
    }
}

