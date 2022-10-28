package com.switchfully.api;

import com.switchfully.service.user.dtos.CreateUserDTO;
import com.switchfully.service.user.UserService;
import com.switchfully.service.user.dtos.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createCustomer(@RequestBody CreateUserDTO createUserDTO) {
        log.info("POST -> create User" + createUserDTO.toString());
        return userService.createUser(createUserDTO);
    }
}
