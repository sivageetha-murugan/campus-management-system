package com.i2i.cms.models;

import com.i2i.cms.models.Student;

/**
 * This class is designed to manage the personal details of a student.
 * It includes information such as:
 *     The names of the student's father and mother.
 *     The student's phone number, city of residence, and nationality.
 * This class aims to encapsulate all relevant personal informations about a student, 
 * providing methods for accessing and modifying this information.
 */

public class PersonalDetails {
    private int personalDetailsId;
    private String fatherName;
    private String motherName;
    private String phoneNumber;
    private String city;
    private String nationality;
    private Student student;

    public PersonalDetails() {}

    public PersonalDetails(String fatherName, String motherName, String phoneNumber, String city, String nationality) {
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.nationality = nationality;
    }

    public int getPersonalDetailsId() {
        return personalDetailsId;
    }

    public void setPersonalDetailsId(int personalDetailsId) {
        this.personalDetailsId = personalDetailsId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\t\tPersonal ID       : ").append(personalDetailsId)
          .append("\n\t\tFather Name       : ").append(fatherName)
          .append("\n\t\tMother Name       : ").append(motherName)
          .append("\n\t\tPhone Number      : ").append(phoneNumber)
          .append("\n\t\tCity              : ").append(city)
          .append("\n\t\tNationality       : ").append(nationality);
        return stringBuilder.toString(); 
    }
}
