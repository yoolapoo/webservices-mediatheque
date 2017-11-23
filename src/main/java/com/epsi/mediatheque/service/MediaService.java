package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.Media;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MediaService {

    Optional<Media> findById(String id);

    List<Media> findAll(String type);

    String addMedia(String id, Media media);

}
