package com.epsi.mediatheque.data;

public enum MediaApiStatus {

	MEDIA_API_STATUS_1400(1400, "Required parameter(s) invalid/not defined"),
	MEDIA_API_STATUS_1401(1401, "All medias in the library with the given id have been borrowed"),
	MEDIA_API_STATUS_1402(1402, "No media in the library has the given id"),
	MEDIA_API_STATUS_1403(1403, "All medias with the given id are already returned"),

	// error for technical problems
	MEDIA_API_STATUS_1500(1500, "Internal error");

	private final int httpCode;
	private final String reason;

	MediaApiStatus(int pValue, String pReason) {
		this.httpCode = pValue;
		this.reason = pReason;
	}

	public int value() {
		return this.httpCode;
	}

	@Override
	public String toString() {
		return String.valueOf(this.httpCode);
	}

	public String getReason() {
		return this.reason;
	}

	public MediaApiStatus valueOf(int pValue) {
		for (MediaApiStatus status : MediaApiStatus.values()) {
			if (status.httpCode == pValue) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + pValue + "]");
	}
}
