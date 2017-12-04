package com.epsi.mediatheque.domain;

import com.epsi.mediatheque.AbstractTest;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.meanbean.test.BeanTester;

import java.io.IOException;

public class DomainTest extends AbstractTest {

    private static final String DOMAIN_PACKAGE = "com.epsi.mediatheque.domain";

    @Before
    @Override
    public void before() {
        beanTester = new BeanTester();
    }

    @Test
    public void testAbstractModels() throws IllegalArgumentException, InstantiationException,
        IllegalAccessException, IOException, AssertionError, NotFoundException, CannotCompileException {
        testAbstractModels(DOMAIN_PACKAGE);
    }

    @Test
    public void testConcreteModels()
        throws IOException, InstantiationException, IllegalAccessException, NotFoundException, CannotCompileException {
        testConcreteModels(DOMAIN_PACKAGE);
    }


}
