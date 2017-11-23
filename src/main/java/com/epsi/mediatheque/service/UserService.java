package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> findById(String id);

    List<User> findAll();
}
