package com.epsi.mediatheque.exception;

import com.epsi.mediatheque.dto.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class DafReportPortalApiException extends RuntimeException{

    /**
     *
     */
    private static final long serialVersionUID = -3636642394398900328L;
    private HttpStatus status;
    private String code;

    public DafReportPortalApiException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public ErrorResponse getResponse() {
        return new ErrorResponse(code, this.getMessage());
    }
}
