package com.locadora.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class UtilDate {

  public static boolean verificarDiaDaSemana(LocalDate data, DayOfWeek dayOfWeek) {
    DayOfWeek diaSemana = data.getDayOfWeek();
    return diaSemana == dayOfWeek;
  }
}
