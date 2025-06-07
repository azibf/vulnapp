package com.reznok.helloworld.controller;

import com.reznok.helloworld.model.User;
import com.reznok.helloworld.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;


@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger("HelloWorld");
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        logger.info("login attempt from user " + "");
        return "login";
    }

    @GetMapping("/reset-password")
    public String showResetPasswordForm() {
        return "reset-password";
    }

    @PostMapping("/reset-password")
    public String resetPassword() {
        return "redirect:/login";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/registration")
    public String showRegistration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/registration")
    public String submitRegistration(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/promotion/list";
    }
}
