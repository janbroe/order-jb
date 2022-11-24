package com.switchfully.domain.user;

import com.switchfully.service.security.Role;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private long userId;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;
    private Role role;

    private String userName;

    public User() {
    }

    public User setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setAddress(String address) {
        this.address = address;
        return this;
    }

    public User setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public Long getUserId() {
        return userId;
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

    public Role getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }
}
