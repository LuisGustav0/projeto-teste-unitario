package com.locadora.service;

import java.time.LocalDate;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme)	throws LocadoraException,
																														FilmeSemEstoqueException {
		if (usuario == null) {
			throw new LocadoraException(UtilException.USUARIO_NULL_OU_VAZIO);
		}

		if (filme == null) {
			throw new LocadoraException(UtilException.FILME_NULL_OU_VAZIO);
		}

		if (filme.isSemEstoque()) {
			throw new FilmeSemEstoqueException(UtilException.FILME_SEM_ESTOQUE);
		}

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