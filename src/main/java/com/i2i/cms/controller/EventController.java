package com.i2i.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.models.Event;
import com.i2i.cms.models.Student;
import com.i2i.cms.service.EventService;
import com.i2i.cms.utils.DateUtils;
import com.i2i.cms.utils.ValidateInputUtils;

/**
 * <p>
 * This class is implemented to handle the event details such as event id, name, date, venue, in-charge and category.
 * This takes the inputs from the user and performs operations like add event, delete event, update event, 
 * remove event, add student to event based on the user request.
 * </p>
 */

public class EventController {

    private static final Logger logger = LoggerFactory.getLogger(EventController.class);
    private EventService eventService = new EventService();
    private Scanner scanner = new Scanner(System.in);

    /**
     * <p>
     * This method displays the operations to be performed on event details.
     * This consists of add event details, display events, display students in particular event,
     * update event details and remove event.
     * </p>
     */
    public void manageEvents() {
        while (true) {
            logger.debug("Managing events");
            System.out.println("\n\t----------------------------------------------------------");
            System.out.println("\t                      Manage Events                      ");
            System.out.println("\t----------------------------------------------------------");
            System.out.println("\n\t\t1. Add Event");
            System.out.println("\t\t2. Display All Events");
            System.out.println("\t\t3. Display Students in a Particular Event");
            System.out.println("\t\t4. Update Event Details");
            System.out.println("\t\t5. Remove Event Details");
            System.out.println("\t\t6. Exit");
            System.out.println("\t----------------------------------------------------------");
            System.out.print("\n\tEnter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addEventDetails();
                    break;
                case 2:
                    displayAllEvents();
                    break;
                case 3:
                    displayStudentsInEvent();
                    break;
                case 4:
                    updateEventDetails();
                    break;
                case 5:
                    removeEventDetails();
                    break;
                case 6:
                    logger.debug("Exiting from managing events");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * <p>
     * This method gets the event details such as event name, venue, date, in-charge and category
     * as the input and adds the event details. 
     * </p>
     */
    public void addEventDetails() {
        System.out.println("Enter event name: ");
        scanner.nextLine();
        String eventName = scanner.nextLine();
        logger.info("Adding event details of event {}", eventName);
        while (!ValidateInputUtils.isValidString(eventName)) {
            System.out.println("Enter a valid event name : ");
            eventName = scanner.nextLine();
        }
        System.out.println("Enter event venue: ");
        String eventVenue = scanner.nextLine();
        System.out.println("Enter event date (YYYY/MM/DD): ");
        String inputDate = scanner.nextLine();
        Date eventDate = DateUtils.validateDate(inputDate);
        while (eventDate == null) {
            System.out.println("Enter a valid date (YYYY/MM/DD): ");
            inputDate = scanner.nextLine();
            eventDate = DateUtils.validateDate(inputDate);
        }
        System.out.println("Enter event incharge: ");
        String eventIncharge = scanner.nextLine();
        while (!ValidateInputUtils.isValidString(eventIncharge)) {
            System.out.println("Enter a valid in-charge name(alphabets only) : ");
            eventIncharge = scanner.nextLine();
        }
        System.out.println("Enter event category: ");
        String eventCategory = scanner.nextLine();
        while (!ValidateInputUtils.isValidString(eventCategory)) {
            System.out.println("Enter a valid event category(alphabets only) : ");
            eventCategory = scanner.nextLine();
        }
        Event event = new Event(eventName, eventVenue, eventDate, eventIncharge, eventCategory);
        try {
            event = eventService.addEvent(event);
            if (null != event) {
                System.out.println(event);
                logger.info("Inserted the details successfully for event {}", event.getEventName());
            } else {
                System.out.println("Event details not added.");
                logger.info("Event details not inserted for event {}", eventName);
            }
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * <p>
     * This method displays all the available events details.
     * </p>
     */
    public void displayAllEvents() {
        logger.info("Displaying all event details");
        try {
            List<Event> events = eventService.getAllEventDetails();
            if (events.isEmpty()) {
                System.out.println("No events available.");
                logger.info("No events available");
            } else {
                for (Event event : events) {
                    System.out.println(event);
                }
                logger.info("Displayed all event details");
            }
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets the event id as the input from the user.
     * Displays the event details of the particular event and the list of students in that event.
     * </p>
     */
    public void displayStudentsInEvent() {
        try {
            displayAllEvents();
            System.out.println("Enter event id: ");
            int eventId = scanner.nextInt();
            logger.info("Displaying students in a particular event of event id {}", eventId);
            Set<Student> students = eventService.getStudentsInEvent(eventId);
            if (students.isEmpty()) {
                System.out.println("No students enrolled");
                logger.info("No students enrolled in event id {}", eventId);
                return;
            }
            for (Student student : students) {
                System.out.println(student);
            }
            logger.info("Displayed students in a particular event id {}", eventId);
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets the event details to update from the user.
     * Updates the event details and display the updated event details.
     * </p>
     */
    public void updateEventDetails() {
        System.out.println("Enter event id to update: ");
        int eventId = scanner.nextInt();
        logger.info("Updating event details of id {}", eventId);
        scanner.nextLine();
        try {
            Event existingEvent = eventService.getEventDetailsById(eventId);
            if (null == existingEvent) {
                System.out.println("Event does not exist");
                return;
            }
            System.out.println("Enter new event name (or press n to keep the current): ");
            String eventName = scanner.nextLine();
            if (!eventName.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(eventName)) {
                    System.out.println("Enter a valid event name : ");
                    eventName = scanner.nextLine();
                }
                existingEvent.setEventName(eventName);
            }
            System.out.println("Enter new event venue (or press n to keep the current): ");
            String eventVenue = scanner.nextLine();
            if (!eventVenue.equalsIgnoreCase("n")) {
                existingEvent.setEventVenue(eventVenue);
            }
            System.out.println("Enter new event date (YYYY/MM/DD) or press n to keep the current: ");
            String inputEventDate = scanner.nextLine();
            if (!inputEventDate.equalsIgnoreCase("n")) {
                Date eventDate = DateUtils.validateDate(inputEventDate);
                while (null == eventDate) {
                    System.out.println("Enter a valid date (YYYY/MM/DD): ");
                    inputEventDate = scanner.nextLine();
                    eventDate = DateUtils.validateDate(inputEventDate);
                }
                existingEvent.setEventDate(eventDate);
            }
            System.out.println("Enter new event inc-harge or press n to keep the current: ");
            String eventIncharge = scanner.nextLine();
            if (!eventIncharge.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(eventIncharge)) {
                    System.out.println("Enter a valid incharge name(alphabets only) : ");
                    eventIncharge = scanner.nextLine();
                }
                existingEvent.setEventIncharge(eventIncharge);
            }
            System.out.println("Enter new event category or press n to keep the current: ");
            String eventCategory = scanner.nextLine();
            if(!eventCategory.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(eventCategory)) {
                    System.out.println("Enter a valid event category(alphabets only) : ");
                    eventCategory = scanner.nextLine();
                }
                existingEvent.setEventCategory(eventCategory);
            }
            boolean isEventUpdated = eventService.updateEventDetails(existingEvent);
            if (isEventUpdated) {
                System.out.println("Event details updated successfully.");
                System.out.println(existingEvent);
                logger.info("Updated the event details of event id {}", eventId);
            } else {
                System.out.println("Failed to update event details.");
                logger.info("Event details not updated for event id {}", eventId);
            }
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets the event id to remove.
     * Removes event details corresponding to the input event id.
     * </p>
     */
    public void removeEventDetails() {
        System.out.println("Enter event id to remove: ");
        int eventId = scanner.nextInt();
        logger.info("Removing event details of Id {}", eventId);
        try {
            if (eventService.removeEventDetails(eventId)) {
                System.out.println("Event removed successfully.");
                logger.info("Event details removed for id {}", eventId);
            } else {
                System.out.println("Event not removed");
                logger.info("Event details not removed for event id {}", eventId);
            }
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets the student id from the user.
     * Displays the available events and gets the event id to participate from the student.
     * Adds the student to the particular event by student and event id.
     * </p>
     */
    public void addStudentToEvents() {
        System.out.println("Enter student id : ");
        int studentId = scanner.nextInt();
        try {
            System.out.println("Available events:");
            displayAllEvents();
            String loop = "";
            do {
                System.out.println("Enter event id to participate: ");
                int eventId = scanner.nextInt();
                logger.info("Adding student {} to event {}", studentId, eventId);
                boolean isStudentAddedToEvent = eventService.addStudentToEvent(studentId, eventId);
                if (isStudentAddedToEvent) {
                    System.out.println("Student added to event successfully.");
                    logger.info("Added student {} to event {}", studentId, eventId);
                } else {
                    System.out.println("Failed to add student to event.");
                    logger.info("Failed to add student {} to event {}", studentId, eventId);
                }
                System.out.println("Do you want to participate in another event (Y / N) : ");
                loop = scanner.next();
            } while (loop.equalsIgnoreCase("Y"));
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets student id and event id and removes the particular student from a particular event.
     * </p>
     */
    public void removeStudentFromEvent() {
        System.out.println("Enter student id: ");
        int studentId = scanner.nextInt();
        System.out.println("Enter event id: ");
        int eventId = scanner.nextInt();
        logger.info("Removing student {} from event {}", studentId, eventId);
        try {
            boolean result = eventService.removeStudentFromEvent(studentId, eventId);
            if (result) {
                System.out.println("Student removed from event successfully.");
                logger.info("Removed student {} from event {}", studentId, eventId);
            } else {
                System.out.println("Failed to remove student from event.");
                logger.info("Failed to remove student {} from event {}", studentId, eventId);
            }
        } catch (StudentException e) {
            logger.error(e.getMessage());
        }
    }
}
    
