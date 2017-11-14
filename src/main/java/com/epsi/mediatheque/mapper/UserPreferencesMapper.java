package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.UserPreference;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserPreferencesMapper {
    @Select("SELECT * FROM user_preferences WHERE id = #{id} ")
    List<UserPreference> findById(String id);

    @Insert("INSERT INTO user_preferences (id, key, value) VALUES (#{id}, #{key}, #{value})  ")
    void createUserPreference(UserPreference userPreference);

    @Update("UPDATE user_preferences set value = #{value} WHERE id = #{id} AND key = #{key}")
    void updateUserPreference(UserPreference userPreference);

}
