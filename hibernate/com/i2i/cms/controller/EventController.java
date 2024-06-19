package com.i2i.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.models.Event;
import com.i2i.cms.models.Student;
import com.i2i.cms.service.EventService;
import com.i2i.cms.utils.DateUtils;
import com.i2i.cms.utils.ValidateInputUtils;

/*
 * This class is implemented to handle the event details such as event id, name, date, venue, incharge and category.
 * This takes the inputs from the user and performs operations like add event, delete event, update event, 
 * remove event, add student to event based on the user request.
 */

public class EventController {

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
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * <p>
     * This method gets the event details such as event name, venue, date, incharge and category 
     * as the input and adds the event details. 
     * </p>
     */
    public void addEventDetails() {
        System.out.println("Enter event name: ");
        String eventName = scanner.next();
        while (!ValidateInputUtils.isValidString(eventName)) {
            System.out.println("Enter a valid event name : ");
            eventName = scanner.next();
        }
        System.out.println("Enter event venue: ");
        String eventVenue = scanner.next();
        System.out.println("Enter event date (YYYY/MM/DD): ");
        String inputDate = scanner.next();
        Date eventDate = DateUtils.validateDate(inputDate);
        while (eventDate == null) {
            System.out.println("Enter a valid date (YYYY/MM/DD): ");
            inputDate = scanner.next();
            eventDate = DateUtils.validateDate(inputDate);
        }
        System.out.println("Enter event incharge: ");
        String eventIncharge = scanner.next();
        while (!ValidateInputUtils.isValidString(eventIncharge)) {
            System.out.println("Enter a valid incharge name(alphabets only) : ");
            eventIncharge = scanner.next();
        }
        System.out.println("Enter event category: ");
        String eventCategory = scanner.next();
        while (!ValidateInputUtils.isValidString(eventCategory)) {
            System.out.println("Enter a valid event category(alphabets only) : ");
            eventCategory = scanner.next();
        }
        Event event = new Event(eventName, eventVenue, eventDate, eventIncharge, eventCategory);
        try {
            Event insertedEvent = eventService.addEvent(event);
            if (null != insertedEvent) {
                System.out.println(insertedEvent);
            } else {
                System.out.println("Event details not added.");
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * <p>
     * This method displays all the available events details.
     * </p>
     */
    public void displayAllEvents() {
        try {
            List<Event> events = eventService.getAllEventDetails();
            if (events.isEmpty()) {
                System.out.println("No events available.");
            } else {
                for (Event event : events) {
                    System.out.println(event);
                }
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
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
            Event event = eventService.getStudentsInEvent(eventId);
            System.out.println(event);
        } catch (StudentException e) {
            System.out.println(e.getMessage());
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
        System.out.println("Enter new event name: ");
        String eventName = scanner.next();
        System.out.println("Enter new event venue: ");
        String eventVenue = scanner.next();
        System.out.println("Enter new event date (YYYY/MM/DD): ");
        String inputEventDate = scanner.next();
        Date eventDate = DateUtils.validateDate(inputEventDate);
        while (null == eventDate) {
            System.out.println("Enter a valid date (YYYY/MM/DD): ");
            inputEventDate = scanner.next();
            eventDate = DateUtils.validateDate(inputEventDate);
        }
        System.out.println("Enter new event incharge: ");
        String eventIncharge = scanner.next();
        System.out.println("Enter new event category: ");
        String eventCategory = scanner.next();
        Event updatedEvent = new Event(eventName, eventVenue, eventDate, eventIncharge, eventCategory);
        updatedEvent.setEventId(eventId);
        try {
            boolean isEventUpdated = eventService.updateEventDetails(updatedEvent);
            if (isEventUpdated) {
                System.out.println("Event details updated successfully.");
                System.out.println(updatedEvent);
            } else {
                System.out.println("Failed to update event details.");
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
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
        int eventIdToRemove = scanner.nextInt();
        try {
            if (eventService.removeEventDetails(eventIdToRemove)) {
                System.out.println("Event removed successfully.");
            } else {
                System.out.println("Event not removed");
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets the student id from the user.
     * Displays the avalible events and gets the event id to participate from the student.
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
                boolean isStudentAddedToEvent = eventService.addStudentToEvent(studentId, eventId);
                if (isStudentAddedToEvent) {
                    System.out.println("Student added to event successfully.");
                } else {
                    System.out.println("Failed to add student to event.");
                }
                System.out.println("Do you want to participate in another event (Y / N) : ");
                loop = scanner.next();
            } while (loop.equalsIgnoreCase("Y"));
        } catch (StudentException e) {
            System.out.println(e.getMessage());
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
        try {
            boolean result = eventService.removeStudentFromEvent(studentId, eventId);
            if (result) {
                System.out.println("Student removed from event successfully.");
            } else {
                System.out.println("Failed to remove student from event.");
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }
}
    
