package com.example.attendancemnagement;

public class searchModel {
    private String Date;
    private String attendence;
    private String rollNo;
    private String fatherName;
    private String Lecture;
    private  String firstName;

    private searchModel() {}


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

    public searchModel(String Date, String attendence,String rollNo,String firstName,String fatherName,String Lecture) {
        this.Date = Date;
        this.attendence = attendence;
        this.rollNo=rollNo;
        this.firstName=firstName;
        this.fatherName=fatherName;
        this.Lecture=Lecture;
    }


}
