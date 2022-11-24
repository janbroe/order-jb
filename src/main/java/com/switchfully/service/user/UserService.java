package com.switchfully.service.user;

import com.switchfully.service.security.KeycloakService;
import com.switchfully.service.security.KeycloakUserDTO;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.dtos.CreateUserDTO;
import com.switchfully.service.user.dtos.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    private final KeycloakService keycloakService;

    public UserService(UserMapper userMapper, UserRepository userRepository, KeycloakService keycloakService) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.keycloakService = keycloakService;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        User newCustomer = userMapper.createUserDTOtoUser(createUserDTO);
        User user = userRepository.save(newCustomer);
        if(!user.getFirstname().contains("test")) {
            System.out.println("No test user -> writing to keycloak");
            keycloakService.addUser(new KeycloakUserDTO(user.getUserName(), createUserDTO.getPassword(), user.getRole()));
        }
        return userMapper.userToDTO(newCustomer);
    }
}
