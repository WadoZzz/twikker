package com.example.suit.controller;

import com.example.suit.domain.Role;
import com.example.suit.domain.User;
import com.example.suit.repository.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepos userRepos;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User usernameFromDB = userRepos.findByUsername(user.getUsername());
        if (usernameFromDB != null) {
            model.put("message", "User exist!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepos.save(user);
        return "redirect:/login";
    }
}

