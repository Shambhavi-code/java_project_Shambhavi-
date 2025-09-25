package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;
import java.util.Optional;

/**
 * The concrete implementation of the StudentService interface.
 * This class contains the business logic for all student-related operations.
 */
public class StudentServiceImpl implements StudentService {

    // Get the single instance of our in-memory database
    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public void addStudent(Student student) {
        // Simple validation to avoid adding null students
        if (student != null) {
            dataStore.getStudents().add(student);
        }
    }

    @Override
    public Optional<Student> findStudentById(int studentId) {
        // Use Java Streams to find the first student that matches the given ID
        return dataStore.getStudents()
                .stream()
                .filter(student -> student.getId() == studentId)
                .findFirst();
    }



    @Override
    public List<Student> findAllStudents() {
        // Return the complete list of students from the DataStore
        return dataStore.getStudents();
    }

    @Override
    public boolean updateStudent(Student updatedStudent) {
        // Find the existing student by ID
        Optional<Student> existingStudentOpt = findStudentById(updatedStudent.getId());

        // If the student exists, update them
        if (existingStudentOpt.isPresent()) {
            Student existingStudent = existingStudentOpt.get();
            // Update the fields
            existingStudent.setFullName(updatedStudent.getFullName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setRegistrationNumber(updatedStudent.getRegistrationNumber());
            return true; // Indicate success
        }

        return false; // Indicate student not found
    }

    @Override
    public boolean deleteStudent(int studentId) {
        // The removeIf method returns true if any elements were removed
        return dataStore.getStudents().removeIf(student -> student.getId() == studentId);
    }
}