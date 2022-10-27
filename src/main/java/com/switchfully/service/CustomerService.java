package com.switchfully.service;

import com.switchfully.domain.Customer;
import com.switchfully.domain.CustomerRepository;
import com.switchfully.service.dtos.CreateCustomerDTO;
import com.switchfully.service.dtos.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerMapper = new CustomerMapper();
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer newCustomer = new Customer(createCustomerDTO.getFirstname(), createCustomerDTO.getLastname(), createCustomerDTO.getEmail(), createCustomerDTO.getAddress(), createCustomerDTO.getPhoneNumber());
        customerRepository.createCustomer(newCustomer);
        return customerMapper.customerToDTO(newCustomer);
    }
}
