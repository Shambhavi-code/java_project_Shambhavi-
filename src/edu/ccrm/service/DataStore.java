package edu.ccrm.service;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment; // <-- Add this import
import edu.ccrm.domain.Student;
import edu.ccrm.io.FileService;
import edu.ccrm.io.FileServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static final DataStore instance = new DataStore();

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments; // <-- Add this line

    private DataStore() {
        // We are using the temporary debugging version, which is fine for now.
        System.out.println("--- USING TEMPORARY DEBUGGING DATASTORE ---");
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>(); // <-- Add this line
        System.out.println("--- LISTS HAVE BEEN INITIALIZED SUCCESSFULLY ---");
    }

    public static DataStore getInstance() {
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Enrollment> getEnrollments() { // <-- Add this method
        return enrollments;
    }
}