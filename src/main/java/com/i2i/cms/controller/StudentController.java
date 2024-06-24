package com.i2i.cms.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.i2i.cms.exception.StudentException;
import com.i2i.cms.models.PersonalDetails;
import com.i2i.cms.models.Student;
import com.i2i.cms.service.StudentService;
import com.i2i.cms.utils.DateUtils;
import com.i2i.cms.utils.ValidateInputUtils;

/**
 * <p>
 * This class is implemented to handle the inputs from the user and the outputs to be displayed.
 * The input consists of student id, name, dob, marks, grade and their personal details such as 
 * father name, mother name, phone number, city and nationality.
 * This class performs the certain operations on the input data such as add, retrieve, update and delete.
 * </p>
 */

public class StudentController {

    private StudentService studentService = new StudentService();
    private Scanner scanner = new Scanner(System.in);

    /**
     * <p>
     * This method gets student details such as student name, dob, marks, grade, father name, mother name, 
     * phone number, city and nationality as the input from the user.
     * Stores the input from the user and display the stored details with generated student id and section.
     * </p>
     */
    public void addStudentDetails() {
        try {
            System.out.println("Enter student name: ");
            String name = scanner.next();
            while (!ValidateInputUtils.isValidString(name)) {
                System.out.println("Enter a valid name : ");
                name = scanner.next();
            }
            System.out.println("Enter student date of birth (YYYY/MM/DD): ");
            String dob = scanner.next();
            Date dateOfBirth = DateUtils.validateDate(dob);
            while (null == dateOfBirth) {
                System.out.println("Enter a valid date (YYYY/MM/DD): ");
                dob = scanner.next();
                dateOfBirth = DateUtils.validateDate(dob);
            }
            System.out.println("Enter student marks: ");
            int marks = scanner.nextInt();
            System.out.println("Enter student grade: ");
            int gradeLevel = scanner.nextInt();
            while (!ValidateInputUtils.isValidGrade(gradeLevel)) {
                System.out.println("Enter a valid grade(1-12) : ");
                gradeLevel = scanner.nextInt();
            }
            System.out.println("Enter Student's Father Name : ");
            String fatherName = scanner.next();
            while (!ValidateInputUtils.isValidString(fatherName)) {
                System.out.println("Enter a valid name : ");
                fatherName = scanner.next();
            }
            System.out.println("Enter Student's Mother Name : ");
            String motherName = scanner.next();
            while (!ValidateInputUtils.isValidString(motherName)) {
                System.out.println("Enter a valid name : ");
                motherName = scanner.next();
            }
            System.out.println("Enter Contact No : ");
            String phoneNumber = scanner.next();
            while (!ValidateInputUtils.isValidPhoneNumber(phoneNumber)) {
                System.out.println("Enter a valid phone number : ");
                phoneNumber = scanner.next();
            }
            System.out.println("Enter City : ");
            String city = scanner.next();
            while (!ValidateInputUtils.isValidString(city)) {
                System.out.println("Enter a valid city name(only characters) : ");
                city = scanner.next();
            }
            System.out.println("Enter Nationality : ");
            String nationality = scanner.next();
            while (!ValidateInputUtils.isValidString(nationality)) {
                System.out.println("Enter a valid string for nationality  : ");
                nationality = scanner.next();
            }
            PersonalDetails personalDetails = new PersonalDetails(fatherName, motherName, phoneNumber, city, nationality);
            Student student = studentService.addStudent(name, dateOfBirth, marks, gradeLevel, personalDetails);
            System.out.println(student);
            System.out.println(student.getGrade());
            System.out.println(student.getPersonalDetails());
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     * This method gets the student id as the input from the user.
     * Gets the student details of the input student id.
     * </p>
     */
    public void getStudentDetailsById() {
        System.out.println("Enter student ID: ");
        int studentId = scanner.nextInt();
        try {
            Student student = studentService.getStudentById(studentId);
            if (null != student) {
                System.out.println(student);
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }  
    }

    /**
     * <p>
     * This method displays all the student details that are stored.
     * </p>
     */
    public void displayAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            if (!students.isEmpty()) {
                for (Student student : students) {
                    System.out.println(student);
                    System.out.println(student.getGrade());
                    System.out.println(student.getPersonalDetails());
                }
            } else {
                System.out.println("No students found.");
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     * This method deletes the details of the student of particular id.
     * </p>
     */
    public void deleteStudentById() {
        System.out.println("Enter student ID to delete: ");
        int studentId = scanner.nextInt();
        try {
            boolean isDeleted = studentService.deleteStudentById(studentId);
            if (isDeleted) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * <p>
     * This method updates the details of the student of particular id.
     * </p>
     */
    public void updateStudentDetails() {
        System.out.println("Enter student ID to update: ");
        int studentId = scanner.nextInt();
        try {
            Student existingStudent = studentService.getStudentById(studentId);
            if (null == existingStudent) {
                System.out.println("Student not found with ID: " + studentId);
                return;
            }
            System.out.println("Enter new student name (or press n to keep the current): ");
            String name = scanner.next();
            if (!name.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(name)) {
                    System.out.println("Enter a valid name : ");
                    name = scanner.next();
                }
                existingStudent.setStudentName(name);
            }
            System.out.println("Enter new student date of birth (DD/MM/YYYY) (or press n to keep the current): ");
            String dob = scanner.next();
            if (!dob.equalsIgnoreCase("n")) {
                Date dateOfBirth = DateUtils.validateDate(dob);
                while (null == dateOfBirth) {
                    System.out.println("Enter a valid date (YYYY/MM/DD): ");
                    dob = scanner.next();
                    dateOfBirth = DateUtils.validateDate(dob);
                }
                existingStudent.setStudentDob(dateOfBirth);
            }
            System.out.println("Enter new student marks (or press 1 to keep the current): ");
            int marks = scanner.nextInt();
            if (marks != 1) {
                existingStudent.setStudentMarks(marks);
            }
            PersonalDetails personalDetails = existingStudent.getPersonalDetails();
            System.out.println("Enter new father's name (or press n to keep the current): ");
            String fatherName = scanner.next();
            if (!fatherName.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(fatherName)) {
                    System.out.println("Enter a valid name : ");
                    fatherName = scanner.next();
                }
                personalDetails.setFatherName(fatherName);
            }
            System.out.println("Enter new mother's name (or press n to keep the current): ");
            String motherName = scanner.next();
            if (!motherName.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(motherName)) {
                    System.out.println("Enter a valid name : ");
                    motherName = scanner.next();
                }
                personalDetails.setMotherName(motherName);
            }
            System.out.println("Enter new phone number (or press n to keep the current): ");
            String phoneNumber = scanner.next();
            if (!phoneNumber.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Enter a valid phone number : ");
                    phoneNumber = scanner.next();
                }
                personalDetails.setPhoneNumber(phoneNumber);
            }
            System.out.println("Enter new city (or press n to keep the current): ");
            String city = scanner.next();
            if (!city.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(city)) {
                    System.out.println("Enter a valid city name(only characters) : ");
                    city = scanner.next();
                }
                personalDetails.setCity(city);
            }

            System.out.println("Enter new nationality (or press n to keep the current): ");
            String nationality = scanner.next();
            if (!nationality.equalsIgnoreCase("n")) {
                while (!ValidateInputUtils.isValidString(nationality)) {
                    System.out.println("Enter a valid string for nationality  : ");
                    nationality = scanner.next();
                }
                personalDetails.setNationality(nationality);
            }
            existingStudent.setPersonalDetails(personalDetails);
            boolean isUpdated = studentService.updateStudent(existingStudent);
            if (isUpdated) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Failed to update student.");
            }
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

}