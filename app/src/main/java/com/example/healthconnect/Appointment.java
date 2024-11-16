package com.example.healthconnect;

import java.io.Serializable;

public class Appointment implements Serializable {
    private int id;
    private Patient patient;
    private String appointmentType;
    private String appointmentDate;

    public Appointment(int id, Patient patient, String appointmentType, String appointmentDate, String appointmentTime) {
        this.id = id;
        this.patient = patient;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    private String appointmentTime;
}
