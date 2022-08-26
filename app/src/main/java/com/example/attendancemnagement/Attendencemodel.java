package com.example.attendancemnagement;

public class Attendencemodel {
    private  String rollNo,firstName,fatherName,attendcheckbox;

    private Attendencemodel(){

    }


    public String getAttendcheckbox() {

        return attendcheckbox;
    }

    public void setAttendcheckbox(String attendcheckbox) {
        this.attendcheckbox = attendcheckbox;
    }

    public Attendencemodel(String rollNo, String firstName, String fatherName, String attendcheckbox){

        this.rollNo=rollNo;
        this.firstName=firstName;
        this.fatherName=fatherName;
        this.attendcheckbox=attendcheckbox;

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

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

}
