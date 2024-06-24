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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.i2i.cms.models.Student;

/**
 * <p>
 * The Event class represents a specific event with its details and includes the information about the students
 * enrolled in that event. 
 * The event consists of a unique ID for the event, event name, event venue, event date, event in-charge, event category.
 * This class is designed to encapsulate all relevant information about an event and to provide methods for accessing 
 * and modifying that information, as well as for associating students with the event.
 * </p>
 */

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int eventId;

    @Column(name = "name")
    private String eventName;

    @Column(name = "venue")
    private String eventVenue;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date eventDate;

    @Column(name = "incharge")
    private String eventIncharge;

    @Column(name = "category")
    private String eventCategory;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "students_events",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students = new HashSet<>();

    public Event() {}

    public Event(String eventName, String eventVenue, Date eventDate, String eventIncharge, String eventCategory) {
        this.eventName = eventName;
        this.eventVenue = eventVenue;
        this.eventDate = eventDate;
        this.eventIncharge = eventIncharge;
        this.eventCategory = eventCategory;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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
        return students;
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
