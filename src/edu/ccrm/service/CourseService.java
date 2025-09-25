package edu.ccrm.service;

import edu.ccrm.domain.Course;
import java.util.List;
import java.util.Optional;

/**
 * An interface that defines the contract for course-related operations.
 */
public interface CourseService {

    /**
     * Adds a new course to the system.
     * @param course The course object to add.
     */
    void addCourse(Course course);

    /**
     * Finds a course by its unique course code.
     * @param courseCode The code of the course to find (e.g., "CS101").
     * @return An Optional containing the course if found, otherwise an empty Optional.
     */
    Optional<Course> findCourseByCode(String courseCode);

    /**
     * Retrieves a list of all courses in the system.
     * @return A list of all courses.
     */
    List<Course> findAllCourses();

    /**
     * Deletes a course from the system using its code.
     * @param courseCode The code of the course to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteCourse(String courseCode);
}