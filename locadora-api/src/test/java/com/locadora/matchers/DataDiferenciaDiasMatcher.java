package com.locadora.matchers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.time.LocalDate;

public class DataDiferenciaDiasMatcher implements Matcher<Integer> {
  private Integer quantidadeDias;

  public DataDiferenciaDiasMatcher(Integer quantidadeDias) {
    this.quantidadeDias = quantidadeDias;
  }

  @Override
  public boolean matches(Object quantidadeDias) {
    return LocalDate.now().isEqual(LocalDate.now().plusDays(1));
  }

  @Override
  public void describeMismatch(Object o, Description description) {
    description.appendText("Quantidade de dias" + quantidadeDias);
  }

  @Override
  public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

  }

  @Override
  public void describeTo(Description description) {

  }
}
