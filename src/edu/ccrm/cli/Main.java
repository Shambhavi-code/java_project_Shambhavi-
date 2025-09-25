package edu.ccrm.cli;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Enrollment;
import edu.ccrm.domain.Grade;
import edu.ccrm.domain.Student;
import edu.ccrm.service.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to the Campus Course & Records Manager (CCRM)!");

        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentServiceImpl();
        CourseService courseService = new CourseServiceImpl();
        EnrollmentService enrollmentService = new EnrollmentServiceImpl(); // <-- Add this
        
        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    manageStudents(scanner, studentService);
                    break;
                case 2:
                    manageCourses(scanner, courseService);
                    break;
                case 3:
                    manageEnrollments(scanner, enrollmentService); // <-- Change this
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        System.out.println("Thank you for using CCRM. Goodbye!");
        scanner.close();
    }

    private static void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Manage Students");
        System.out.println("2. Manage Courses");
        System.out.println("3. Manage Enrollments");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(Scanner scanner) {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // --- Student Management (existing code) ---
    private static void manageStudents(Scanner scanner, StudentService studentService) {
        // This method remains unchanged
        boolean backToMain = false;
        while (!backToMain) {
            printStudentMenu();
            int choice = getUserChoice(scanner);
            switch (choice) {
                case 1: addNewStudent(scanner, studentService); break;
                case 2: findStudentById(scanner, studentService); break;
                case 3: listAllStudents(studentService); break;
                case 4: updateStudent(scanner, studentService); break;
                case 5: deleteStudent(scanner, studentService); break;
                case 0: backToMain = true; break;
                default: System.out.println("Invalid choice. Please try again."); break;
            }
        }
    }
    
    // ... other existing student methods (printStudentMenu, addNewStudent, etc.) remain unchanged ...

    // --- Course Management (existing code) ---
    private static void manageCourses(Scanner scanner, CourseService courseService) {
        // This method remains unchanged
        boolean backToMain = false;
        while (!backToMain) {
            printCourseMenu();
            int choice = getUserChoice(scanner);
            switch (choice) {
                case 1: addNewCourse(scanner, courseService); break;
                case 2: findCourseByCode(scanner, courseService); break;
                case 3: listAllCourses(courseService); break;
                case 4: deleteCourse(scanner, courseService); break;
                case 0: backToMain = true; break;
                default: System.out.println("Invalid choice. Please try again."); break;
            }
        }
    }

    // ... other existing course methods (printCourseMenu, addNewCourse, etc.) remain unchanged ...

    // --- NEW: Enrollment Management ---
    private static void manageEnrollments(Scanner scanner, EnrollmentService enrollmentService) {
        boolean backToMain = false;
        while (!backToMain) {
            printEnrollmentMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    enrollStudentInCourse(scanner, enrollmentService);
                    break;
                case 2:
                    assignGradeToStudent(scanner, enrollmentService);
                    break;
                case 3:
                    viewStudentEnrollments(scanner, enrollmentService);
                    break;
                case 0:
                    backToMain = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printEnrollmentMenu() {
        System.out.println("\n--- Enrollment Management ---");
        System.out.println("1. Enroll Student in a Course");
        System.out.println("2. Assign Grade");
        System.out.println("3. View a Student's Enrollments");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private static void enrollStudentInCourse(Scanner scanner, EnrollmentService enrollmentService) {
        try {
            System.out.println("\n--- Enroll Student ---");
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();

            if (enrollmentService.enrollStudent(studentId, courseCode)) {
                System.out.println("Enrollment successful!");
            } else {
                System.out.println("Enrollment failed. Please check the details and try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Student ID. Please enter a number.");
        }
    }

    private static void assignGradeToStudent(Scanner scanner, EnrollmentService enrollmentService) {
        try {
            System.out.println("\n--- Assign Grade ---");
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Course Code: ");
            String courseCode = scanner.nextLine();

            System.out.println("Available Grades:");
            for (Grade g : Grade.values()) {
                if (g != Grade.NOT_GRADED) {
                    System.out.println("- " + g.name());
                }
            }
            System.out.print("Enter Grade: ");
            String gradeStr = scanner.nextLine().toUpperCase();
            Grade grade = Grade.valueOf(gradeStr);

            if (enrollmentService.assignGrade(studentId, courseCode, grade)) {
                System.out.println("Grade assigned successfully!");
            } else {
                System.out.println("Failed to assign grade. Enrollment not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Student ID. Please enter a number.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid Grade. Please choose from the available grades.");
        }
    }

    private static void viewStudentEnrollments(Scanner scanner, EnrollmentService enrollmentService) {
        try {
            System.out.println("\n--- View Student Enrollments ---");
            System.out.print("Enter Student ID: ");
            int studentId = Integer.parseInt(scanner.nextLine());

            List<Enrollment> enrollments = enrollmentService.getEnrollmentsForStudent(studentId);
            if (enrollments.isEmpty()) {
                System.out.println("No enrollments found for student with ID " + studentId);
            } else {
                System.out.println("Enrollments for Student ID " + studentId + ":");
                enrollments.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid Student ID. Please enter a number.");
        }
    }
    
    // NOTE: Make sure the existing methods for Student and Course Management are also in your file.
    // The code block above only shows the NEW and CHANGED parts for brevity, but you need the whole file.
    // If you need the entire file again, just let me know.
    private static void printStudentMenu() {}
    private static void addNewStudent(Scanner scanner, StudentService studentService) {}
    private static void findStudentById(Scanner scanner, StudentService studentService) {}
    private static void listAllStudents(StudentService studentService) {}
    private static void updateStudent(Scanner scanner, StudentService studentService) {}
    private static void deleteStudent(Scanner scanner, StudentService studentService) {}
    private static void printCourseMenu() {}
    private static void addNewCourse(Scanner scanner, CourseService courseService) {}
    private static void findCourseByCode(Scanner scanner, CourseService courseService) {}
    private static void listAllCourses(CourseService courseService) {}
    private static void deleteCourse(Scanner scanner, CourseService courseService) {}
}