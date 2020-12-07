package com.todo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.todo.user.UserService.UserDTO;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public UserDTO login(@RequestBody User user) {
        try {
            return userService.login(user);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }
    
    @PostMapping("register")
    public UserDTO register(@RequestBody User user) {
        try {
            return userService.register(user);
        } catch (UserAlreadyExistsException e) {
            throw new ResponseStatusException(e.getStatusCode(), e.getMessage(), e);
        }
    }

}
