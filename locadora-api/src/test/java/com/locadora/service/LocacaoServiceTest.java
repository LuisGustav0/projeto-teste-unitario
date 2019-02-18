package com.locadora.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void locacaoTest() throws Exception {
		// Cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 1, 5.0);

		// Acao
		Locacao locacao = service.alugarFilme(usuario, filme);

		// Verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getDataLocacao().isEqual(LocalDate.now()), is(true));
		error.checkThat(locacao.getDataRetorno().isEqual(LocalDate.now().plusDays(1)), is(true));
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void locacaoTest_filmeSemEstoque() throws Exception {
		// Cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", null, 5.0);

		// Acao
		service.alugarFilme(usuario, filme);
	}

	@Test
	public void locacaoTest_usuarioIsNullOuVazio() throws FilmeSemEstoqueException {
		// Cenario
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 1", 0, 5.0);

		// Acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException ex) {
			assertThat(ex.getMessage(), is(UtilException.USUARIO_NULL_OU_VAZIO));
		}
	}

	@Test
	public void locacaoTest_filmeIsNullOuVazio()	throws LocadoraException,
																								FilmeSemEstoqueException {
		// Cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");

		// Exception
		expectedException.expect(LocadoraException.class);
		expectedException.expectMessage(UtilException.FILME_NULL_OU_VAZIO);

		// Acao
		service.alugarFilme(usuario, null);
	}
}
