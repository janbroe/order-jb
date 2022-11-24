package com.switchfully.service.user.dtos;

public class UserDTO {

    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;
    private String userName;

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public UserDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public UserDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public UserDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public UserDTO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getId() {
        return id;
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
}
