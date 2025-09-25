package edu.ccrm.domain;

import java.time.LocalDate;

public class Enrollment {
    private int studentId;
    private String courseCode;
    private LocalDate enrollmentDate;
    private Grade grade;

    public Enrollment(int studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
        this.enrollmentDate = LocalDate.now();
        this.grade = Grade.NOT_GRADED; // Default grade upon enrollment
    }

    // --- Getters and Setters ---
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Enrollment [Student ID=" + studentId + ", Course Code=" + courseCode + ", Grade=" + grade + "]";
    }
}
