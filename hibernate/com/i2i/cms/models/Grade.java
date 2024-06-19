package com.i2i.cms.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.i2i.cms.models.Student;
import com.i2i.cms.utils.DateUtils;

/**
 * The Grade class represents a specific grade level with a section and includes the information about the students 
 * enrolled in that grade. 
 * The grade consists of unique ID for the grade, the grade number, and the section identifier.
 * This class is designed to encapsulate all relevant information about a grade and to provide methods for accessing 
 * and modifying that information, as well as for associating students with the grade.
 */

public class Grade {

    private int gradeId;
    private int grade;
    private String section;
    private Set<Student> students;

    public Grade() {}

    public Grade(int gradeId, int grade, String section) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.section = section;
        this.students = new HashSet<>();
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Set<Student> getStudents() {
        return null != students ? students : new HashSet<>();
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setGrade(this);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Grade             : ").append(grade)
            .append("\nSection           : ").append(section)
            .append("\n\t\tStudents          : ");
        for(Student student : students) {
            stringBuilder.append("\n\n\t\tStudent ID        : ").append(student.getStudentId())
                .append("\n\t\tStudent Name      : ").append(student.getStudentName())
                .append("\n\t\tStudent Dob       : ").append(student.getStudentDob())
                .append("\n\t\tStudent Marks     : ").append(student.getStudentMarks())
                .append("\n\t\tStudent Age       : ").append(DateUtils.calculatePeriodDifference(student.getStudentDob()));
        }
        return stringBuilder.toString();
    }
}
