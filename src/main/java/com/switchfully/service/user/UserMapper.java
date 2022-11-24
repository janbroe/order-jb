package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.service.security.Role;
import com.switchfully.service.user.dtos.CreateUserDTO;
import com.switchfully.service.user.dtos.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTO userToDTO(User user) {
        return new UserDTO()
                .setId(user.getUserId())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setAddress(user.getAddress())
                .setPhoneNumber(user.getPhoneNumber())
                .setUserName(user.getUserName());
    }

    public User createUserDTOtoUser(CreateUserDTO createUserDTO) {
        return new User()
                .setFirstname(createUserDTO.getFirstname())
                .setLastname(createUserDTO.getLastname())
                .setEmail(createUserDTO.getEmail())
                .setAddress(createUserDTO.getAddress())
                .setPhoneNumber(createUserDTO.getPhoneNumber())
                .setRole(Role.CUSTOMER)
                .setUserName(createUserDTO.getUserName());
    }
}
