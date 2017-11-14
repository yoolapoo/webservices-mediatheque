package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.FactUserReports;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface FactUserReportsMapper {

	@Select("SELECT * FROM dm_fact_user_reports WHERE id = #{id} ORDER BY ssb, reports, groups_type, groups_id, vid, vin LIMIT 1000")
	List<FactUserReports> findById(String id);
}
