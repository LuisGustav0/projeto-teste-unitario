package com.locadora.model;

import java.math.BigDecimal;

public class Filme {

	private String	nome;
	private Integer	estoque;
	private BigDecimal precoLocacao;

	public Filme() {
	}

	public Filme(String nome, Integer estoque, BigDecimal precoLocacao) {
		this.nome = nome;
		this.estoque = estoque;
		this.precoLocacao = precoLocacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public BigDecimal getPrecoLocacao() {
		return precoLocacao;
	}

	public void setPrecoLocacao(BigDecimal precoLocacao) {
		this.precoLocacao = precoLocacao;
	}

	public boolean isSemEstoque() {
		return getEstoque() == null || getEstoque() == 0;
	}
}