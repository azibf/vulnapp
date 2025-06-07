package com.reznok.helloworld.controller;

import com.reznok.helloworld.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @GetMapping("/list")
    public String seePromotion() {
        return "promotion/list";
    }

    @GetMapping("/new")
    public String createPromotion() {
        return "promotion/form";
    }

    @PostMapping("/new")
    public String submitPromotion() {
        return "redirect:/promotion/list";
    }
    @GetMapping("/{id}/edit")
    public String editPromotion(@PathVariable Long id) {
        return "promotion/form";
    }

    @PostMapping("/{id}/edit")
    public String updatePromotion(@PathVariable Long id) throws IOException {
        return "redirect:/promotion/list";
    }

    @PostMapping("/{id}/delete")
    public String deletePromotion(@PathVariable Long id) {
        return "redirect:/promotion/list";
    }
}