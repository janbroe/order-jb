package com.switchfully.service.user.dtos;

public class CreateUserDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;

    private String userName;

    private String password;

    public CreateUserDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CreateUserDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CreateUserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateUserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public CreateUserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public CreateUserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public CreateUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
