package edu.ccrm.domain;

public class Student extends Person {

    private String registrationNumber;

    public Student(int id, String fullName, String email, String registrationNumber) {
        super(id, fullName, email);
        this.registrationNumber = registrationNumber;
    }

    // --- Getters and Setters (Ensure these are present) ---
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Override
    public String getDisplayRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + fullName + ", RegNo=" + registrationNumber + "]";
    }
}