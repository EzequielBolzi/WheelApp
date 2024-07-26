package com.proyect.authAndUserModule.infraestructure.adapters.primary;

import com.proyect.authAndUserModule.application.service.UserService;
import com.proyect.authAndUserModule.domain.model.User;
import com.proyect.authAndUserModule.infraestructure.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        try {
            return userService.getUser(userId)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}