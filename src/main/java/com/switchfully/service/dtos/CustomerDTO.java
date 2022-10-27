package com.switchfully.service.dtos;

public class CustomerDTO {

    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;

    public CustomerDTO setId(String id) {
        this.id = id;
        return this;
    }

    public CustomerDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CustomerDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public CustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getId() {
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
