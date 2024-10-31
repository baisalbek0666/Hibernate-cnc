package java15;

import java15.enity.Course;
import java15.enity.Student;
import java15.service.CourseService;
import java15.service.StudentService;
import java15.service.impl.CourseServiceImpl;
import java15.service.impl.StudentServiceImpl;

import java.time.LocalDate;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
  //    HibernateConfig.getEntityManager();
        CourseService courseService = new CourseServiceImpl();
        StudentService studentService = new StudentServiceImpl();

        Course course = new Course("Peaksoft", "java15");
        Course course1 = new Course("js", "java15");
        Course course2 = new Course("java3", "java15");
        Course course3 = new Course("java", "java15");
        Course course4 = new Course("s++", "java15");

        System.out.println(courseService.createCourse(course));
        System.out.println(courseService.createCourse(course1));
        System.out.println(courseService.createCourse(course2));
        System.out.println(courseService.createCourse(course3));
        System.out.println(courseService.createCourse(course4));

        Student student1 = new Student("Baisalbek","baisalbek@gmail.com", LocalDate.of(2024,1,2));
        Student student2 = new Student("Nuradil","nuradil@gmail.com", LocalDate.of(2023,1,2));
        Student student3 = new Student("Atai","atai@gmail.com", LocalDate.of(2021,1,2));
        Student student4 = new Student("Adilet","adilet@gmail.com", LocalDate.of(2022,1,2));
        Student student5 = new Student("Anarbek","anarbek@gmail.com", LocalDate.of(2020,1,2));

        studentService.create(student1);
        studentService.create(student2);
        studentService.create(student3);
        studentService.create(student4);
        studentService.create(student5);

        courseService.assignStudentToCourses(student1.getId(), course.getId());
        courseService.assignStudentToCourses(student2.getId(), course1.getId());
        courseService.assignStudentToCourses(student3.getId(), course2.getId());
        courseService.assignStudentToCourses(student4.getId(), course3.getId());
        courseService.assignStudentToCourses(student5.getId(), course4.getId());

        course.setName("Updated Java Course");
        System.out.println(courseService.updateCourse(course.getId(), course));

        student1.setEmail("updated_baisalbek@gmail.com");
        Student updatedStudent = studentService.updateStudent(student1.getId(), student1);
        System.out.println("Жаңыртылган студент: " + updatedStudent.getFull_name() + " - " + updatedStudent.getEmail());

        courseService.assignStudentToCourses(student1.getId(), course.getId());
        courseService.assignStudentToCourses(student2.getId(), course1.getId());

        List<Course> coursesForStudent1 = courseService.getCourseStudents(student1.getId());
        System.out.println("Студентке тиешелүү курстар:");
        for (Course c : coursesForStudent1) {
            System.out.println("Курс аты: " + c.getName());
        }

        System.out.println(studentService.deleteStudent(student2.getId()));

        System.out.println(courseService.deleteCourse(course2.getId()));

        List<Student> studentsInCourse = studentService.getStudentsByCourseId(course.getId());
        System.out.println("Курс ичиндеги студенттер:");
        for (Student s : studentsInCourse) {
            System.out.println(s.getFull_name() + " - " + s.getEmail());
        }

        List<Student> recentStudents = studentService.getStudentsByRecentEnrollments(2);
        System.out.println("Акыркы кошулган студенттер:");
        for (Student student : recentStudents) {
            System.out.println(student.getFull_name() + " - " + student.getEnrollment_date());
        }













    }
}
