package com.epsi.mediatheque.dto;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class DafListResponse {
	
	private List<DafDataResponse> data;
	private Object error;
	
	public DafListResponse() {
		super();
		this.data = new LinkedList<>();
	}
	
}
