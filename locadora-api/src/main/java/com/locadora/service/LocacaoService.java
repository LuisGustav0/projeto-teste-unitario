package com.locadora.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import com.machinezoo.noexception.Exceptions;

public class LocacaoService {

  public Locacao alugarFilme(Usuario usuario, List<Filme> listaFilme) throws LocadoraException,
      FilmeSemEstoqueException {
    if (usuario == null) {
      throw new LocadoraException(UtilException.USUARIO_NULL_OU_VAZIO);
    }

    if (listaFilme == null || listaFilme.isEmpty()) {
      throw new LocadoraException(UtilException.FILME_NULL_OU_VAZIO);
    }

    for (Filme filmeTO : listaFilme) {
      if (filmeTO.isSemEstoque()) {
        throw new FilmeSemEstoqueException(UtilException.FILME_SEM_ESTOQUE);
      }
    }

    Locacao locacao = new Locacao();
    locacao.setListaFilme(listaFilme);
    locacao.setUsuario(usuario);
    locacao.setDataLocacao(LocalDate.now());

    BigDecimal valorTotalLocacao = listaFilme
        .stream()
        .map(Filme::getPrecoLocacao)
        .reduce(BigDecimal::add)
        .get();

    locacao.setValor(valorTotalLocacao);

    // Entrega no dia seguinte,
    LocalDate dataEntrega = LocalDate.now().plusDays(1);
    locacao.setDataRetorno(dataEntrega);

    // Salvando a locacao...
    // TODO adicionar método para salvar

    return locacao;
  }
}