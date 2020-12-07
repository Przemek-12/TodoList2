package com.todo.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO login(User user) throws UserNotFoundException {
        return mapToUserDTO(findByLoginAndPassword(user.getLogin(), user.getPassword()));
    }

    public UserDTO register(User user) throws UserAlreadyExistsException {
        if (findByLogin(user.getLogin()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return mapToUserDTO(saveUser(user));
    }

    private User findByLoginAndPassword(String login, String password) throws UserNotFoundException {
        return userRepository.findByLoginAndPassword(login, password).orElseThrow(UserNotFoundException::new);
    }

    private Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    private User saveUser(User user) {
        return userRepository.save(user);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).login(user.getLogin()).build();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserDTO {
        private Long id;
        private String login;
    }

}
