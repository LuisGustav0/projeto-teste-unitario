package com.locadora.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Locacao {

  private Usuario usuario;
  private List<Filme> listaFilme;
  private LocalDate dataLocacao;
  private LocalDate dataRetorno;
  private BigDecimal valor;

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public LocalDate getDataLocacao() {
    return dataLocacao;
  }

  public void setDataLocacao(LocalDate dataLocacao) {
    this.dataLocacao = dataLocacao;
  }

  public LocalDate getDataRetorno() {
    return dataRetorno;
  }

  public void setDataRetorno(LocalDate dataRetorno) {
    this.dataRetorno = dataRetorno;
  }

  public BigDecimal getValor() {
    return valor;
  }

  public void setValor(BigDecimal valor) {
    this.valor = valor;
  }

  public List<Filme> getListaFilme() {
    return listaFilme;
  }

  public void setListaFilme(List<Filme> listaFilme) {
    this.listaFilme = listaFilme;
  }
}