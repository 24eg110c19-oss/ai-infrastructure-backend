package com.anurag.aiinfra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anurag.aiinfra.entity.User;
import com.anurag.aiinfra.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User loginUser(String email, String password) {

        List<User> users = userRepository.findByEmail(email);

        for (User user : users) {

            if (password != null && password.equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    public List<User> searchUsers(String keyword) {
        return userRepository
                .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }

    public User updateUser(Long id, User user) {

        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser == null) {
            return null;
        }

        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

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