package com.reznok.helloworld.controller;

import com.reznok.helloworld.model.FeedbackMessage;
import com.reznok.helloworld.model.User;
import com.reznok.helloworld.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public String seeFeedback(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        return "feedback/list";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('USER')")
    public String createFeedback(Model model) {
        model.addAttribute("feedback", new FeedbackMessage());
        return "feedback/form";
    }

    @PostMapping("/new")
    @PreAuthorize("hasRole('USER')")
    public String submitFeedback(@AuthenticationPrincipal User user,
                                 @RequestParam String message)  {
        feedbackService.createFeedback(user, message);
        return "redirect:/promotion/list";
    }


}