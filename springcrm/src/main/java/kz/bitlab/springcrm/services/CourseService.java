package kz.bitlab.springcrm.services;

import kz.bitlab.springcrm.entities.Course;

import java.util.List;

public interface CourseService {
    Course addCourse(Course course);
    List<Course> getAllCourse();
    Course getCourse(Long id);
    Course updateCourse(Course course);
    void deleteCourse(Long id);
}
