package com.i2i.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.i2i.cms.dao.GradeDao;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.Student;

/**
 * This class provides methods for managing grades.
 * It handles operations like inserting grades, retrieving grade details, assigning sections
 * and counting students in a particular grade and section.
 */

public class GradeService {

    private GradeDao gradeDao = new GradeDao(); 

    /**
     * <p>
     * Adds a new grade and returns the grade details of the section with available capacity.
     * Inserts grade and section and checks for available sections with student count less than 
     * the specified maximum count of 2.
     * </p>
     *
     * @param gradeLevel
     *        The grade which has to be added.
     *        The grade should contain integer values from 1 to 12.
     *
     * @return Grade with the details of the grade, section and grade id.
     */
    public Grade addOrRetrieveGrade(int gradeLevel) {
        List<Grade> sections = new ArrayList<>();
        if (gradeDao.isGradeAvailable(gradeLevel)) {
            sections = gradeDao.retrieveSectionsByGrade(gradeLevel);
        }
        int maxStudents = 2;
        for (Grade grade : sections) {
            long studentCount = gradeDao.countStudentsInSection(grade.getGradeId());
            if (studentCount < maxStudents) {
                return grade;
            }
        }
        char newSection = sections.isEmpty() ? 'A' : (char) (sections.get(sections.size() - 1).getSection().charAt(0) + 1);
        Grade grade = new Grade();
        grade.setGrade(gradeLevel);
        grade.setSection(String.valueOf(newSection));
        return gradeDao.insertGrade(grade);   
    }

    /*
     * <p>
     * This method gets all the students in a particular grade and section.
     * </p>
     *
     * @param grade 
     *        The grade consists of grade level and section.
     *        The grade level should contain only numbers from 1 - 12.
     *        The section should contain only characters starting from A.
     *
     * @return The list of all students in a particular grade and section.
     */
    public Set<Student> getStudentsByGrade(Grade grade) {
        return gradeDao.retrieveStudentsByGrade(grade);
    }

}
