package com.switchfully.service.user;

import com.switchfully.domain.user.User;
import com.switchfully.service.user.dtos.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {

    public UserDTO userToDTO(User user) {
        return new UserDTO()
                .setId(user.getUserId())
                .setFirstname(user.getFirstname())
                .setLastname(user.getLastname())
                .setEmail(user.getEmail())
                .setAddress(user.getAddress())
                .setPhoneNumber(user.getPhoneNumber());
    }

    public List<UserDTO> userToDTO(List<User> users) {
        return users.stream()
                .map(this::userToDTO)
                .collect(Collectors.toList());
    }
}
