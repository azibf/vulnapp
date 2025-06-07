package com.reznok.helloworld.repository;

import com.reznok.helloworld.model.Promotion;
import com.reznok.helloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import java.util.*;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long id);
    <S extends Promotion> S save(S entity);
    void deleteById(Long id);
    long count();
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
