package java15.service;

import java15.enity.Course;
import java15.enity.Student;

import java.util.List;

public interface StudentService {
    String create(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Long studentId, Student newStudent);

    String deleteStudent(Long studentId);

    List<Student> getStudentsByCourseId(Long courseId);

    List<Student> getStudentsByRecentEnrollments(int limit);


}
