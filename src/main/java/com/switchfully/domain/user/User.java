package com.switchfully.domain.user;

import com.switchfully.domain.PasswordHasher;

import java.util.UUID;

public class User {

    private final String id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phoneNumber;

    private String password;

    private Role role;

    public User() {
        this.id = UUID.randomUUID().toString();
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

    public User setPassword(String password) {
        this.password = new PasswordHasher(password).getHashedPassword();
        return this;
    }

    public User setRole(Role role) {
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public boolean doesPasswordMatch(String passwordToMatch) {
        return password.equals(new PasswordHasher(passwordToMatch).getHashedPassword());
    }

    public boolean canHaveAccessTo(Feature feature) {
        return role.containsFeature(feature);
    }
}
