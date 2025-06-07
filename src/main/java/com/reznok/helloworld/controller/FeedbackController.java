package com.reznok.helloworld.controller;

import com.reznok.helloworld.model.Greeting;
import com.reznok.helloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @GetMapping("/list")
    public String seeFeedback() {
        return "feedback/list";
    }

    @GetMapping("/new")
    public String createFeedback() {
        return "feedback/form";
    }

    @PostMapping("/new")
    public String submitFeedback() {
        return "redirect:/promotion/list";
    }


}