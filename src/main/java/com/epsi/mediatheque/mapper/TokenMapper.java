package com.epsi.mediatheque.mapper;

import com.epsi.mediatheque.domain.Token;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface TokenMapper {

	@Select("select * from token_store where uuid = #{uuid}")
	@Results(id = "tokenResult", value = {
		@Result(property = "expireAt", column = "expire_at")
	})
	Token findByUuid(@Param("uuid") String uuid);

	@Insert("insert into token_store (uuid, token, expire_at) values (#{uuid}, #{token}, #{expireAt})")
	void storeToken(Token token);
}
