package com.i2i.cms.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.i2i.cms.models.Student;

/**
 * <p>
 * The Grade class represents a specific grade level with a section and includes the information about the students 
 * enrolled in that grade. 
 * The grade consists of unique ID for the grade, the grade number, and the section identifier.
 * This class is designed to encapsulate all relevant information about a grade and to provide methods for accessing 
 * and modifying that information, as well as for associating students with the grade.
 * </p>
 */

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int gradeId;

    @Column(name = "grade", nullable = false)
    private int grade;

    @Column(name = "section", length = 1, nullable = false)
    private String section;

    @OneToMany(mappedBy = "grade", fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    public Grade() {}

    public Grade(int grade, String section) {
        this.grade = grade;
        this.section = section;
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
        return students;
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
        stringBuilder.append("\t\tGrade             : ").append(grade)
            .append("\n\t\tSection           : ").append(section);
        return stringBuilder.toString();
    }
}
