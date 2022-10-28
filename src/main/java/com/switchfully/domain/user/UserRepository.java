package com.switchfully.domain.user;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Repository
public class UserRepository {

    private final Map<String, User> userRepository;

    public UserRepository() {
        this.userRepository = new HashMap<>();
        createUser(new User().setFirstname("Joske").setLastname("Verboven").setEmail("jvb@hotmail.com").setAddress("Ploplaan 12").setPhoneNumber("0499887766").setPassword("pwd").setRole(Role.CUSTOMER));        createUser(new User().setFirstname("Jefke").setLastname("Verloren").setEmail("jvl@hotmail.com").setAddress("Kloplaan 24").setPhoneNumber("0499335555").setPassword("pwd").setRole(Role.ADMIN));
    }

    public void createUser(User customer) {
        userRepository.put(customer.getId(), customer);
    }

    public Collection<User> getAllCustomers() {
        return userRepository.values();
    }

    public User getMemberByEmail(String emailAsUsername) {
        return userRepository.values().stream()
                .filter(user -> user.getEmail().equals(emailAsUsername))
                .findFirst()
                .orElse(null);
    }
}
