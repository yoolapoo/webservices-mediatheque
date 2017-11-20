package com.epsi.mediatheque.exception;

import com.epsi.mediatheque.dto.ErrorResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Applicative runtime exception
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MedialApiException extends RuntimeException{


    private static final long serialVersionUID = -3636642394398900328L;
    private final HttpStatus status;
    private final String code;

    /**
     *
     * @param status an Http Status
     * @param code Error code. See {@link com.epsi.mediatheque.data.MediaApiStatus}
     * @param message message
     */
    public MedialApiException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public ErrorResponse getResponse() {
        return new ErrorResponse(code, this.getMessage());
    }
}
