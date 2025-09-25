package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EnrollmentServiceImpl implements EnrollmentService {

    private final DataStore dataStore = DataStore.getInstance();

    @Override
    public boolean enrollStudent(int studentId, String courseCode) {
        // Check if student is already enrolled in this course
        boolean alreadyEnrolled = dataStore.getEnrollments().stream()
                .anyMatch(e -> e.getStudentId() == studentId && e.getCourseCode().equalsIgnoreCase(courseCode));

        if (alreadyEnrolled) {
            System.out.println("Error: Student is already enrolled in this course.");
            return false;
        }

        // We should also check if the student and course actually exist
        // For now, we'll assume they do and add the enrollment
        Enrollment newEnrollment = new Enrollment(studentId, courseCode);
        dataStore.getEnrollments().add(newEnrollment);
        return true;
    }

    @Override
    public boolean assignGrade(int studentId, String courseCode, Grade grade) {
        // Find the specific enrollment
        Optional<Enrollment> enrollmentOpt = dataStore.getEnrollments().stream()
                .filter(e -> e.getStudentId() == studentId && e.getCourseCode().equalsIgnoreCase(courseCode))
                .findFirst();

        if (enrollmentOpt.isPresent()) {
            enrollmentOpt.get().setGrade(grade); // Set the grade on the found enrollment
            return true;
        }

        return false; // Enrollment not found
    }

    @Override
    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        // Use a stream to filter and collect all enrollments for a specific student
        return dataStore.getEnrollments().stream()
                .filter(e -> e.getStudentId() == studentId)
                .collect(Collectors.toList());
    }
}
