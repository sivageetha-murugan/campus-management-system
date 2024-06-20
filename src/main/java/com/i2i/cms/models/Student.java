package com.i2i.cms.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.i2i.cms.models.Event;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.PersonalDetails;
import com.i2i.cms.utils.DateUtils;

/**
 * The Student class represents a student with various attributes and behaviors.
 * It includes basic details such as the student's name, date of birth, and marks.
 * Additionally, it stores information about the student's grade and personal details.
 * The class also keeps track of events the student participates in.
 */

public class Student {

    private int studentId;
    private String studentName;
    private Date studentDob;
    private int studentMarks;
    private Grade grade;
    private PersonalDetails personalDetails;
    private Set<Event> events;

    public Student() {}

    public Student(String studentName, Date studentDob, int studentMarks, Grade grade) {
        this.studentName = studentName;
        this.studentDob = studentDob;
        this.studentMarks = studentMarks;
        this.grade = grade;
        this.events = new HashSet<>();
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
        return null != events ? events : new HashSet<>();
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\t\tStudent ID        : ").append(studentId)
            .append("\n\t\tStudent Name      : ").append(studentName)
            .append("\n\t\tStudent Dob       : ").append(studentDob)
            .append("\n\t\tStudent Marks     : ").append(studentMarks)
            .append("\n\t\tStudent Age       : ").append(DateUtils.calculatePeriodDifference(studentDob));
        return stringBuilder.toString();
    }
}
   
