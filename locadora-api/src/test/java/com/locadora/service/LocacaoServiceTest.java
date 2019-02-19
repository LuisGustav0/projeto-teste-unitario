package com.locadora.service;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import com.locadora.util.UtilDate;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
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
    service = new LocacaoService();
  }

  @Test
  public void permitidoAlugarFilme() throws Exception {
    //
    Assume.assumeFalse(UtilDate.verificarDiaDaSemana(LocalDate.now(), DayOfWeek.SATURDAY));

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


  @Test
  public void naoPermitidoAlugarFilmeComUsuarioNullOuVazio() throws FilmeSemEstoqueException {
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
  public void naoPermitidoAlugarFilmeComFilmeNullOuVazio() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    Usuario usuario = new Usuario("Usuario 1");

    // Exception
    expectedException.expect(LocadoraException.class);
    expectedException.expectMessage(UtilException.FILME_NULL_OU_VAZIO);

    // Acao
    service.alugarFilme(usuario, null);
  }

  @Test(expected = FilmeSemEstoqueException.class)
  public void naoPermitidoAlugarFilmeSemEstoque() throws Exception {
    // Cenario
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 0, new BigDecimal(5.0)));

    // Acao
    service.alugarFilme(usuario, listaFilme);
  }

  @Test
  public void devePagar75PorcentoDescontoNo3Filme() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    BigDecimal valorLocacao = new BigDecimal(11.0);
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Arrays.asList(
        new Filme("Filme 1", 2, new BigDecimal(4.0)),
        new Filme("Filme 2", 3, new BigDecimal(4.0)),
        new Filme("Filme 3", 5, new BigDecimal(4.0))
    );

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificao
    Assert.assertThat(locacao.getValor(), Matchers.comparesEqualTo(valorLocacao));
  }

  @Test
  public void devePagar50PorcentoDescontoNo4Filme() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    BigDecimal valorLocacao = new BigDecimal(13.0);
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Arrays.asList(
        new Filme("Filme 1", 2, new BigDecimal(4.0)),
        new Filme("Filme 2", 3, new BigDecimal(4.0)),
        new Filme("Filme 3", 5, new BigDecimal(4.0)),
        new Filme("Filme 4", 5, new BigDecimal(4.0))
    );

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificao
    Assert.assertThat(locacao.getValor(), Matchers.comparesEqualTo(valorLocacao));
  }

  @Test
  public void devePagar25PorcentoDescontoNo5Filme() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    BigDecimal valorLocacao = new BigDecimal(14.0);
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Arrays.asList(
        new Filme("Filme 1", 2, new BigDecimal(4.0)),
        new Filme("Filme 2", 3, new BigDecimal(4.0)),
        new Filme("Filme 3", 5, new BigDecimal(4.0)),
        new Filme("Filme 4", 5, new BigDecimal(4.0)),
        new Filme("Filme 5", 5, new BigDecimal(4.0))
    );

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificao
    Assert.assertThat(locacao.getValor(), Matchers.comparesEqualTo(valorLocacao));
  }

  @Test
  public void devePagar100PorcentoDescontoNo6Filme() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    BigDecimal valorLocacao = new BigDecimal(14.0);
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Arrays.asList(
        new Filme("Filme 1", 2, new BigDecimal(4.0)),
        new Filme("Filme 2", 3, new BigDecimal(4.0)),
        new Filme("Filme 4", 5, new BigDecimal(4.0)),
        new Filme("Filme 5", 5, new BigDecimal(4.0)),
        new Filme("Filme 6", 5, new BigDecimal(4.0))
    );

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificao
    Assert.assertThat(locacao.getValor(), Matchers.comparesEqualTo(valorLocacao));
  }

  @Test
  public void deveDevolverNaSegundaSeAlugarNoSabado() throws LocadoraException, FilmeSemEstoqueException {
    //
    Assume.assumeTrue(UtilDate.verificarDiaDaSemana(LocalDate.now(), DayOfWeek.SATURDAY));

    // Cenario
    Usuario usuario = new Usuario("Usuario 1");
    List<Filme> listaFilme = Arrays.asList(
        new Filme("Filme 1", 2, new BigDecimal(4.0))
    );

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificacao
    boolean isSegundaFeira = UtilDate.verificarDiaDaSemana(locacao.getDataRetorno(), DayOfWeek.MONDAY);
    Assert.assertTrue(isSegundaFeira);
  }
}
