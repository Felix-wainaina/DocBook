package com.example.docbook;

public class Doctor {


        // Fields (variables that describe a doctor)
        private String name;
        private String specialty;
        private String hospitalLocation;
        private int profileImage;

        // Constructor (used to create a Doctor object)
        public Doctor(String name, String specialty, String hospitalLocation, int profileImage) {
            this.name = name;
            this.specialty = specialty;
            this.hospitalLocation = hospitalLocation;
            this.profileImage = profileImage;
        }

        // Getters (used to retrieve the data)

        public String getName() {
            return name;
        }

        public String getSpecialty() {
            return specialty;
        }

        public String getHospitalLocation() {
            return hospitalLocation;
        }

        public int getProfileImage() {
            return profileImage;
        }

        // Setters

        public void setName(String name) {
            this.name = name;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }

        public void setHospitalLocation(String hospitalLocation) {
            this.hospitalLocation = hospitalLocation;
        }

        public void setProfileImage(int profileImage) {
            this.profileImage = profileImage;
        }
    }

