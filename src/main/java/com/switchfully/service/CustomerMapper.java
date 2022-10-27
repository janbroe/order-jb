package com.switchfully.service;

import com.switchfully.domain.Customer;
import com.switchfully.service.dtos.CustomerDTO;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public CustomerDTO customerToDTO(Customer customer) {
        return new CustomerDTO()
                .setId(customer.getId())
                .setFirstname(customer.getFirstname())
                .setLastname(customer.getLastname())
                .setEmail(customer.getEmail())
                .setAddress(customer.getAddress())
                .setPhoneNumber(customer.getPhoneNumber());
    }

    public List<CustomerDTO> customerToDTO(List<Customer> customers) {
        return customers.stream()
                .map(this::customerToDTO)
                .collect(Collectors.toList());
    }
}
