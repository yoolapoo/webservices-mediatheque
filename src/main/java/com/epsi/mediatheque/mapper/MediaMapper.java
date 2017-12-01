package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.Media;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper
@Component
public interface MediaMapper {

    @Select("SELECT * FROM media WHERE id_media = #{id} AND isAvailable = true ")
    Optional<Media> findById(long id);

    @Select("SELECT * FROM media WHERE type_media = #{type}")
    List<Media> findAll(String type);

    @Insert("INSERT INTO media (id_media, type_media, author, title, creation, genre, isAvailable) " +
            "VALUES(#{id},#{type_media},#{author},#{title},#{creation},#{genre},#{dispo})")
    String addMedia(long id,Media media);

    @Update("UPDATE media set isavailable = #{isavailable} WHERE id_media = #{id_media} ")
    void updateMedia(Media media);

    @Select("SELECT * FROM media WHERE id_media = #{searchTerm} OR title = #{searchTerm} OR author = #{searchTerm}")
    List<Media> search(String searchTerm);


}
