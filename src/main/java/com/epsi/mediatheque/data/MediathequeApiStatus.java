package com.epsi.mediatheque.data;

public enum MediathequeApiStatus {

	DAF_REPORT_API_STATUS_1400(1400, "Required parameter(s) invalid/not defined"),
	DAF_REPORT_API_STATUS_1401(1401, "Invalid token"),
	DAF_REPORT_API_STATUS_1402(1402, "Malformed token"),
	// error for technical problems
	DAF_REPORT_API_STATUS_1500(1500, "Internal error");

	private final int httpCode;
	private final String reason;

	MediathequeApiStatus(int pValue, String pReason) {
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

	public MediathequeApiStatus valueOf(int pValue) {
		for (MediathequeApiStatus status : MediathequeApiStatus.values()) {
			if (status.httpCode == pValue) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + pValue + "]");
	}
}
