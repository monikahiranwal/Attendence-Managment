package com.example.attendancemnagement;

public class attendanceDetailModel {
    private String Date;
    private String attendence;
    private String rollNo;
    private String fatherName;
    private String Lecture;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getLecture() {
        return Lecture;
    }

    public void setLecture(String lecture) {
        Lecture = lecture;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public attendanceDetailModel(String date, String attendence, String rollNo, String fatherName, String lecture, String firstName) {
        Date = date;
        this.attendence = attendence;
        this.rollNo = rollNo;
        this.fatherName = fatherName;
        Lecture = lecture;
        this.firstName = firstName;
    }

    private  String firstName;
private attendanceDetailModel(){};
}
