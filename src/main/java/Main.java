import java.util.Scanner;

import com.i2i.cms.controller.EventController;
import com.i2i.cms.controller.GradeController;
import com.i2i.cms.controller.StudentController;

/**
 * Main class for the Student Application.
 * 
 * This class serves as the entry point to the Student Management System application. 
 * It provides a menu-driven interface for managing student details, grades, and events.
 * The available functionalities include adding, updating, and removing student details,
 * displaying student information, managing grades and sections, and handling student 
 * participation in events.
 */

public class Main {

    private StudentController studentController = new StudentController();
    private GradeController gradeController = new GradeController();
    private EventController eventController = new EventController();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        Main mainController = new Main();
        mainController.start();
    }

    /**
     * Starts the Student Management System application.
     * 
     * Displays a menu of options to the user and processes their choices in a loop.
     * The user can perform various operations related to students, grades, and events
     * until they choose to exit the application.
     */
 
    public void start() {
        while (true) {
            System.out.println("\n\t----------------------------------------------------------");
            System.out.println("\t                   STUDENT APPLICATION                    ");
            System.out.println("\t----------------------------------------------------------");
            System.out.println("\n\t\t1. Add Student");
            System.out.println("\t\t2. Get Student Details by ID");
            System.out.println("\t\t3. Update Student Details");
            System.out.println("\t\t4. Display All Student details");
            System.out.println("\t\t5. Display Students by Grade and Section");
            System.out.println("\t\t6. Remove Student Details");
            System.out.println("\t\t7. Manage Events");
            System.out.println("\t\t8. Participate in Events");
            System.out.println("\t\t9. Remove Student From an Event");
            System.out.println("\t\t10. Display all grades");
            System.out.println("\t\t11. Exit");
            System.out.println("\n\t----------------------------------------------------------\n");
            System.out.print("\n\tEnter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    studentController.addStudentDetails();
                    break;
                case 2:
                    studentController.getStudentDetailsById();
                    break;
                case 3:
                    studentController.updateStudentDetails();
                    break;
                case 4:
                    studentController.displayAllStudents();
                    break;
                case 5:
                    gradeController.displayStudentsByGrade();
                    break;
                case 6:
                    studentController.deleteStudentById();
                    break;
                case 7:
                    eventController.manageEvents();
                    break;
                case 8:
                    eventController.addStudentToEvents();
                    break;
                case 9:
                    eventController.removeStudentFromEvent();
                    break;
                case 10:
                    gradeController.displayAllGradesWithStudents();
                    break;
                case 11:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}