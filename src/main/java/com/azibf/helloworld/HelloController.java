package com.azibf.helloworld;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import org.springframework.security.core.Authentication;


@Controller
public class HelloController {

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "hello";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/")
    public String mainPage(Model model, Authentication authentication) {
        model.addAttribute("username", authentication.getName());
        return "index";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        return "hello";
    }

}
