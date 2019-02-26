package com.locadora.matchers;

import java.time.DayOfWeek;

public class MatchersProprios {

  public static DiaSemanaMatcher caiEm(DayOfWeek diaSemana) {
    return new DiaSemanaMatcher(diaSemana);
  }

  public static DiaSemanaMatcher caiEmUmaSegunda() {
    return new DiaSemanaMatcher(DayOfWeek.MONDAY);
  }
}
