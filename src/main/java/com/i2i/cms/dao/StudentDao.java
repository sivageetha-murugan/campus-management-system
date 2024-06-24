package com.i2i.cms.dao;

import java.util.ArrayList;
import java.util.List;

import com.i2i.cms.models.Event;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.models.Student;

/**
 * <p>
 * This class manages the student details and performs store, retrieve and delete operations on student details.
 * </p>
 */

public class StudentDao {

    /**
     * <p>
     * Insert the details of the student such as student id, name, dob, marks and grade details and 
     * the student's personal details.
     * </p>
     *
     * @param student 
     *        The student contains the details to be inserted such as student name, dob, marks, grade details 
     *        and personal details.
     *        The student name should contain only alphabets and should not contain any special 
     *        characters(/@.) or numbers.
     *        The student dob should contain a valid date in the format of 2003/02/01 and should not be in 
     *        any other format 01/02/2003.
     *        The student marks should contain only numbers of 3 digit in the range of (0 - 500).
     *        The grade details consists of grade id, grade and section to which the student has to be assigned.
     *        The personal details consists father name, mother name, phone number, city and nationality of the student.
     *
     * @return The student details with the generated student ID.
     *
     * @throws StudentException when the student details can not be inserted.
     */
    public Student insertStudentDetails(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return student;
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to insert the student details of student " + student.getStudentName(), e);
        }
    }

    /**
     * <p>
     * Retrieves the details of the student of particular id.
     * </p>
     *
     * @param studentId 
     *        The id of the student to retrieve the student details.
     *        The student id should contain only numbers.
     *
     * @return The student with the retrieved details, or null if no such student exists.
     *
     * @throws StudentException when the given student id can not be accessed.
     */
    public Student retrieveDetailsById(int studentId) {
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, studentId);
            return student;
        } catch (Exception e) {
            throw new StudentException("Unable to retrieve the details of student " + studentId, e);
        }
    }

    /**
     * <p>
     * Retrieves the details of all students.
     * The student details consist of student id, name dob, marks, grade details, personal details and 
     * event details a particular student is participating.
     * </p>
     *
     * @return The list of all student details.
     *
     * @throws StudentException when the student details can not be accessed.
     */
    public List<Student> retrieveAllStudentDetails() {
        List<Student> students = new ArrayList<>();
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            students = session.createQuery("from Student", Student.class).list();
        } catch (Exception e) {
            throw new StudentException("Unable to access student details", e);
        }
        return students;
    }

    /**
     * <p>
     * Deletes the details of a particular student.
     * </p>
     *
     * @param studentId 
     *        The unique identifier of the student to delete.
     *        The student id should contain only numbers.
     *
     * @return True if the student details was successfully deleted, false otherwise.
     *
     * @throws StudentException when the student id is invalid or can not be accessed.
     */
    public boolean deleteStudentById(int studentId) {
        Transaction transaction = null;
        try (Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            if (student != null) {
                for (Event event : student.getEvents()) {
                    event.getStudents().remove(student);
                    session.update(event);
                }
                session.delete(student);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to delete the student details of id " + studentId, e);
        }
        return false;
    }

    /**
     * <p>
     * Updates an existing student details that is stored.
     * </p>
     *
     * @param student 
     *        The student contains student id with the updated details such as name, dob, marks, 
     *        grade id and personal details.
     *        The student id should contain only numbers.
     *        The student name should contain only alphabets and should not contain any special characters(/@.) or numbers.
     *        The student dob should contain a valid date in the format of 2003/02/01 and 
     *        should not be in any other format 01/02/2003.
     *        The student marks should contain only numbers of 3 digit in the range of (0 - 500).
     *        The student grade contains the grade id, grade and section to which the student is assigned.
     *        The personal details consist of student's father name, mother name, phone number, city and nationality.
     *
     * @return True if the student details are updated, else return false.
     *
     * @throws StudentException when the student id to update can not be accessed or details not updated.
     */
    public boolean updateStudentDetails(Student student) {
         Transaction transaction = null;
         try (Session session = HibernateConnection.getSessionFactory().openSession()) {
             transaction = session.beginTransaction();
             session.update(student);
             transaction.commit();
             return true;
         } catch (Exception e) {
             if (null != transaction) {
                 transaction.rollback();
             }
             throw new StudentException("Unable to update the details of student " + student.getStudentId(), e);
         }
    }
}
