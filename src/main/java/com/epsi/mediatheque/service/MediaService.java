package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.Media;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MediaService {

    Media findById(String id);

    List<Media> findAll(String type);
}
