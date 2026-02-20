package com.example.docbook;

import java.util.ArrayList;

public class DataGenerator {

    public static ArrayList<Doctor> getDoctors() {

        ArrayList<Doctor> doctorList = new ArrayList<>();

        doctorList.add(new Doctor(
                "Dr. Sarah Kamau",
                "Cardiologist",
                "Kenyatta National Hospital",
                R.drawable.doctor1
        ));

        doctorList.add(new Doctor(
                "Dr. Michael Mutisya",
                "Dermatologist",
                "Mbagathi Referral Hospital",
                R.drawable.doctor2
        ));

        doctorList.add(new Doctor(
                "Dr. Emily Jerop",
                "Pediatrician",
                "Moi Teaching and Referral Hospital",
                R.drawable.doctor3
        ));

        doctorList.add(new Doctor(
                "Dr. David Onyango",
                "Neurologist",
                "The Aga Khan University Hospital",
                R.drawable.doctor4
        ));

        doctorList.add(new Doctor(
                "Dr. Olivia Nafula",
                "Orthopedic Surgeon",
                "The Nairobi Hospital",
                R.drawable.doctor5
        ));

        return doctorList;
    }
}