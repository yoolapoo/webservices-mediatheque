package com.epsi.mediatheque.exception;

import com.epsi.mediatheque.AbstractTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;

public class MediathequeApiExceptionTest extends AbstractTest {

    @Test
    public void checkContructor() {
        String message = "aMessage";
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        String code = "1403";
        MedialApiException  medialApiException = new MedialApiException(httpStatus, code, message);
        assertEquals(message, medialApiException.getMessage());
        assertEquals(code, medialApiException.getCode());
        assertEquals(httpStatus, medialApiException.getStatus());

    }
}
