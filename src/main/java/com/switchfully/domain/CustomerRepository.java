package com.switchfully.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CustomerRepository {

    private final Map<String, Customer> customerRepository;

    public CustomerRepository() {
        this.customerRepository = new HashMap<>();
        createCustomer(new Customer("Joske", "Verboven", "jvb@hotmail.com", "Ploplaan 12", "0499887766"));
    }

    public void createCustomer(Customer customer) {
        customerRepository.put(customer.getId(), customer);
    }
}
