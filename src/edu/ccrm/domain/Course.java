package edu.ccrm.domain;

/**
 * Represents a course in the system.
 * This class uses the Builder design pattern for object creation.
 */
public class Course {

    // Fields are 'final' to make Course objects immutable (unchangeable) after creation
    private final String code;
    private final String title;
    private final int credits;

    // 1. The constructor is PRIVATE. It can only be called from within this class (by the Builder).
    private Course(Builder builder) {
        this.code = builder.code;
        this.title = builder.title;
        this.credits = builder.credits;
    }

    // --- Getters (No Setters to ensure immutability) ---
    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public String toString() {
        return "Course [Code=" + code + ", Title=" + title + ", Credits=" + credits + "]";
    }

    // 2. The public static nested Builder class
    public static class Builder {
        // Builder has the same fields as the main class
        private String code;
        private String title;
        private int credits;

        // 3. Setter-like methods that return 'this' for method chaining
        public Builder code(String code) {
            this.code = code;
            return this; // Returning 'this' enables chaining (e.g., new Builder().code(...).title(...))
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder credits(int credits) {
            this.credits = credits;
            return this;
        }

        // 4. The build() method that creates the final Course object
        public Course build() {
            // Here you can add validation before creating the object
            // For example: if(code == null || title == null) throw new IllegalStateException(...);
            return new Course(this);
        }
    }
}