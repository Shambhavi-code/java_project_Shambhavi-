package edu.ccrm.domain;

import java.time.LocalDate;

public abstract class Person {

    protected int id;
    protected String fullName;
    protected String email;
    protected LocalDate dateCreated;

    public Person(int id, String fullName, String email) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.dateCreated = LocalDate.now();
    }

    public abstract String getDisplayRole();

    // --- Getters and Setters (Ensure these are all present) ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
}