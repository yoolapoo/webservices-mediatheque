package com.epsi.mediatheque.dto;

import com.epsi.mediatheque.domain.UserPreference;
import lombok.Data;

@Data
public class DafResponse {

	private DafDataResponse data;
	private UserPreference user;
	private Object error;
	
	
	public DafResponse() {
		super();
		this.data = new DafDataResponse();
		this.user = new UserPreference();
	}
	
	

}
