package com.locadora.service;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CalculoValorLocacaoTest {

  private LocacaoService service;

  @Parameter
  public List<Filme> listaFilme;

  @Parameter(value = 1)
  public BigDecimal valorLocacao;

  @Parameter(value = 2)
  public String cenario;

  @Before
  public void onBefore() {
    service = new LocacaoService();
  }

  private static Filme filme1 = new Filme("Filme 1", 2, new BigDecimal(4.0));
  private static Filme filme2 = new Filme("Filme 2", 2, new BigDecimal(4.0));
  private static Filme filme3 = new Filme("Filme 3", 2, new BigDecimal(4.0));
  private static Filme filme4 = new Filme("Filme 4", 2, new BigDecimal(4.0));
  private static Filme filme5 = new Filme("Filme 5", 2, new BigDecimal(4.0));
  private static Filme filme6 = new Filme("Filme 6", 2, new BigDecimal(4.0));
  private static Filme filme7 = new Filme("Filme 7", 2, new BigDecimal(4.0));

  @Parameters(name = "{2}")
  public static Collection<Object[]> getParametros() {
    return Arrays.asList(new Object[][]{
        {Arrays.asList(filme1, filme2), new BigDecimal(8.0), "Locacao de 2 filmes sem desconto"},
        {Arrays.asList(filme1, filme2, filme3), new BigDecimal(11.0), "Locacao de 3 filmes com 25% desconto"},
        {Arrays.asList(filme1, filme2, filme3, filme4), new BigDecimal(13.0), "Locacao de 2 filmes com 50% desconto"},
        {Arrays.asList(filme1, filme2, filme3, filme4, filme5), new BigDecimal(14.0), "Locacao de 2 filmes com 75% desconto"},
        {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6), new BigDecimal(14.0), "Locacao de 6 filmes 100% desconto"},
        {Arrays.asList(filme1, filme2, filme3, filme4, filme5, filme6, filme7), new BigDecimal(18.0), "Locacao de 7 filmes sem desconto"}
    });
  }

  @Test
  public void deveCalcularValorLocacaoConsiderenadoDescontos() throws LocadoraException, FilmeSemEstoqueException {
    // Cenario
    Usuario usuario = new Usuario("Usuario 1");

    // Acao
    Locacao locacao = service.alugarFilme(usuario, listaFilme);

    // Verificao
    Assert.assertThat(locacao.getValor(), Matchers.comparesEqualTo(valorLocacao));
  }
}
