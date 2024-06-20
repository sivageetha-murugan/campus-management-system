package com.i2i.cms.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.i2i.cms.models.Grade;
import com.i2i.cms.models.PersonalDetails;
import com.i2i.cms.models.Student;
import com.i2i.cms.utils.DateUtils;

/**
 * The Event class represents a specific event with it details and includes the information about the students
 * entrolled in that event. 
 * The event consists of unique ID for the event, event name, event venue, event date, event incharge, event category.
 * This class is designed to encapsulate all relevant information about a event and to provide methods for accessing 
 * and modifying that information, as well as for associating students with the event.
 */

public class Event {

    private int eventId;
    private String eventName;
    private String eventVenue;
    private Date eventDate;
    private String eventIncharge;
    private String eventCategory;
    private Set<Student> students;

    public Event() {}

    public Event(String eventName, String eventVenue, Date eventDate, String eventIncharge, String eventCategory) {
        this.eventName = eventName;
        this.eventVenue = eventVenue;
        this.eventDate = eventDate;
        this.eventIncharge = eventIncharge;
        this.eventCategory = eventCategory;
        this.students = new HashSet<>();
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventIncharge() {
        return eventIncharge;
    }
   
    public void setEventIncharge(String eventIncharge) {
        this.eventIncharge = eventIncharge;
    }

    public String getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(String eventCategory) {
        this.eventCategory = eventCategory;
    }

    public void addStudent(Student student) {
        this.students.add(student);
        student.getEvents().add(this); 
    }

    public Set<Student> getStudents() {
        return null != students ? students : new HashSet<>();
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\tEvent id       : ").append(eventId)
            .append("\n\tEvent name     : ").append(eventName)
            .append("\n\tEvent venue    : ").append(eventVenue)
            .append("\n\tEvent date     : ").append(eventDate)
            .append("\n\tEvent incharge : ").append(eventIncharge)
            .append("\n\tEvent category : ").append(eventCategory);
        return stringBuilder.toString();
    }
}
