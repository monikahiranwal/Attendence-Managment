package com.example.attendancemnagement;

public class viewfacultydb {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String address;

    private viewfacultydb(String username,String password,String firstName,String lastName,String phoneNo,String address){
       this.username=username;
       this.password=password;
        this.firstName =firstName;
        this.lastName =lastName;
        this.phoneNo = phoneNo;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }

    public viewfacultydb() {
    }
}
