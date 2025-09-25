package edu.ccrm.service;

import edu.ccrm.domain.Student;
import java.util.List;
import java.util.Optional;

/**
 * An interface that defines the contract for student-related operations.
 * Any class that manages students must implement these methods.
 */
public interface StudentService {

    /**
     * Adds a new student to the system.
     * @param student The student object to add.
     */
    void addStudent(Student student);

    /**
     * Finds a student by their unique ID.
     * @param studentId The ID of the student to find.
     * @return An Optional containing the student if found, otherwise an empty Optional.
     */
    Optional<Student> findStudentById(int studentId);

    /**
     * Retrieves a list of all students in the system.
     * @return A list of all students.
     */
    List<Student> findAllStudents();

    /**
     * Updates an existing student's information.
     * @param updatedStudent The student object with updated details.
     * @return true if the update was successful, false otherwise.
     */
    boolean updateStudent(Student updatedStudent);

    /**
     * Deletes a student from the system using their ID.
     * @param studentId The ID of the student to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteStudent(int studentId);
}
