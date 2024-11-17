package com.example.healthconnect;

import java.util.ArrayList;
import java.util.List;

public class MockAppointments {
    static List<Appointment> mockAppointments = new ArrayList<>();

    static {
        List<Patient> mockPatients = MockPatients.getMockPatients();

        mockAppointments.add(new Appointment(1, mockPatients.get(0), "New patient", "15/11/2024", "09:30", "", "", "", false));
        mockAppointments.add(new Appointment(2, mockPatients.get(1), "New patient", "16/11/2024", "10:00", "", "", "", false));
        mockAppointments.add(new Appointment(3, mockPatients.get(2), "New patient", "17/11/2024", "10:20", "", "", "", false));
        mockAppointments.add(new Appointment(4, mockPatients.get(3), "New patient", "18/11/2024", "10:40", "", "", "", false));
        mockAppointments.add(new Appointment(5, mockPatients.get(0), "Regular appointment", "19/11/2024", "09:30", "", "", "", false));
        mockAppointments.add(new Appointment(6, mockPatients.get(1), "Regular appointment", "20/11/2024", "10:00", "", "", "", false));
        mockAppointments.add(new Appointment(7, mockPatients.get(2), "Regular appointment", "21/11/2024", "10:20", "", "", "", false));
        mockAppointments.add(new Appointment(8, mockPatients.get(3), "Regular appointment", "22/11/2024", "10:40", "", "", "", false));
        mockAppointments.add(new Appointment(9, mockPatients.get(0), "Regular appointment", "23/11/2024", "09:30", "", "", "", false));
        mockAppointments.add(new Appointment(10, mockPatients.get(1), "Regular appointment", "24/11/2024", "10:00", "", "", "", false));
        mockAppointments.add(new Appointment(11, mockPatients.get(2), "Regular appointment", "25/11/2024", "10:20", "", "", "", false));
        mockAppointments.add(new Appointment(12, mockPatients.get(3), "Regular appointment", "26/11/2024", "10:40", "", "", "", false));
    }

    public static List<Appointment> getMockAppointments() {
        return mockAppointments;
    }
}
