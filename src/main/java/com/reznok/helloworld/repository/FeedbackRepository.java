package com.reznok.helloworld.repository;
import com.reznok.helloworld.model.FeedbackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.*;

public interface FeedbackRepository extends JpaRepository<FeedbackMessage, Long> {
    List<FeedbackMessage> findAll();
    Optional<FeedbackMessage> findById(Long id);
    <S extends FeedbackMessage> S save(S entity);
    long count();
}



