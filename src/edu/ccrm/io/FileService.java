package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;
import java.util.List;

/**
 * Defines the contract for importing and exporting data from files.
 */
public interface FileService {
    List<Student> loadStudents();
    List<Course> loadCourses();
}