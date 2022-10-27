package com.switchfully.service.customer.dtos;

public class CreateCustomerDTO {

    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;

    public CreateCustomerDTO setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public CreateCustomerDTO setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public CreateCustomerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public CreateCustomerDTO setAddress(String address) {
        this.address = address;
        return this;
    }

    public CreateCustomerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
}
