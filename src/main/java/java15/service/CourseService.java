package java15.service;

import java15.enity.Course;

import java.util.List;

public interface CourseService {

    String createCourse(Course course);
    Course  getCourseById(Long id);
    String updateCourse(Long id, Course course);
    String deleteCourse(Long id);
    List<Course> getCourseStudents(Long id);
    boolean assignStudentToCourses(Long studentId, Long courseId);

}
