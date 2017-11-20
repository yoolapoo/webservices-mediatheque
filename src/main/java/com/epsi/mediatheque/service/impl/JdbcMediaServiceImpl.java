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
    public Media findById(String id){return this.movieMapper.findById(id);}

}
