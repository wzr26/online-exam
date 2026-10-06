package com.wzr26.onlineexam.service;

import com.wzr26.onlineexam.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1L, "dung", "123456","student"));
        users.add(new User(2L, "teacher", "123456", "teacher"));
    }

    // GET all users
    public List<User> getAllUsers() {
        return users;
    }

    public User login(
        String username,
        String password
) {

    for (User user : users) {

        if (
                user.getUsername().equals(username)
                &&
                user.getPassword().equals(password)
        ) {
            return user;
        }

    }

    return null;
    }

    // GET user by ID
    public User getUserById(Long id) {

        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }

        return null;
    }

    // POST create user
    public User createUser(User user) {

        Long newId = 1L;

        for (User existingUser : users) {
            if (existingUser.getId() >= newId) {
                newId = existingUser.getId() + 1;
            }
        }

        user.setId(newId);
        users.add(user);

        return user;
    }

    // PUT update user
    public User updateUser(Long id, User updatedUser) {

        User existingUser = getUserById(id);

        if (existingUser == null) {
            return null;
        }

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());

        return existingUser;
    }

    // DELETE user
    public boolean deleteUser(Long id) {

        User user = getUserById(id);

        if (user == null) {
            return false;
        }

        users.remove(user);

        return true;
    }
}

