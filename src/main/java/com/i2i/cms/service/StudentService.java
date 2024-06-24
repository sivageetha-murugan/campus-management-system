package com.i2i.cms.service;

import java.util.Date;
import java.util.List;

import com.i2i.cms.dao.StudentDao;
import com.i2i.cms.models.Event;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.PersonalDetails;
import com.i2i.cms.models.Student;
import com.i2i.cms.service.GradeService;

/**
 * <p>
 * This class provides services such as inserting student details and accessing student details.
 * </p>
 */

public class StudentService {

    private StudentDao studentDao = new StudentDao();
    private GradeService gradeService = new GradeService();

    /**
     * <p>
     * This method adds the details of the student such as student name, dob, marks, grade and personal details.
     * </p>
     * 
     * @param name 
     *        The name of the student.
     *        The student name should contain only alphabets and should not contain 
     *        any special characters(/@.) or numbers.
     * @param dob 
     *        The date of birth of the student.
     *        The student dob should contain a valid date in the format of 2003/09/29 and should not be in 
     *        any other format 29/09/2003.
     * @param marks 
     *        The marks of the student.
     *        The student marks should contain only numbers of 3 digit in the range of (0 - 500).
     * @param gradeLevel 
     *        The grade of the student to which the student has to be assigned.
     *        The grade should contain only numbers from 1 to 12.
     * @param personalDetails
     *        The personal details of the student contains student's father name, mother name, phone number, 
     *        city of residence and nationality.
     *
     * @return The newly added student details, or null if the addition failed.
     */
    public Student addStudent(String name, Date dob, int marks, int gradeLevel, PersonalDetails personalDetails) {
       Grade grade = gradeService.addOrRetrieveGrade(gradeLevel);
       if (null != grade) {
            Student student = new Student(name, dob, marks, grade);
            student.setPersonalDetails(personalDetails);
            student.setGrade(grade);
            return studentDao.insertStudentDetails(student);
       }
       return null;
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
     */
    public Student getStudentById(int studentId) {
        return studentDao.retrieveDetailsById(studentId);
    }

    /**
     * <p>
     * Retrieves the details of all students.
     * The student details consist of student id, name dob, marks, grade details, personal details and 
     * event details a particular student is participating.
     * </p>
     *
     * @return The list of all student details.
     */
    public List<Student> getAllStudents() {
        return studentDao.retrieveAllStudentDetails();
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
     */
    public boolean deleteStudentById(int studentId) {
        return studentDao.deleteStudentById(studentId);
    }

    /**
     * <p>
     * Updates an existing student details that is stored by the given value.
     * </p>
     *
     * @param student 
     *        The student containing the updated details such as student id, name, dob, marks, grade id and personal details.
     *        The student id should contain only numbers.
     *        The student name should contain only alphabets and should not contain any special characters(/@.) or numbers.
     *        The student dob should contain a valid date in the format of 2003/02/01 and should not be in any other format 01/02/2003.
     *        The student marks should contain only numbers of 3 digit in the range of (0 - 500).
     *        The student grade contains the grade id, grade and section to which the student is assigned.
     *        The personal details consist of student's father name, mother name, phone number, city and nationality.
     *
     * @return True if the student details are updated, else return false.
     */
    public boolean updateStudent(Student student) {
        return studentDao.updateStudentDetails(student);
    }

}
