package com.example.healthconnect.utils.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "health_connect.db";
    private static final int DATABASE_VERSION = 1;

    private static Database instance;

    private Database(Context context) {
        super(context.getApplicationContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized void initializeInstance(Context context) {
        if (instance == null) {
            instance = new Database(context);
        }
    }

    private static synchronized Database getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Database is not initialized. Call initializeInstance(Context) first.");
        }
        return instance;
    }

    private static final String CREATE_TABLE_PATIENTS =
            "CREATE TABLE patients (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "date_of_birth INTEGER NOT NULL, " + // Format: YYYYMMDD
                    "gender TEXT NOT NULL, " +
                    "phone_number TEXT NOT NULL, " +
                    "email TEXT UNIQUE NOT NULL, " +
                    "height REAL NOT NULL, " +
                    "weight REAL NOT NULL" +
                    ");";

    private static final String CREATE_TABLE_APPOINTMENTS =
            "CREATE TABLE appointments (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "patient_id INTEGER NOT NULL, " +
                    "appointment_type TEXT NOT NULL, " +
                    "appointment_date TEXT NOT NULL, " +
                    "appointment_time TEXT NOT NULL, " +
                    "notes TEXT, " +
                    "medicines TEXT NOT NULL, " +
                    "exams TEXT NOT NULL, " +
                    "is_done INTEGER NOT NULL DEFAULT 0, " +
                    "FOREIGN KEY(patient_id) REFERENCES patients(id)" +
                    ");";

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_PATIENTS);
        database.execSQL(CREATE_TABLE_APPOINTMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS patients;");
        database.execSQL("DROP TABLE IF EXISTS appointments;");
        onCreate(database);
    }

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        SQLiteDatabase database = getInstance().getReadableDatabase();

        Cursor cursor = database.query("patients",
                null,
                null,
                null,
                null,
                null,
                "name ASC");

        if (cursor.moveToFirst()) {
            do {
                patients.add(new Patient(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        cursor.getString(cursor.getColumnIndexOrThrow("name")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("date_of_birth")),
                        cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                        cursor.getString(cursor.getColumnIndexOrThrow("phone_number")),
                        cursor.getString(cursor.getColumnIndexOrThrow("email")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("height")),
                        cursor.getDouble(cursor.getColumnIndexOrThrow("weight"))
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return patients;
    }

    public static Patient getPatientById(int patientId) {
        SQLiteDatabase database = getInstance().getReadableDatabase();

        Cursor cursor = database.query(
                "patients",
                null,
                "id = ?",
                new String[]{String.valueOf(patientId)},
                null,
                null,
                null
        );

        Patient patient = null;

        if (cursor.moveToFirst()) {
            patient = new Patient(
                    cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("date_of_birth")),
                    cursor.getString(cursor.getColumnIndexOrThrow("gender")),
                    cursor.getString(cursor.getColumnIndexOrThrow("phone_number")),
                    cursor.getString(cursor.getColumnIndexOrThrow("email")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("height")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("weight"))
            );
        }

        cursor.close();

        return patient; // Returns null if no patient is found
    }

    public static long addPatient(Patient patient) {
        SQLiteDatabase database = getInstance().getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", patient.getName());
        values.put("date_of_birth", patient.getDateOfBirth());
        values.put("gender", patient.getGender());
        values.put("phone_number", patient.getPhoneNumber());
        values.put("email", patient.getEmail());
        values.put("height", patient.getHeight());
        values.put("weight", patient.getWeight());

        long patientId = database.insert("patients", null, values);
        database.close();

        return patientId; // Return the ID of the inserted patient
    }

    public static void updatePatient(Patient patient) {
        SQLiteDatabase database = instance.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", patient.getName());
        values.put("date_of_birth", patient.getDateOfBirth());
        values.put("gender", patient.getGender());
        values.put("phone_number", patient.getPhoneNumber());
        values.put("email", patient.getEmail());
        values.put("height", patient.getHeight());
        values.put("weight", patient.getWeight());

        database.update(
                "patients",
                values,
                "id = ?",
                new String[]{String.valueOf(patient.getId())}
        );

        database.close();
    }

    public static List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        SQLiteDatabase database = getInstance().getReadableDatabase();

        Cursor cursor = database.query(
                "appointments",
                null,
                null,
                null,
                null,
                null,
                "appointment_date ASC, appointment_time ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                int appointmentId = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                int patientId = cursor.getInt(cursor.getColumnIndexOrThrow("patient_id"));
                String appointmentType = cursor.getString(cursor.getColumnIndexOrThrow("appointment_type"));
                String appointmentDate = cursor.getString(cursor.getColumnIndexOrThrow("appointment_date"));
                String appointmentTime = cursor.getString(cursor.getColumnIndexOrThrow("appointment_time"));
                String notes = cursor.getString(cursor.getColumnIndexOrThrow("notes"));
                String medicines = cursor.getString(cursor.getColumnIndexOrThrow("medicines"));
                String exams = cursor.getString(cursor.getColumnIndexOrThrow("exams"));
                boolean isDone = cursor.getInt(cursor.getColumnIndexOrThrow("is_done")) == 1;

                // Fetch the associated patient
                Patient patient = getPatientById(patientId);

                // Create the appointment object
                Appointment appointment = new Appointment(
                        appointmentId,
                        patient,
                        appointmentType,
                        appointmentDate,
                        appointmentTime,
                        notes,
                        medicines,
                        exams,
                        isDone
                );

                appointments.add(appointment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return appointments;
    }

    public static List<Appointment> getTodayAppointments() {
        List<Appointment> todayAppointments = new ArrayList<>();
        SQLiteDatabase database = instance.getReadableDatabase();

        // Get the current date in "YYYY-MM-DD" format
        String today = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        Cursor cursor = database.query(
                "appointments",
                null,
                "appointment_date = ? AND is_done = ?",
                new String[]{today, "0"}, // "0" represents false in SQLite
                null,
                null,
                "appointment_time ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        getPatientById(cursor.getInt(cursor.getColumnIndexOrThrow("patient_id"))),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_type")),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_time")),
                        cursor.getString(cursor.getColumnIndexOrThrow("notes")),
                        cursor.getString(cursor.getColumnIndexOrThrow("medicines")),
                        cursor.getString(cursor.getColumnIndexOrThrow("exams")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("is_done")) == 1
                );
                todayAppointments.add(appointment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return todayAppointments;
    }

    public static List<Appointment> getDoneAppointments() {
        List<Appointment> doneAppointments = new ArrayList<>();
        SQLiteDatabase database = instance.getReadableDatabase();

        Cursor cursor = database.query(
                "appointments",
                null,
                "is_done = ?",
                new String[]{"1"}, // "1" represents true in SQLite
                null,
                null,
                "appointment_date ASC, appointment_time ASC"
        );

        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        getPatientById(cursor.getInt(cursor.getColumnIndexOrThrow("patient_id"))),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_type")),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_time")),
                        cursor.getString(cursor.getColumnIndexOrThrow("notes")),
                        cursor.getString(cursor.getColumnIndexOrThrow("medicines")),
                        cursor.getString(cursor.getColumnIndexOrThrow("exams")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("is_done")) == 1
                );
                doneAppointments.add(appointment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return doneAppointments;
    }

    public static List<Appointment> getDoneAppointmentsByPatientId(Patient patient) {
        List<Appointment> patientAppointments = new ArrayList<>();
        SQLiteDatabase database = instance.getReadableDatabase();

        // Query to get appointments for a specific patient that are marked as "done" (is_done = 1)
        Cursor cursor = database.query(
                "appointments", // Table name
                null, // Select all columns
                "patient_id = ? AND is_done = ?", // WHERE clause
                new String[]{String.valueOf(patient.getId()), "1"}, // WHERE arguments (patient's ID and is_done = 1)
                null, // GROUP BY
                null, // HAVING
                "appointment_date ASC, appointment_time ASC" // ORDER BY (date and time)
        );

        if (cursor.moveToFirst()) {
            do {
                Appointment appointment = new Appointment(
                        cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                        getPatientById(cursor.getInt(cursor.getColumnIndexOrThrow("patient_id"))), // Assuming you have a method to get Patient by ID
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_type")),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_date")),
                        cursor.getString(cursor.getColumnIndexOrThrow("appointment_time")),
                        cursor.getString(cursor.getColumnIndexOrThrow("notes")),
                        cursor.getString(cursor.getColumnIndexOrThrow("medicines")),
                        cursor.getString(cursor.getColumnIndexOrThrow("exams")),
                        cursor.getInt(cursor.getColumnIndexOrThrow("is_done")) == 1
                );
                patientAppointments.add(appointment);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return patientAppointments;
    }

    public static long addAppointment(Appointment appointment) {
        SQLiteDatabase database = getInstance().getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("patient_id", appointment.getPatient().getId());
        values.put("appointment_type", appointment.getAppointmentType());
        values.put("appointment_date", appointment.getAppointmentDate());
        values.put("appointment_time", appointment.getAppointmentTime());
        values.put("notes", appointment.getNotes());
        values.put("medicines", appointment.getMedicines());
        values.put("exams", appointment.getExams());
        values.put("is_done", appointment.isDone() ? 1 : 0);

        long id = database.insert("appointments", null, values);

        database.close();

        return id; // Returns the ID of the inserted appointment or -1 if the operation failed
    }

    public static void updateAppointment(Appointment appointment) {
        SQLiteDatabase database = instance.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("patient_id", appointment.getPatient().getId());
        values.put("appointment_type", appointment.getAppointmentType());
        values.put("appointment_date", appointment.getAppointmentDate());
        values.put("appointment_time", appointment.getAppointmentTime());
        values.put("notes", appointment.getNotes());
        values.put("medicines", appointment.getMedicines());
        values.put("exams", appointment.getExams());
        values.put("is_done", appointment.isDone() ? 1 : 0);

        database.update(
                "appointments",
                values,
                "id = ?",
                new String[]{String.valueOf(appointment.getId())}
        );

        database.close();
    }
}
