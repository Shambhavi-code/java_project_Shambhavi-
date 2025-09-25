package edu.ccrm.io;

import edu.ccrm.domain.Course;
import edu.ccrm.domain.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileServiceImpl implements FileService {

    private static final String STUDENT_FILE_PATH = "test-data/students.csv";
    private static final String COURSE_FILE_PATH = "test-data/courses.csv";

    @Override
    public List<Student> loadStudents() {
        return loadData(STUDENT_FILE_PATH, this::parseStudentFromCsv);
    }

    @Override
    public List<Course> loadCourses() {
        return loadData(COURSE_FILE_PATH, this::parseCourseFromCsv);
    }

    /**
     * A generic helper method to load data from a CSV file.
     */
    private <T> List<T> loadData(String filePath, java.util.function.Function<String, T> parser) {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            System.err.println("Data file not found: " + filePath);
            return Collections.emptyList();
        }
        try (Stream<String> lines = Files.lines(path)) {
            return lines.skip(1) // Skip header row
                    .map(parser)
                    .filter(Objects::nonNull) // Filter out any objects that failed to parse
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + filePath);
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Parses a single line of CSV text into a Student object.
     * Returns null if parsing fails.
     */
    private Student parseStudentFromCsv(String line) {
        try {
            String[] fields = line.split(",");
            int id = Integer.parseInt(fields[0].trim());
            String fullName = fields[1].trim();
            String email = fields[2].trim();
            String regNo = fields[3].trim();
            return new Student(id, fullName, email, regNo);
        } catch (Exception e) {
            System.err.println("Skipping malformed student line: " + line);
            return null; // Return null if parsing fails
        }
    }

    /**
     * Parses a single line of CSV text into a Course object.
     * Returns null if parsing fails.
     */
    private Course parseCourseFromCsv(String line) {
        try {
            String[] fields = line.split(",");
            String code = fields[0].trim();
            String title = fields[1].trim();
            int credits = Integer.parseInt(fields[2].trim());
            return new Course.Builder().code(code).title(title).credits(credits).build();
        } catch (Exception e) {
            System.err.println("Skipping malformed course line: " + line);
            return null; // Return null if parsing fails
        }
    }
}