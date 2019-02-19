package com.locadora.service;

import com.locadora.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {
  public int somar(int valor1, int valor2) {
    return valor1 + valor2;
  }

  public int subtrair(int valor1, int valor2) {
    return valor1 - valor2;
  }

  public int divide(int valor1, int valor2) throws NaoPodeDividirPorZeroException {
    if (valor2 == 0) {
      throw new NaoPodeDividirPorZeroException("Nao pode ser dividido por ZERO!");
    }
    return valor1 / valor2;
  }
}
