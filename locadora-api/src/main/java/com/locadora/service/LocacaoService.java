package com.locadora.service;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilMensagem;
import com.locadora.helper.ThrowingConsumer;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import com.locadora.util.UtilDate;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class LocacaoService {

    private BigDecimal getValorTotalLocacaoPorListaFilme(List<Filme> listaFilme) {

        return null;
    }

    private void validarFilmeSemEstoque(Filme filme) throws FilmeSemEstoqueException {
        Optional.ofNullable(filme)
                .filter(Filme::isComEstoque)
                .orElseThrow(() -> new FilmeSemEstoqueException(UtilMensagem.FILME_SEM_ESTOQUE));
    }

    private void validarListaFilmeSeNaoExisteEstoque(List<Filme> listaFilme) {
        listaFilme.forEach((ThrowingConsumer<Filme>) this::validarFilmeSemEstoque);
    }

    public Locacao alugarFilme(Usuario usuario, List<Filme> listaFilme) throws LocadoraException {
        if (usuario == null) {
            throw new LocadoraException(UtilMensagem.USUARIO_NULL_OU_VAZIO);
        }

        if (listaFilme == null || listaFilme.isEmpty()) {
            throw new LocadoraException(UtilMensagem.FILME_NULL_OU_VAZIO);
        }

        validarListaFilmeSeNaoExisteEstoque(listaFilme);

        BigDecimal valorTotalLocacao = getValorTotalLocacaoPorListaFilme(listaFilme);

        Locacao locacao = new Locacao();
        locacao.setListaFilme(listaFilme);
        locacao.setUsuario(usuario);
        locacao.setDataLocacao(LocalDate.now());
        locacao.setValor(valorTotalLocacao);

        // Entrega no dia seguinte,
        LocalDate dataEntrega = LocalDate.now();
        dataEntrega = UtilDate.verificarDiaDaSemana(dataEntrega, DayOfWeek.SATURDAY) ? dataEntrega.plusDays(2) : dataEntrega.plusDays(1);
        locacao.setDataRetorno(dataEntrega);

        // Salvando a locacao...
        // TODO adicionar método para salvar

        return locacao;
    }
}
