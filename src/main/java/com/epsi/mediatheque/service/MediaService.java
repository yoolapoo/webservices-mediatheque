package com.epsi.mediatheque.service;

import com.epsi.mediatheque.domain.Media;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MediaService {

    Optional<Media> findById(long id);

    List<Media> findAll(String type);

    List<Media> search(String searchTerm);

    String addMedia(long id, Media media);

    void updateMedia(Media media);

}
