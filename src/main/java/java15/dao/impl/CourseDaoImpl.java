package java15.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import java15.config.HibernateConfig;
import java15.dao.CourseDao;
import java15.enity.Course;
import java15.enity.Student;

import java.util.List;

public class CourseDaoImpl implements CourseDao {
    EntityManagerFactory entityManagerFactory = HibernateConfig.getEntityManager();

    @Override
    public String createCourse(Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(course);
            transaction.commit();
            return "Курс ийгиликтүү түзүлдү";
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Course getCourseById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Course.class, id);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String updateCourse(Long id, Course course) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course existingCourse = entityManager.find(Course.class, id);
            if (existingCourse != null) {
                existingCourse.setName(course.getName());
                existingCourse.setDescription(course.getDescription());
                entityManager.merge(existingCourse);
                transaction.commit();
                return "Курс ийгиликтүү жаңыртылды";
            } else {
                transaction.rollback();
                return "Курс табылбады.";
            }
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public String deleteCourse(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Course course = entityManager.find(Course.class, id);
            if (course != null) {
                entityManager.remove(course);
                transaction.commit();
                return "Курс ийгиликтүү жок кылынды";
            } else {
                transaction.rollback();
                return "Курс табылбай калды";
            }
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Course> getCourseStudents(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            TypedQuery<Course> query = entityManager.createQuery(
                    "SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId", Course.class);
            query.setParameter("studentId", id);
            return query.getResultList();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean assignStudentToCourses(Long studentId, Long courseId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Student student = entityManager.find(Student.class, studentId);
            Course course = entityManager.find(Course.class, courseId);

            if (student != null && course != null) {
                student.getCourses().add(course);
                course.getStudents().add(student);
                entityManager.merge(student);
                entityManager.merge(course);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}