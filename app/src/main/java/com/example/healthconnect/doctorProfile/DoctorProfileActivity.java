package com.example.healthconnect.doctorProfile;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.healthconnect.R;
import com.example.healthconnect.utils.FastSharedPreferences;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DoctorProfileActivity extends AppCompatActivity {
    String doctorName;
    String doctorEmail;
    String doctorPassword;
    String doctorPictureStr;

    static final int PICK_IMAGE_REQUEST = 1;
    Uri imageUri;
    ImageView doctorPicture;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_profile);
        setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //STATUS BAR
        ImageView statusBackIcon = findViewById(R.id.status_bar_back_arrow_icon);
        ImageView statusBarIcon = findViewById(R.id.status_bar_icon);
        TextView statusBarTitle = findViewById(R.id.status_bar_title);
        statusBackIcon.setOnClickListener(v -> finish());
        statusBarIcon.setImageResource(R.drawable.doctor_white);
        statusBarTitle.setText(getString(R.string.doctor_profile));


        //DOCTOR'S PROFILE: Picture + Name + Email + Password
        doctorPicture = findViewById(R.id.doctor_profile_activity_doctor_picture);
        EditText doctorNameInput = findViewById(R.id.doctor_profile_activity_doctor_name);
        EditText doctorEmailInput = findViewById(R.id.doctor_profile_activity_doctor_email);
        EditText doctorPasswordInput = findViewById(R.id.doctor_profile_activity_doctor_password);
        AppCompatButton saveButton = findViewById(R.id.doctor_profile_activity_save_button);

        doctorName = (String) FastSharedPreferences.get(this, "doctor_name", "");
        doctorEmail = (String) FastSharedPreferences.get(this, "doctor_email", "");
        doctorPassword = (String) FastSharedPreferences.get(this, "doctor_password", "");
        doctorPictureStr = (String) FastSharedPreferences.get(this, "doctor_picture", "");
        if (doctorPictureStr != null) {
            // Display the saved image
            File imgFile = new File(doctorPictureStr);
            if (imgFile.exists()) {
                Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                doctorPicture.setImageBitmap(bitmap);
            }
        } else {
            // Set a placeholder if no image is saved
            doctorPicture.setImageResource(R.drawable.default_profile_picture);
        }

        doctorNameInput.setText(doctorName);
        doctorEmailInput.setText(doctorEmail);
        doctorPasswordInput.setText(doctorPassword);

        doctorPicture.setOnClickListener(v -> openImagePicker());

        saveButton.setOnClickListener(v -> {
            doctorPictureStr = saveImageLocally(imageUri); // Save image locally

            doctorName = doctorNameInput.getText().toString();
            doctorEmail = doctorEmailInput.getText().toString();
            doctorPassword = doctorPasswordInput.getText().toString();
            FastSharedPreferences.put(this, "doctor_name", doctorName);
            FastSharedPreferences.put(this,"doctor_email", doctorEmail);
            FastSharedPreferences.put(this,"doctor_password", doctorPassword);
            FastSharedPreferences.put(this, "doctor_picture", doctorPictureStr);

            doctorPassword = (String) FastSharedPreferences.get(this, "doctor_password", "");
            doctorPictureStr = (String) FastSharedPreferences.get(this, "doctor_picture", null);

            finish();
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            doctorPicture.setImageURI(imageUri); // Show selected image
        }
    }

    private String saveImageLocally(Uri imageUri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            File directory = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "DoctorImages");
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(directory, "doctor_profile.jpg");
            try (FileOutputStream out = new FileOutputStream(file)) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
                return file.getAbsolutePath().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }
    }






}