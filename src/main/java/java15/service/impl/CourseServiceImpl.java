package java15.service.impl;

import java15.dao.CourseDao;
import java15.dao.impl.CourseDaoImpl;
import java15.enity.Course;
import java15.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    CourseDao courseDao = new CourseDaoImpl();


    @Override
    public String createCourse(Course course) {
        return courseDao.createCourse(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public String updateCourse(Long id, Course course) {
        return courseDao.updateCourse(id, course);
    }

    @Override
    public String deleteCourse(Long id) {
        return courseDao.deleteCourse(id);
    }

    @Override
    public List<Course> getCourseStudents(Long id) {
        return courseDao.getCourseStudents(id);
    }

    @Override
    public boolean assignStudentToCourses(Long studentId, Long courseId) {
        return courseDao.assignStudentToCourses(studentId, courseId);
    }
}

