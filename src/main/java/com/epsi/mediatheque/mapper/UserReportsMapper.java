package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.UserReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserReportsMapper {

	@Select("SELECT * FROM dm_agg_user_reports WHERE id = #{id}")
	List<UserReport> findById(String id);
}
