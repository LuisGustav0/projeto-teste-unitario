package com.locadora.matchers;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DiaSemanaMatcher extends TypeSafeMatcher<LocalDate> {

    private DayOfWeek diaSemana;

    public DiaSemanaMatcher(DayOfWeek diaSemana) {
        this.diaSemana = diaSemana;
    }

    @Override
    protected boolean matchesSafely(LocalDate data) {
        DayOfWeek diaSemana = data.getDayOfWeek();
        return diaSemana == diaSemana;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(LocalDate.now().getDayOfWeek().name());
    }
}
