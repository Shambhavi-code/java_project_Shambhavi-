package edu.ccrm.service;

import java.util.List;
import java.util.Optional;

import edu.ccrm.domain.Course;

public class CourseServiceImpl implements CourseService {

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Course> findCourseByCode(String courseCode) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Course> findAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCourse(String courseCode) {
		// TODO Auto-generated method stub
		return false;
	}

}
