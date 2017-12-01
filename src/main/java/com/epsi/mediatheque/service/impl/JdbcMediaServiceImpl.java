package com.epsi.mediatheque.service.impl;

import com.epsi.mediatheque.domain.Media;
import com.epsi.mediatheque.mapper.MediaMapper;
import com.epsi.mediatheque.service.MediaService;

import java.util.List;
import java.util.Optional;

public class JdbcMediaServiceImpl implements MediaService {

    private MediaMapper movieMapper;

    public JdbcMediaServiceImpl(MediaMapper movieMapper){
        super();
        this.movieMapper = movieMapper;
    }

    @Override
    public List<Media> findAll(String type){return this.movieMapper.findAll(type);}

    @Override
    public Optional<Media> findById(long id){return this.movieMapper.findById(id);}

    @Override
    public String addMedia(long id, Media media){return this.movieMapper.addMedia(id, media);}

    @Override
    public void updateMedia(Media media){this.movieMapper.updateMedia(media);}

    @Override
    public List<Media> search(String searchTerm){return this.movieMapper.search(searchTerm);}

}
