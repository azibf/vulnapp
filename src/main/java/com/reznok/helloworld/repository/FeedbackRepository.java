package com.reznok.helloworld.repository;
import com.reznok.helloworld.model.FeedbackMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackMessage, Long> {
    List<FeedbackMessage> findAll();
    Optional<FeedbackMessage> findById(Long id);
    <S extends FeedbackMessage> S save(S entity);
    long count();
}



