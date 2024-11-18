package com.example.healthconnect.patientRecords;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static com.example.healthconnect.MockPatients.getMockPatients;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.healthconnect.Patient;
import com.example.healthconnect.R;
import com.example.healthconnect.patientProfile.PatientProfileActivity;

import java.util.List;

public class PatientRecordsActivity extends AppCompatActivity implements PatientRecordsActivityPatientAdapter.OnItemClickListener {
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_patient_records);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);

        // Setting the icon and text for the status bar
        statusBarIcon.setImageResource(R.drawable.patient_records_icon);
        statusBarTitle.setText(getString(R.string.patients_records));

        EditText searchBar = findViewById(R.id.search_bar_input);

        // Patients
        RecyclerView patientsList = findViewById(R.id.patient_records_activity_patient_list);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Do nothing
            }

            @Override
            public void afterTextChanged(Editable searchInput) {
                if (!searchInput.toString().isEmpty()) {
                    // TODO: Implement search functionality
                }
            }
        });

        // TODO: Set the patient list
        List<Patient> patients = getMockPatients();

        PatientRecordsActivityPatientAdapter adapter = new PatientRecordsActivityPatientAdapter(patients, this);
        patientsList.setLayoutManager(new LinearLayoutManager(this));
        patientsList.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Patient patient) {
        Intent intent = new Intent(PatientRecordsActivity.this, PatientProfileActivity.class);
        intent.putExtra("patient", patient);
        startActivity(intent);
    }
}