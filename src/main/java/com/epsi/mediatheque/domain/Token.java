package com.epsi.mediatheque.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Token {

	private String uuid;
	private String token;
	private LocalDateTime expireAt;

}
