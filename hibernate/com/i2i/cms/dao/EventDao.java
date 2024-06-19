package com.i2i.cms.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.helper.HibernateConnection;
import com.i2i.cms.models.Event;
import com.i2i.cms.models.Grade;
import com.i2i.cms.models.PersonalDetails;
import com.i2i.cms.models.Student;

/*
 * This class is implemented to handle insert, retrieve and delete operations of the event details.
 * The event details consist of event id, name, date, venue, incharge name and category.
 */ 

public class EventDao {

    /**
     * <p>
     * This method inserts the details of an event such as event name, venue, date, incharge and category.
     * This assigns a unique event id to the event.
     * </p>
     *
     * @param event
     *        Event contains the details of the event such as event name, venue, date, incharge and category which has to be added.
     *        The event name contains only alphabets and does not contains numbers or special characters(/@.).
     *        The event venue can contain alpha numerical values Ex: APJ Block 2nd Floor.
     *        The event date should be in the fromat yyyy/mm/dd ex: 2024/10/10 and should not be in 01/02/2024.
     *        The event incharge can contain only alphabets and should not contain numbers and special characters(/@.).
     *        The event category can contain only alphabets and should not contain numbers and special characters(/@.).
     *
     * @return The event details with generated id that is inserted.
     *
     * @throws StudentException when the event details are not inserted. 
     */
    public Event insertEventDetails(Event event) {
        Transaction transaction = null;
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(event);
            transaction.commit();    
        } catch(Exception e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to insert event: " + event.getEventName(), e);
        }
        return event;  
    }

    /**
     * <p>
     * This method checks whether an event is already exist or not.
     * </p>
     *.
     * @param event
     *        Event contains the details of the event such as event id, name, venue, date, incharge and category which has to be checked.
     *        The event id can contain only numbers.
     *        The event name contains only alphabets and does not contains numbers or special characters(/@.).
     *        The event venue can contain alpha numerical values.
     *        The event date should be in the fromat yyyy/mm/dd ex: 2024/09/29 and should not be in 29/09/2024.
     *        The event incharge can contain only alphabets and should not contain numbers and special characters(/@.).
     *        The event category can contain only aiphabets and should not contain numbers and special characters(/@.).
     *
     * @return True if the event details are already exist, Else return false.
     *
     * @throws StudentException when the event details can not be retrieved. 
     */
    public boolean isEventExist(Event event) {
        Transaction transaction = null;
        boolean isEventExist = false;
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            String hql = "from Event e where e.eventName = :eventName";
            Query query = session.createQuery(hql, Event.class);
            query.setParameter("eventName", event.getEventName());
            Event existingEvent = (Event) query.uniqueResult();
            if(null != existingEvent) {
                isEventExist = true;
            }
            transaction.commit();
        } catch(Exception e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Cannot access event details of event: " + event.getEventName(), e);
        }
        return isEventExist;
    }

    /**
     * <p>
     * This method retrieves all the event details that exist.
     * </p>
     *
     * @return List of all the event details. 
     */
    public List<Event> retrieveAllEventDetails() {
        List<Event> events = new ArrayList<>();
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            events = session.createQuery("from Event", Event.class).list();
        } catch(Exception e) {
            return events;
        }
        return events;
    }

    /**
     * <p>
     * This method updates the details of the particular event by event id.
     * </p>
     *
     * @param event
     *        Event contains the details of the event to update such as event id, name, venue, date, incharge and category which has to be added.
     *        The event id can contain only numbers.
     *        The event name contains only alphabets and does not contains numbers or special characters(/@.).
     *        The event venue can contain alpha numerical values.
     *        The event date should be in the fromat yyyy/mm/dd ex: 2024/09/29 and should not be in 29/09/2024.
     *        The event incharge can contain only alphabets and should not contain numbers and special characters(/@.).
     *        The event category can contain only alphabets and should not contain numbers and special characters(/@.).
     *
     * @return True if the event details are updated.
     *
     * @throws StudentException when the event details can not be accesed and updated. 
     */
    public boolean updateEventDetails(Event event) {
        Transaction transaction = null;
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(event);
            transaction.commit();
            return true;
        } catch(Exception e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to update event: " + event.getEventId(), e);
        }
        
    }
    /**
     * <p>
     * This method deletes the details of the particular event by event id.
     * </p>
     *
     * @param eventId
     *        The unique indentifier for a particular event.
     *        The event id can contain only numbers.
     *
     * @return True if the event details are deleted, else false. 
     *
     * @throws StudentException when the event details can not be deleted for the particular event id.
     */
    public boolean deleteEventById(int eventId) {
        Transaction transaction = null;
        boolean isEventDeleted = false;
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, eventId);
            if(event != null) {
                session.delete(event);
                isEventDeleted = true;
                transaction.commit();
            }
        } catch(Exception e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Unable to delete event with ID: " + eventId, e);
        }
        return isEventDeleted;
    }

    /**
     * <p>
     * This method gets students in a particular event.
     * </p>
     *
     * @param eventId
     *        The unique identifier of the event for which the student details has to be fetched.
     *        The event id can contain only numbers.
     *
     * @return The details of the event and the list of students in that event.
     *
     * @throws StudentException If the event id can not be accessed.
     */
    public Event retrieveStudentsInEvent(int eventId) {
        Event event = new Event();
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            event = session.get(Event.class, eventId);
        } catch(Exception e) {
            throw new StudentException("Error while retrieving the details of the students in a particular event " + eventId, e);
        }
        return event;
    }

    /**
     * <p>
     * This method deletes the details of the student from particular event usind student and event ids.
     * </p>
     *
     * @param studentId
     *        The unique identifier of the student for which the event has to be deleted.
     *        The student id should contain only numbers.
     * @param eventId
     *        The unique identifier of the event for which the student has to be deleted.
     *        The event id can contain only numbers.
     *
     * @return True if the student id is deleted from a particular event, else return false.
     *
     * @throws StudentException If the student id and event id can not be accessed.
     */
    public boolean deleteStudentFromEvent(int studentId, int eventId) {
        Transaction transaction = null;
        boolean isStudentDeletedFromEvent = false;
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, eventId);
            Student student = session.get(Student.class, studentId);
            Set<Event> events = student.getEvents();
            events.remove(event);
            student.setEvents(events);
            session.update(student);
            transaction.commit();
        } catch(Exception e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Error occurred while deleting the student " + studentId + " from event " + eventId, e);
        }
        return isStudentDeletedFromEvent;
    }

    /**
     * <p>
     * This method inserts the the student id of the student and the event id the student is participating.
     * </p>
     *
     * @param studentId 
     *        The unique identifier of the student for which the event has to be assigned.
     *        The student id can contain only numbers.
     * @param eventId
     *        The unique identifier of an event for which the student has to be assigned.
     *        The event id can contain only numbers.
     *
     * @return True if the particular student is inserted to a particular event, else return false.
     *
     * @throws StudentException if the student and event can not be assigned.
     */
    public boolean insertStudentToEvent(int studentId, int eventId) {
        Transaction transaction = null;
        boolean isStudentAddedToEvent = false;
        try(Session session = HibernateConnection.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Event event = session.get(Event.class, eventId);
            Student student = session.get(Student.class, studentId);
            if(null != student && null != event) {
                event.addStudent(student);
                isStudentAddedToEvent = true;
            }
            session.update(event);
            transaction.commit();
        } catch(Exception e) {
            if(null != transaction) {
                transaction.rollback();
            }
            throw new StudentException("Error occurred while adding the student " + studentId + " to the event " + eventId, e);
        }
        return isStudentAddedToEvent;
    }
}