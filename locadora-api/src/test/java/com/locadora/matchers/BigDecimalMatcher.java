package com.locadora.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.math.BigDecimal;

public class BigDecimalMatcher extends TypeSafeMatcher<BigDecimal> {

    @Override
    protected boolean matchesSafely(BigDecimal bigDecimal) {
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}
