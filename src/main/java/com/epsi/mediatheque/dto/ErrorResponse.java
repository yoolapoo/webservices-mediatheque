package com.epsi.mediatheque.dto;

import lombok.Data;

/**
 * Generic error response
 * 
 * @author A175402
 *
 */
@Data
public class ErrorResponse {
	/**
	 * Error code.
	 */
	private String code;
	/**
	 * Error message.
	 */
	private String message;

	public ErrorResponse() {
	}

	/**
	 * Constructor.
	 * 
	 * @param code
	 *            error code
	 * @param message
	 *            error message
	 */
	public ErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
