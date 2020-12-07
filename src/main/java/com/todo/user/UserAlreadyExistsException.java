package com.todo.user;

import java.io.IOException;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends IOException {

    private static final long serialVersionUID = 1L;

    private static final HttpStatus STATUS = HttpStatus.FORBIDDEN;

    public UserAlreadyExistsException() {
        super();
    }

    public HttpStatus getStatusCode() {
        return STATUS;
    }

    @Override
    public String getMessage() {
        return "User already exists";
    }

}
