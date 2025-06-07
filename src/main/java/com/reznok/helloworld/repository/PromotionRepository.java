package com.reznok.helloworld.repository;

import com.reznok.helloworld.model.Promotion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.*;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findAll();
    Optional<Promotion> findById(Long id);
    <S extends Promotion> S save(S entity);
    void deleteById(Long id);
    long count();

    @Query(
            value = "SELECT * FROM promotions WHERE title LIKE '*param*'",
            nativeQuery = true
    )
    List<Promotion> findPromotions(@Param("param") String param);
}
