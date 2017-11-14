package com.epsi.mediatheque.service.impl;

import com.epsi.mediatheque.domain.UserPreference;
import com.epsi.mediatheque.mapper.UserPreferencesMapper;
import com.epsi.mediatheque.service.UserPreferencesService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class JdbcUserPreferencesServiceImpl implements UserPreferencesService{

    private UserPreferencesMapper userPreferencesMapper;

    public JdbcUserPreferencesServiceImpl(UserPreferencesMapper userPreferencesMapper){
        super();
        this.userPreferencesMapper = userPreferencesMapper;
    }

    @Override
    public List<UserPreference> findById(String id){
        return this.userPreferencesMapper.findById(id);
    }

    @Override
    public void createUserPreference(String id,String key,String value){
        UserPreference userPreference = new UserPreference();
        userPreference.setId(id);
        userPreference.setKey(key);
        userPreference.setValue(value);
        userPreferencesMapper.createUserPreference(userPreference);
    }

    @Override
    public void updateUserPreference(String id,String key,String value){
        UserPreference userPreference = new UserPreference();
        userPreference.setId(id);
        userPreference.setKey(key);
        userPreference.setValue(value);
        userPreferencesMapper.updateUserPreference(userPreference);
    }
}
