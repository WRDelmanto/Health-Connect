package com.example.healthconnect.patientRecords;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthconnect.Patient;
import com.example.healthconnect.R;

import java.util.List;

public class PatientRecordsActivityPatientAdapter extends RecyclerView.Adapter<PatientRecordsActivityPatientAdapter.PatientViewHolder> {

    private List<Patient> patientList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }

    // Constructor to pass in the patient data
    public PatientRecordsActivityPatientAdapter(List<Patient> patientList, OnItemClickListener listener) {
        this.patientList = patientList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_patient_records_item, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        // Get the current patient object from the list
        Patient patient = patientList.get(position);

        // Set data for the patient item view
        holder.patientName.setText(patient.getName());
        holder.patientGenderAge.setText(patient.getGender() + ", " + patient.getAge() + " years-old");
        holder.patientPhoneNumberEmail.setText(patient.getPhoneNumber() + " | " + patient.getEmail());

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
        TextView patientGenderAge;
        TextView patientPhoneNumberEmail;

        public PatientViewHolder(View itemView) {
            super(itemView);

            // Initialize the views
            patientImage = itemView.findViewById(R.id.activity_patient_records_item_patient_picture);
            patientName = itemView.findViewById(R.id.activity_patient_records_item_patient_name);
            patientGenderAge = itemView.findViewById(R.id.activity_patient_records_item_patient_gender_age);
            patientPhoneNumberEmail = itemView.findViewById(R.id.activity_patient_records_item_patient_phone_number_email);
        }
    }
}
