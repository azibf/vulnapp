package com.reznok.helloworld.service;

import com.reznok.helloworld.model.Promotion;
import com.reznok.helloworld.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }
    public List<Promotion> searchPromotions(String param) {
        return promotionRepository.findPromotions(param);
    }

    public Optional<Promotion> getPromotionById(Long id) {
        return promotionRepository.findById(id);
    }

    public Promotion createPromotion(Promotion promotion, MultipartFile image) throws IOException {
        if (image != null && !image.isEmpty()) {
            promotion.setImage(image.getBytes());
        }
        return promotionRepository.save(promotion);
    }

    public void updatePromotion(Long id, Promotion promotionDetails, MultipartFile image) throws IOException {
        Optional<Promotion> promotion = getPromotionById(id);
        if (promotion.isPresent()) {
            promotion.get().setTitle(promotionDetails.getTitle());
            promotion.get().setDescription(promotionDetails.getDescription());

            if (image != null && !image.isEmpty()) {
                promotion.get().setImage(image.getBytes());
            }
            promotionRepository.save(promotion.get());
        }
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
}
