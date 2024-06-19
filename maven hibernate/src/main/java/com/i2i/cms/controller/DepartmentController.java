package com.i2i.cms.controller;

import java.util.Scanner;

import com.i2i.cms.exception.DepartmentException;
import com.i2i.cms.model.Department;
import com.i2i.cms.service.DepartmentService;

public class DepartmentController {

    private static DepartmentService departmentService = new DepartmentService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Department name: ");
        String departmentName = scanner.nextLine();
        System.out.println("Enter department HOD name: ");
        String departmentHOD = scanner.nextLine();
        System.out.println("Enter number of teachers: ");
        int noOfTeachers = scanner.nextInt();
        System.out.println("Enter number of students: ");
        int noOfStudents = scanner.nextInt();
        Department department = new Department(departmentName, departmentHOD, noOfTeachers, noOfStudents);
        try {
            department = departmentService.addDepartment(department);
            System.out.println(department);
        } catch (DepartmentException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
}
