package com.wzr26.onlineexam.service;

import com.wzr26.onlineexam.model.User;
import com.wzr26.onlineexam.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        seedDefaultUser("dung", "123456", "student");
        seedDefaultUser("teacher", "123456", "teacher");
    }

    private void seedDefaultUser(String username, String password, String role) {
        if (userRepository.findByUsername(username).isEmpty()) {
            userRepository.save(new User(null, username, password, role));
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User login(String username, String password) {
        return userRepository.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .orElse(null);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        user.setId(null);
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        if (existingUser == null) {
            return null;
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }

        userRepository.deleteById(id);
        return true;
    }
}
