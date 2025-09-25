package edu.ccrm.service;

import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import java.util.List;

public interface EnrollmentService {

    boolean enrollStudent(int studentId, String courseCode);

    boolean assignGrade(int studentId, String courseCode, Grade grade);

    List<Enrollment> getEnrollmentsForStudent(int studentId);
}