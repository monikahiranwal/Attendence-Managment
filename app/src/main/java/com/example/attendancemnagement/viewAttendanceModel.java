package com.example.attendancemnagement;

public class viewAttendanceModel {
    private  String rollNo,firstName,fatherName,attendence;

    public String getrollNo() {
        return rollNo;
    }

    public void setrollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getfatherName() {
        return fatherName;
    }

    public void setfatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    private viewAttendanceModel(){
    }

    public viewAttendanceModel(String rollNo, String firstName, String fatherName, String attendence){

        this.rollNo=rollNo;
        this.firstName=firstName;
        this.fatherName=fatherName;
        this.attendence=attendence;

    }
}
