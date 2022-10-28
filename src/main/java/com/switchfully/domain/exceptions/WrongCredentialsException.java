package com.switchfully.domain.exceptions;

import java.util.NoSuchElementException;

public class WrongCredentialsException extends NoSuchElementException {
    public WrongCredentialsException() {
        super("Wrong credentials");
    }
}
