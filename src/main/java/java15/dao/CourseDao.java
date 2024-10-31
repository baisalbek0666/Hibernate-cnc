package java15.dao;

import java15.enity.Course;

import java.util.List;

public interface CourseDao {
    String createCourse(Course course);
    Course  getCourseById(Long id);
    String updateCourse(Long id, Course course);
    String deleteCourse(Long id);
    List<Course> getCourseStudents(Long id);
    boolean assignStudentToCourses(Long studentId, Long courseId);
}
