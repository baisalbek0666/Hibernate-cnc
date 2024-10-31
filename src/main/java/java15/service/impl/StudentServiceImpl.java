package java15.service.impl;

import java15.dao.StudentDao;
import java15.dao.impl.StudentDaoImpl;
import java15.enity.Student;
import java15.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentDao studentDao = new StudentDaoImpl();


    @Override
    public String create(Student student) {
        return studentDao.create(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentDao.getStudentById(id);
    }

    @Override
    public Student updateStudent(Long studentId, Student newStudent) {
        return studentDao.updateStudent(studentId, newStudent);
    }

    @Override
    public String deleteStudent(Long studentId) {
        return studentDao.deleteStudent(studentId);
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        return studentDao.getStudentsByCourseId(courseId);
    }

    @Override
    public List<Student> getStudentsByRecentEnrollments(int limit) {
        return studentDao.getStudentsByRecentEnrollments(limit);
    }
}

