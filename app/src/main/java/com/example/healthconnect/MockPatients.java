package com.example.healthconnect;

import java.util.ArrayList;
import java.util.List;

public class MockPatients {
    static List<Patient> mockPatients = new ArrayList<>();

    static {
        mockPatients.add(new Patient(1, "Mr. Adam Smith", 72, "Male", "(604) 555 - 5555", "smith.adam@gmail.com"));
        mockPatients.add(new Patient(2, "Ms. Alice Chang", 12, "Female", "(604) 555 - 5555", "alice.chang@gmail.com"));
        mockPatients.add(new Patient(3, "Ms. Emma Liu", 56, "Female", "(604) 555 - 5555", "emma.liu@gmail.com"));
        mockPatients.add(new Patient(4, "Mr. Daniel Grant", 35, "Male", "(604) 555 - 5555", "daniel.grant@gmail.com"));
    }

    public static List<Patient> getMockPatients() {
        return mockPatients;
    }
}
