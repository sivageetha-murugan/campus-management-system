import java.util.Scanner;

import com.i2i.cms.controller.EventController;
import com.i2i.cms.controller.GradeController;
import com.i2i.cms.controller.StudentController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


/**
 * Main class for the Student Application.
 * 
 * This class serves as the entry point to the Student Management System application. 
 * It provides a menu-driven interface for managing student details, grades, and events.
 * The available functionalities include adding, updating, and removing student details,
 * displaying student information, managing grades and sections, and handling student 
 * participation in events.
 */

@SpringBootApplication
@ComponentScan(basePackages = "com.i2i.cms")
public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    @Autowired
    private StudentController studentController;
    @Autowired
    private GradeController gradeController;
    @Autowired
    private EventController eventController;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Main main = context.getBean(Main.class);
        main.start();
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
            logger.debug("Entering into campus management system");
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
                    logger.debug("Exiting from campus management system");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}