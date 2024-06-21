package com.i2i.cms.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.Student;

/**
 * This class provides methods for managing grades.
 * It handles operations like inserting grades, retrieving grade details,
 * and counting students in a particular grade and section.
 */

public class GradeDao {

    /**
     * <p>
     * Inserts a new grade level and generates grade IDs for the section
     * of the given grade.
     * </p>
     *
     * @param grade 
     *        The grade consists of the grade and section.
     *
     * @return grade details with the grade id.
     *
     * @throws StudentException If the grade and section can not be inserted.
     */
    public Grade insertGrade(Grade grade) {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(grade);
            transaction.commit();
            return grade;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to insert grade " + grade.getGrade() + " and section " + grade.getSection(), e);
        }      
    }
    
    /**
     * <p>
     * This method counts the number of students in a particular grade and section using the grade id.
     * </p>
     *
     * @param gradeId 
     *        The unique identifier of the grade and section.
     *        The gradeId should be a valid uuid.
     * 
     * @return count
     *         The number of students in the particular grade id.
     *
     * @throws Student exception when the grade id can not be accessed.
     */
    public long countStudentsInSection(int gradeId) {
        long count = 0;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(s) FROM Student s WHERE s.grade.gradeId = :gradeId";
            count = (long) session.createQuery(hql).setParameter("gradeId", gradeId).uniqueResult();
        } catch (Exception e) {
            throw new StudentException("Error occured while counting the students in grade id " + gradeId, e);
        }
        return count;
    }

    /**
     * <p>
     * This method retrieves the sections of a particular grade. 
     * </p>
     *
     * @param gradeLevel 
     *        The grade level for which the sections has to be fetched.
     *        The grade should contain only integer values from 1 - 12.
     *
     * @return List of sections of a particular grade.
     *
     * @throws StudentException If the grade is invalid or can not access grade.
     */
    public List<Grade> retrieveSectionsByGrade(int gradeLevel) {
        List<Grade> grades = new ArrayList<>();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            String hql = "from Grade g where g.grade = :grade";
            Query query = session.createQuery(hql, Grade.class);
            query.setParameter("grade", gradeLevel);
            grades = query.list();
        } catch (Exception e) {
            throw new StudentException("Error occured while retrieving the sections of grade " + gradeLevel, e);
        }       
        return grades;
    }

    /*
     * <p>
     * This method checks whether the given grade is already exist or not.
     * </p>
     *
     * @param grade 
     *        The grade level for which the availability has to be fetched.
     *        The grade should contain only integers within the range (1 - 12).
     * 
     * @return True if the grade is already present, else return false.
     *
     * @throws Student exception when the grade can not be accessed.
     */
    public boolean isGradeAvailable(int gradeLevel) {
        boolean isGradeExist = false;
        List<Grade> grades = new ArrayList<>();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            String hql = "from Grade g where g.grade = :grade";
            Query query = session.createQuery(hql, Grade.class);
            query.setParameter("grade", gradeLevel);
            grades = query.getResultList();
            if (!grades.isEmpty()) {
                isGradeExist = true;
            }
        } catch (Exception e) {
            throw new StudentException("Unabe to check the availability of the grade " + gradeLevel , e);
        }
        return isGradeExist;
    }

    /*
     * <p>
     * This method retrieves all the students in a particular grade and section.
     * </p>
     *
     * @param grade 
     *        The grade consists of grade level and section.
     *        The grade level should contain only numbers from 1 - 12.
     *        The section should contain only characters starting from A.
     * 
     * @return List of students in the particular grade and section.
     *
     * @throws Student exception when the grade and section can not be accessed.
     */
    public Set<Student> retrieveStudentsByGrade(Grade grade) {
        Set<Student> students = new HashSet<>();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            String hql = "from Grade g where g.grade = :grade and g.section = :section";
            Query<Grade> query = session.createQuery(hql, Grade.class);
            query.setParameter("grade", grade.getGrade());
            query.setParameter("section", grade.getSection());
            grade = query.uniqueResult();
            students = grade.getStudents();
        } catch (Exception e) {
            throw new StudentException("Unable to retrieve students in grade " + grade.getGrade() + " and section " + grade.getSection(), e);
        }
        return students;
    }

    /*
     * <p>
     * This method retrieves all the students in each grade.
     * </p>
     *
     * @return List of grades along with its students.
     *
     * @throws Student exception when the grade and section can not be accessed.
     */
    public List<Grade> retrieveAllGradeDetails() {
        List<Grade> grades = new ArrayList<>();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            grades = session.createQuery("from Grade", Grade.class).list();
        } catch (Exception e) {
            throw new StudentException("Unable to retrieve grades ", e);
        }
        return grades;
    }

}
