package com.epsi.mediatheque.dto;

import com.epsi.mediatheque.AbstractTest;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DtoTest extends AbstractTest {

    private static final String DTO_PACKAGE = "com.epsi.mediatheque.dto";

    @Test
    public void testAbstractModels() throws IllegalArgumentException, InstantiationException,
        IllegalAccessException, IOException, AssertionError, NotFoundException, CannotCompileException {
        testAbstractModels(DTO_PACKAGE);
    }

    @Test
    public void testConcreteModels()
        throws IOException, InstantiationException, IllegalAccessException, NotFoundException, CannotCompileException {
        testConcreteModels(DTO_PACKAGE);
    }

    @Test
    public void testErrorResponseConstrutor() {
        String code = "500";
        String message = "aMessage";
        ErrorResponse errorResponse = new ErrorResponse(code, message);
        Assert.assertEquals(code, errorResponse.getCode());
        Assert.assertEquals(message, errorResponse.getMessage());
    }
}
