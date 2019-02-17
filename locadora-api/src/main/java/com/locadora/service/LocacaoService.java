package com.locadora.service;

import java.time.LocalDate;

import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(LocalDate.now());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		LocalDate dataEntrega = LocalDate.now();
		dataEntrega = dataEntrega.plusDays(1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}
}