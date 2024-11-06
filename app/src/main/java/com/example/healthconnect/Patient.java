package com.example.healthconnect;

public class Patient {
    private String name;
    private int age;
    private String appointmentType;
    private String appointmentTime;

    public Patient(String name, int age, String appointmentType, String appointmentTime) {
        this.name = name;
        this.age = age;
        this.appointmentType = appointmentType;
        this.appointmentTime = appointmentTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
}

