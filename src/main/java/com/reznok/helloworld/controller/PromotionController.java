package com.reznok.helloworld.controller;

import com.reznok.helloworld.model.Promotion;
import com.reznok.helloworld.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Base64;

@Controller
@RequestMapping("/promotion")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    private final ObjectMapper vulnerableObjectMapper;

    public PromotionController() {
        this.vulnerableObjectMapper = new ObjectMapper();
        this.vulnerableObjectMapper.enableDefaultTyping(); // Включает опасную функциональность
    }

    @GetMapping("/list")
    public String seePromotion(Model model) {
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "promotion/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String createPromotion(Model model) {
        model.addAttribute("promotion", new Promotion());
        return "promotion/form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public String submitPromotion(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam MultipartFile image,
            Model model) throws IOException {
        if (image != null && !image.isEmpty()) {
            try {
                String fileContent = new String(image.getBytes());

                // Попытка десериализации (уязвимость CVE-2018-7489)
                Promotion maliciousPromo = vulnerableObjectMapper.readValue(fileContent, Promotion.class);

                // Если десериализация прошла успешно - сохраняем
                Promotion base = new Promotion();
                base.setTitle(maliciousPromo.getTitle());
                base.setDescription(maliciousPromo.getDescription());
                promotionService.createPromotion(
                        base,
                        image
                );
            } catch (Exception e) {
                Promotion base = new Promotion();
                base.setTitle(title);
                base.setDescription(description);
                promotionService.createPromotion(base, image);
            }
        } else {
            Promotion base = new Promotion();
            base.setTitle(title);
            base.setDescription(description);
            promotionService.createPromotion(base, null);
        }

        return "redirect:/promotion/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/delete")
    public String deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return "redirect:/promotion/list";
    }
}