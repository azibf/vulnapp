package com.reznok.helloworld.service;

import com.reznok.helloworld.model.FeedbackMessage;
import com.reznok.helloworld.model.User;
import com.reznok.helloworld.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public FeedbackMessage createFeedback(User user, String message) {
        FeedbackMessage feedback = new FeedbackMessage();
        feedback.setUser(user);
        feedback.setMessage(message);
        return feedbackRepository.save(feedback);
    }

    public List<FeedbackMessage> getAllFeedback() {
        return feedbackRepository.findAll();
    }

}