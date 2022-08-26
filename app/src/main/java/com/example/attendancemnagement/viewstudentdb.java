package com.example.attendancemnagement;

public class viewstudentdb {
    private String rollNo;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNo;
    private String addresss;
    private String course;
    private String department;
    private String year;

    private viewstudentdb(String rollNo,String firstName,String lastName,String fatherName,String phoneNo,String address,String course,String department,String year){

        this.rollNo = rollNo;
        this.firstName =firstName;
        this.lastName =lastName;
        this.fatherName = fatherName;
        this.phoneNo = phoneNo;
        this.addresss = address;
        this.course = course;
        this.department = department;
        this.year = year;
    }

    public viewstudentdb() {
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return addresss;
    }

    public void setAddresss(String address) {
        this.addresss = address;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


}
