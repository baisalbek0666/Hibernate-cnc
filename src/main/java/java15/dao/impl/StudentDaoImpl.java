package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java15.config.HibernateConfig;
import java15.dao.StudentDao;
import java15.enity.Course;
import java15.enity.Student;

import java.util.Collections;
import java.util.List;


public class StudentDaoImpl implements StudentDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManager();

    @Override
    public String create(Student student) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
            return "Student successfully added.";
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Student getStudentById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Student.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Student updateStudent(Long studentId, Student newStudent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student existingStudent = entityManager.find(Student.class, studentId);
            if (existingStudent != null) {
                existingStudent.setFull_name(newStudent.getFull_name());
                existingStudent.setEmail(newStudent.getEmail());
                existingStudent.setEnrollment_date(newStudent.getEnrollment_date());
                entityManager.merge(existingStudent);
                transaction.commit();
                return existingStudent;
            } else {
                transaction.rollback();
                return null;
            }
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteStudent(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student foundStudent = entityManager.find(Student.class, id);
            if (foundStudent != null) {
                entityManager.remove(foundStudent);
                transaction.commit();
                return "Student successfully deleted.";
            } else {
                transaction.rollback();
                return "Student not found.";
            }
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Student> getStudentsByCourseId(Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery(
                            "SELECT s FROM Student s JOIN s.courses c WHERE c.id = :courseId", Student.class)
                    .setParameter("courseId", courseId)
                    .getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Student> getStudentsByRecentEnrollments(int limit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery(
                            "SELECT s FROM Student s ORDER BY s.enrollment_date DESC", Student.class)
                    .setMaxResults(limit)
                    .getResultList();
        } finally {
            entityManager.close();
        }


    }
}