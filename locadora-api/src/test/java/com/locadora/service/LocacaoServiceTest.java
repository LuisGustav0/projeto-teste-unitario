package com.locadora.service;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilMensagem;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import com.locadora.util.UtilDate;
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

import static com.locadora.matchers.MatchersProprios.caiEmUmaSegunda;
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
        List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 1,  BigDecimal.valueOf(5.0), 1));

        // Acao
        Locacao locacao = service.alugarFilme(usuario, listaFilme);

        // Verificacao
        error.checkThat(locacao.getValor(), is(equalTo(BigDecimal.valueOf(5.0))));
        //error.checkThat(locacao.getDataLocacao().isEqual(LocalDate.now()), is(true));
        //error.checkThat(locacao.getDataRetorno().isEqual(LocalDate.now().plusDays(1)), is(true));

//    error.checkThat(locacao.getDataLocacao(), ehHoje());
        //error.checkThat(locacao.getDataRetorno().isEqual(LocalDate.now().plusDays(1)), is(true));
    }


    @Test
    public void naoPermitidoAlugarFilmeComUsuarioNullOuVazio() throws FilmeSemEstoqueException {
        // Cenario
        List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 1, BigDecimal.valueOf(5.0), 1));

        // Acao
        try {
            service.alugarFilme(null, listaFilme);
            Assert.fail();
        } catch (LocadoraException ex) {
            assertThat(ex.getMessage(), is(UtilMensagem.USUARIO_NULL_OU_VAZIO));
        }
    }

    @Test
    public void naoPermitidoAlugarFilmeComFilmeNullOuVazio() throws LocadoraException, FilmeSemEstoqueException {
        // Cenario
        Usuario usuario = new Usuario("Usuario 1");

        // Exception
        expectedException.expect(LocadoraException.class);
        expectedException.expectMessage(UtilMensagem.FILME_NULL_OU_VAZIO);

        // Acao
        service.alugarFilme(usuario, null);
    }

    @Test(expected = FilmeSemEstoqueException.class)
    public void naoPermitidoAlugarFilmeSemEstoque() throws Exception {
        // Cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> listaFilme = Collections.singletonList(new Filme("Filme 1", 0, BigDecimal.valueOf(5.0), 1));

        // Acao
        service.alugarFilme(usuario, listaFilme);
    }

    @Test
    public void deveDevolverNaSegundaSeAlugarNoSabado() throws LocadoraException, FilmeSemEstoqueException {
        //
//        Assume.assumeTrue(UtilDate.verificarDiaDaSemana(LocalDate.now(), DayOfWeek.SATURDAY));

        // Cenario
        Usuario usuario = new Usuario("Usuario 1");
        List<Filme> listaFilme = Arrays.asList(
                new Filme("Filme 1", 2, BigDecimal.valueOf(4.0), 1),
                new Filme("Filme 2", 2, BigDecimal.valueOf(4.0), 2),
                new Filme("Filme 3", 3, BigDecimal.valueOf(4.0), 3)
        );

        // Acao
        Locacao locacao = service.alugarFilme(usuario, listaFilme);

        // Verificacao
        Assert.assertThat(locacao.getDataRetorno(), caiEmUmaSegunda());
    }
}
