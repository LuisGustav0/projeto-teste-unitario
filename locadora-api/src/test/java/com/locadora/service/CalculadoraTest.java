package com.locadora.service;

import com.locadora.exceptions.NaoPodeDividirPorZeroException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculadoraTest {

  private Calculadora calculadora;

  @Before
  public void onBefore() {
    calculadora = new Calculadora();
  }

  @Test
  public void onSomar() {
    // Cenario
    int valor1 = 5;
    int valor2 = 3;

    // Acao
    int resultado = calculadora.somar(valor1, valor2);

    // Verificacao
    Assert.assertEquals(8, resultado);
  }

  @Test
  public void onSubtrair() {
    // Cenario
    int valor1 = 5;
    int valor2 = 3;

    // Acao
    int resultado = calculadora.subtrair(valor1, valor2);

    // Verificacao
    Assert.assertEquals(2, resultado);
  }

  @Test
  public void onDividir() throws NaoPodeDividirPorZeroException {
    // Cenario
    int valor1 = 6;
    int valor2 = 3;

    // Acao
    int resultado = calculadora.dividir(valor1, valor2);

    // Verificacao
    Assert.assertEquals(2, resultado);
  }

  @Test(expected = NaoPodeDividirPorZeroException.class)
  public void onDividir_Valor2_ZERO() throws NaoPodeDividirPorZeroException {
    // Cenario
    int valor1 = 10;
    int valor2 = 0;

    // Acao
    int resultado = calculadora.dividir(valor1, valor2);

    // Verificacao
    Assert.assertEquals(8, resultado);
  }
}
