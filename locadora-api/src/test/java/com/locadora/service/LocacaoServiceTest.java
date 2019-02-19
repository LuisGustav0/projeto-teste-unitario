package com.locadora.service;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocacaoServiceTest {

  @Rule
  public ErrorCollector error = new ErrorCollector();

  @Rule
  public ExpectedException expectedException = ExpectedException.none();

  private LocacaoService service;

  @Before
  public void onBefore() {
    System.out.println("onBefore");
    service = new LocacaoService();
  }

  @After
  public void onAfter() {
    System.out.println("onAfter");
  }

  @Test
  public void locacaoTest() throws Exception {
    // Cenario
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 1, new BigDecimal(5.0)));

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificacao
    error.checkThat(locacao.getValor(), is(equalTo(new BigDecimal(5.0))));
    error.checkThat(locacao.getDataLocacao().isEqual(LocalDate.now()), is(true));
    error.checkThat(locacao.getDataRetorno().isEqual(LocalDate.now().plusDays(1)), is(true));
  }

  @Test(expected = FilmeSemEstoqueException.class)
  public void locacaoTest_filmeSemEstoque() throws Exception {
    // Cenario
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 0, new BigDecimal(5.0)));

    // Acao
    service.alugarFilme(usuario, listaFilme);
  }

  @Test
  public void locacaoTest_usuarioIsNullOuVazio() throws FilmeSemEstoqueException {
    // Cenario
    List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 1, new BigDecimal(5.0)));

    // Acao
    try {
      service.alugarFilme(null, listaFilme);
      Assert.fail();
    } catch (LocadoraException ex) {
      assertThat(ex.getMessage(), is(UtilException.USUARIO_NULL_OU_VAZIO));
    }
  }

  @Test
  public void locacaoTest_filmeIsNullOuVazio() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    Usuario usuario = new Usuario("Usuario 1");

    // Exception
    expectedException.expect(LocadoraException.class);
    expectedException.expectMessage(UtilException.FILME_NULL_OU_VAZIO);

    // Acao
    service.alugarFilme(usuario, null);
  }
}
