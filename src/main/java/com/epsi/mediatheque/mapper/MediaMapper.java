package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.Media;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MediaMapper {

    @Select("SELECT * FROM media WHERE id_media = #{id}")
    Media findById(String id);

    @Select("SELECT * FROM media WHERE type_media = #{type}")
    List<Media> findAll(String type);
}
