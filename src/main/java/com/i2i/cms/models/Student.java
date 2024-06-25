package com.i2i.cms.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.i2i.cms.utils.DateUtils;

/**
 * <p>
 * The Student class represents a student with various attributes and behaviors.
 * It includes basic details such as the student's name, date of birth, and marks.
 * Additionally, it stores information about the student's grade and personal details.
 * The class also keeps track of events the student participates in.
 * </p>
 */

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int studentId;

    @Column(name = "name", length = 30, nullable = false)
    private String studentName;

    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date studentDob;

    @Column(name = "marks", nullable = false)
    private int studentMarks;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "grade_id")
    private Grade grade;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_details_id")
    private PersonalDetails personalDetails;

    @ManyToMany(mappedBy = "students", fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Event> events = new HashSet<>();

    public Student() {}

    public Student(String studentName, Date studentDob, int studentMarks, Grade grade) {
        this.studentName = studentName;
        this.studentDob = studentDob;
        this.studentMarks = studentMarks;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getStudentDob() {
        return studentDob;
    }

    public void setStudentDob(Date studentDob) {
        this.studentDob = studentDob;
    }

    public int getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(int studentMarks) {
        this.studentMarks = studentMarks;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
        personalDetails.setStudent(this);
    }

    public void addEvent(Event event) {
        this.events.add(event);
        event.getStudents().add(this);
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\tStudent ID        : ").append(studentId)
            .append("\n\t\tStudent Name      : ").append(studentName)
            .append("\n\t\tStudent Dob       : ").append(studentDob)
            .append("\n\t\tStudent Marks     : ").append(studentMarks)
            .append("\n\t\tStudent Age       : ").append(DateUtils.calculatePeriodDifference(studentDob));
        return stringBuilder.toString();
    }
}
