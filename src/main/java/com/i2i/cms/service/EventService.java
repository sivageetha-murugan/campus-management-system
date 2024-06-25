package com.i2i.cms.service;

import java.util.List;
import java.util.Set;

import com.i2i.cms.dao.EventDao;
import com.i2i.cms.models.Event;
import com.i2i.cms.models.Student;

/**
 * <p>
 * This class is implemented to handle the services to insert, retrieve and delete operations of the event details.
 * </p>
 */ 

public class EventService {

    private EventDao eventDao = new EventDao();

    /**
     * <p>
     * This method inserts the details of an event such as event name, venue, date, in-charge and category.
     * </p>
     *
     * @param event
     *        Event contains the details of the event such as event name, venue, date, 
     *        in-charge and category which has to be added.
     *        The event name contains only alphabets and does not contain numbers or special characters(/@.).
     *        The event venue can contain alphanumerical values Ex: APJ Block 2nd Floor.
     *        The event date should be in the format yyyy/mm/dd ex: 2024/10/10 and should not be in 01/02/2024.
     *        The event in-charge can contain only alphabets and should not contain numbers and special characters(/@.).
     *        The event category can contain only alphabets and should not contain numbers and special characters(/@.).
     *
     * @return The event details with generated id that is inserted, Else return false.
     */
    public Event addEvent(Event event) {
        return eventDao.insertEventDetails(event);       
    }

    /**
     * <p>
     * This method checks whether an event is already exist or not.
     * </p>
     *.
     * @param event
     *        Event contains the details of the event such as event id, name, venue, date, in-charge and category which has to be checked.
     *        The event id can contain only numbers.
     *        The event name contains only alphabets and does not contain numbers or special characters(/@.).
     *        The event venue can contain alphanumerical values.
     *        The event date should be in the format yyyy/mm/dd ex: 2024/09/29 and should not be in 29/09/2024.
     *        The event in-charge can contain only alphabets and should not contain numbers and special characters(/@.).
     *        The event category can contain only alphabets and should not contain numbers and special characters(/@.).
     *
     * @return True if the event details are already exist, Else return false.
     * 
     */
    public boolean isEventExist(Event event) {
        return eventDao.isEventExist(event);
    }

    /**
     * <p>
     * This method gets all the events and its details
     * </p>
     *
     * @return List of event details that exist. 
     */
    public List<Event> getAllEventDetails() {
        return eventDao.retrieveAllEventDetails();
    }

    /**
     * <p>
     * This method updates the details of the event that exist.
     * </p>
     *
     * @param updatedEventDetails
     *        This contains the details of the event to update such as event id, name, venue, date, in-charge and category which has to be added.
     *        The event id can contain only numbers.
     *        The event name contains only alphabets and does not contain numbers or special characters(/@.).
     *        The event venue can contain alphanumerical values.
     *        The event date should be in the format yyyy/mm/dd ex: 2024/09/29 and should not be in 29/09/2024.
     *        The event in-charge can contain only alphabets and should not contain numbers and special characters(/@.).
     *        The event category can contain only alphabets and should not contain numbers and special characters(/@.).
     *
     * @return The details of the updated event. 
     */
    public boolean updateEventDetails(Event updatedEventDetails) {
        return eventDao.updateEventDetails(updatedEventDetails);
    }

    /**
     * <p>
     * This method removes the details of a particular event by event id.
     * </p>
     *
     * @param eventId
     *        The unique identifier of an event which has to be deleted.
     *        The event id can contain only numbers.
     *
     * @return True if the event details are deleted. 
     */
    public boolean removeEventDetails(int eventId) {
        return eventDao.deleteEventById(eventId);
    }

    /**
     * <p>
     * This method gets the details of the students in a particular event.
     * </p>
     *
     * @param eventId
     *        The unique identifier of the event for which the students has to be fetched.
     *        The event id can contain only numbers.
     *
     * @return The event details with the list of students in the event.
     */
    public Set<Student> getStudentsInEvent(int eventId) {
        return eventDao.retrieveStudentsInEvent(eventId);
    }

    /**
     * <p>
     * This method removes a particular student from a particular event.
     * </p>
     *
     * @param studentId
     *        The unique identifier of the student for which the event has to be deleted.
     *        The student id should contain only numbers.
     * @param eventId
     *        The unique identifier of the event for which the student has to be deleted.
     *        The event id can contain only numbers.
     *
     * @return True if the particular student is deleted form a particular event, else return false.
     */
    public boolean removeStudentFromEvent(int studentId, int eventId) {
        return eventDao.deleteStudentFromEvent(studentId, eventId);
    }

    /**
     * <p>
     * This method is implemented to add a particular student to a particular event.
     * </p>
     *
     * @param studentId
     *        The unique identifier of the student for which the event has to be added.
     *        The student id should contain only numbers.
     * @param eventId
     *        The unique identifier of the event for which the student has to be added.
     *        The event id can contain only numbers.
     *
     * @return True if the particular student is added to particular event.
     */
    public boolean addStudentToEvent(int studentId, int eventId) {
        return eventDao.insertStudentToEvent(studentId, eventId);
    }

    /**
     * <p>
     * This method retrieves the details of a particular event by using the event id.
     * </P>
     *
     * @param eventId
     *        The unique identifier of the event for which the event details has to be fetched.
     *
     * @return event
     *         The details of the particular event.
     */
    public Event getEventDetailsById(int eventId) {
        return eventDao.retrieveEventById(eventId);
    }
}
