package com.i2i.cms.controller;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.Student;
import com.i2i.cms.service.GradeService;
import com.i2i.cms.utils.ValidateInputUtils;

/*
 * This class is implemented to handle the inputs from the user such as grade, section and grade id and
 * manages the outputs to be displayed.
 */

public class GradeController {

    private GradeService gradeService = new GradeService();
    private Scanner scanner = new Scanner(System.in);

    /**
     * <p>
     * This method is takes the grade and section as the input from the user and
     * displays the list of students in the particular grade and section.
     * </p>
     */
    public void displayStudentsByGrade() {
        System.out.println("Enter grade : ");
        int gradeLevel = scanner.nextInt();
        while (!ValidateInputUtils.isValidGrade(gradeLevel)) {
            System.out.println("Enter a valid grade(1 - 12) : ");
            gradeLevel = scanner.nextInt();
        }
        System.out.println("Enter section : ");
        String section = scanner.next();
        Grade grade = new Grade();
        grade.setGrade(gradeLevel);
        grade.setSection(section);
        try {
            Set<Student> students = gradeService.getStudentsByGrade(grade);
            for (Student student : students) {
                System.out.println(student);
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        } 
    }

}