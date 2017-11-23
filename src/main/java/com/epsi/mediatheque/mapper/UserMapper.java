package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id_user = #{id}")
    Optional<User> findById(String id);

    @Select("SELECT * FROM users ")
    List<User> findAll();
}
