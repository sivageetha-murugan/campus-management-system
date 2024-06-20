package com.i2i.cms.model;

/*
 * This manages the department details such as department name, HOD name, no of teachers and students.
 */

public class Department {

    private int departmentId;
    private String departmentName;
    private String departmentHOD;
    private int noOfTeachers;
    private int noOfStudents;

    public Department(String departmentName, String departmentHOD, int noOfTeachers, int noOfStudents) {
        this.departmentName = departmentName;
        this.departmentHOD = departmentHOD;
        this.noOfTeachers = noOfTeachers;
        this.noOfStudents = noOfStudents;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentHOD() {
        return departmentHOD;
    }

    public void setDepartmentHOD(String departmentHOD) {
        this.departmentHOD = departmentHOD;
    }

    public int getNoOfTeachers() {
        return noOfTeachers;
    }

    public void setNoOfTeachers(int noOfTeachers) {
        this.noOfTeachers = noOfTeachers;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Department{");
        sb.append("departmentId=").append(departmentId);
        sb.append(", departmentName='").append(departmentName).append('\'');
        sb.append(", departmentHOD='").append(departmentHOD).append('\'');
        sb.append(", noOfTeachers=").append(noOfTeachers);
        sb.append(", noOfStudents=").append(noOfStudents);
        sb.append('}');
        return sb.toString();
    }
}
