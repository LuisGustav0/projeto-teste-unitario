package com.locadora.matchers;

import java.time.DayOfWeek;

public class MatchersProprios {

    public static DiaSemanaMatcher caiEm(DayOfWeek diaSemana) {
        return new DiaSemanaMatcher(diaSemana);
    }

    public static DiaSemanaMatcher caiEmUmaSegunda() {
        return new DiaSemanaMatcher(DayOfWeek.MONDAY);
    }

    public static DataDiferenciaDiasMatcher ehHoje() {
        return new DataDiferenciaDiasMatcher(0);
    }

    public static DataDiferenciaDiasMatcher ehHojeComDiferencaDias(Integer quantidadeDias) {
        return new DataDiferenciaDiasMatcher(quantidadeDias);
    }
}
