package com.epsi.mediatheque.service.impl;

import com.epsi.mediatheque.domain.User;
import com.epsi.mediatheque.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

public class JdbcUserServiceImpl implements UserMapper {

    private UserMapper userMapper;

    public JdbcUserServiceImpl(UserMapper userMapper){
        super();
        this.userMapper = userMapper;
    }

    @Override
    public List<User> findAll(){return this.userMapper.findAll();}

    @Override
    public Optional<User> findById(String id){return this.userMapper.findById(id);}
}
