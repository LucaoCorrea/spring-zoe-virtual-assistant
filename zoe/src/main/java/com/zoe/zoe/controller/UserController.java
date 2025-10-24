package com.zoe.zoe.controller;

import com.zoe.zoe.model.UserModel;

import com.zoe.zoe.services.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.zoe.zoe.enums.RoleEnum;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserModel register(@RequestBody Map<String, String> dataUser) {
        UserModel user = new UserModel(
                dataUser.get("username"),
                dataUser.get("email"),
                dataUser.get("password"),
                RoleEnum.USER);
        return userService.save(user);
    }

    @PostMapping("/login")
    public UserModel login(@RequestBody Map<String, String> dataUser) {
        String email = dataUser.get("email");
        String password = dataUser.get("password");

        UserModel user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!userService.checkPassword(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }

    @GetMapping
    public List<UserModel> listAll() {
        return userService.findAll();
    }
}
