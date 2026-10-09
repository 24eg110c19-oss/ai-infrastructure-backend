package com.anurag.aiinfra.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anurag.aiinfra.entity.User;
import com.anurag.aiinfra.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5181")
public class UserController {


private final UserService userService;

public UserController(UserService userService) {
    this.userService = userService;
}

@GetMapping
public List<User> getAllUsers() {
    return userService.getAllUsers();
}

@PostMapping("/register")
public User registerUser(@RequestBody User user) {
    return userService.registerUser(user);
}

@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody User user) {

    User loggedInUser = userService.loginUser(
            user.getEmail(),
            user.getPassword()
    );

    if (loggedInUser != null) {
        return ResponseEntity.ok(loggedInUser);
    }

    return ResponseEntity
            .status(401)
            .body("Invalid email or password");
}

@PutMapping("/{id}")
public ResponseEntity<?> updateUser(
        @PathVariable Long id,
        @RequestBody User user) {

    User updatedUser = userService.updateUser(id, user);

    if (updatedUser == null) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(updatedUser);
}

@DeleteMapping("/{id}")
public ResponseEntity<?> deleteUser(@PathVariable Long id) {

    boolean deleted = userService.deleteUser(id);

    if (!deleted) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok("User deleted successfully");
}

@GetMapping("/search")
public List<User> searchUsers(@RequestParam String keyword) {
    return userService.searchUsers(keyword);
}


}
