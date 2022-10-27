package com.switchfully.api;

import com.switchfully.service.dtos.CreateCustomerDTO;
import com.switchfully.service.CustomerService;
import com.switchfully.service.dtos.CustomerDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public String getAllTest() {
        log.info("GET -> getBackTest");
        return "test";
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO) {
        log.info("POST -> create User" + createCustomerDTO.toString());
        return customerService.createCustomer(createCustomerDTO);
    }
}
