package com.switchfully.service.user;

import com.switchfully.domain.user.Role;
import com.switchfully.domain.user.User;
import com.switchfully.domain.user.UserRepository;
import com.switchfully.service.user.dtos.CreateUserDTO;
import com.switchfully.service.user.dtos.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userMapper = new UserMapper();
        this.userRepository = userRepository;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        //todo can be done in mapper
        User newCustomer = new User()
                .setFirstname(createUserDTO.getFirstname())
                .setLastname(createUserDTO.getLastname())
                .setEmail(createUserDTO.getEmail())
                .setAddress(createUserDTO.getAddress())
                .setPhoneNumber(createUserDTO.getPhoneNumber())
                .setRole(Role.CUSTOMER)
                .setPassword("pwd");

        userRepository.save(newCustomer);
        return userMapper.userToDTO(newCustomer);
    }
}
