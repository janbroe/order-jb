package com.switchfully.service.customer;

import com.switchfully.domain.customer.Customer;
import com.switchfully.domain.customer.CustomerRepository;
import com.switchfully.service.customer.dtos.CreateCustomerDTO;
import com.switchfully.service.customer.dtos.CustomerDTO;
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
