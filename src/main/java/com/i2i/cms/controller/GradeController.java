package com.i2i.cms.controller;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.Student;
import com.i2i.cms.service.GradeService;
import com.i2i.cms.utils.ValidateInputUtils;

/**
 * <p>
 * This class is implemented to handle the inputs from the user such as grade, section and grade id and
 * manages the outputs to be displayed.
 * </p>
 */

@RestController
@Component
public class GradeController {

    private static final Logger logger = LogManager.getLogger(GradeController.class);

    @Autowired
    private GradeService gradeService;

    private Scanner scanner = new Scanner(System.in);

    /**
     * <p>
     * This method is takes the grade and section as the input from the user and
     * displays the list of students in the particular grade and section.
     * </p>
     */
    public void displayStudentsByGrade() {
        logger.debug("Entering into display students by grade");
        System.out.println("Enter grade : ");
        int gradeLevel = scanner.nextInt();
        while (!ValidateInputUtils.isValidGrade(gradeLevel)) {
            System.out.println("Enter a valid grade(1 - 12) : ");
            gradeLevel = scanner.nextInt();
        }
        System.out.println("Enter section : ");
        String section = scanner.next();
        logger.info("Displaying students in grade {} and section {}", gradeLevel, section);
        Grade grade = new Grade();
        grade.setGrade(gradeLevel);
        grade.setSection(section);
        try {
            Set<Student> students = gradeService.getStudentsByGrade(grade);
            if (students.isEmpty()) {
                System.out.println("\t\tNo students in this section");
                logger.info("No students in grade {} and section {}", gradeLevel, section);
                return;
            }
            for (Student student : students) {
                System.out.println(student);
            }
            logger.info("Displayed students in grade {} and section {}", gradeLevel, section);
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
        logger.debug("Exiting from display students by grade");
    }

    /**
     * <p>
     * This method displays all the grades that are exist along with it student details.
     * </p>
     */
    public void displayAllGradesWithStudents() {
        logger.debug("Entering into display all grades with students");
        try {
            logger.info("Displaying all grades and its student details");
            List<Grade> grades = gradeService.getAllGradeDetails();
            if (grades.isEmpty()) {
                System.out.println("No grades enrolled");
                logger.info("No grades available");
                return;
            }
            for (Grade grade : grades) {
                System.out.println("\n\tGrade   : " + grade.getGrade());
                System.out.println("\tSection : " + grade.getSection());
                if (grade.getStudents().isEmpty()) {
                    System.out.println("\t\tNo Students in this section");
                } else {
                    for (Student student : grade.getStudents()) {
                        System.out.println(student);
                    }
                }
                System.out.println("\n");
            }
            logger.info("Displayed students in all grades and sections");
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
        logger.debug("Exiting from display all grades with students");
    }

}