package com.switchfully.service.security;

public record KeycloakUserDTO(String userName, String password, Role role){
}
