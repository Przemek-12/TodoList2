package com.todo.user;

import java.io.IOException;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends IOException {

    private static final long serialVersionUID = 1L;

    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;

    public UserNotFoundException() {
        super();
    }

    public HttpStatus getStatusCode() {
        return STATUS;
    }

    @Override
    public String getMessage() {
        return "User not found";
    }

}
