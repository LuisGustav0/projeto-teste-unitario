package com.locadora.service;

import com.locadora.exceptions.FilmeSemEstoqueException;
import com.locadora.exceptions.LocadoraException;
import com.locadora.exceptions.UtilException;
import com.locadora.model.Filme;
import com.locadora.model.Locacao;
import com.locadora.model.Usuario;
import com.locadora.util.UtilDate;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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

    BigDecimal[] valorTotalLocacao = {BigDecimal.ZERO};
    AtomicInteger index = new AtomicInteger();
    listaFilme.forEach(filmeTO -> {
      index.getAndIncrement();
      BigDecimal valorFilme = BigDecimal.ZERO;
      valorFilme = valorFilme.add(filmeTO.getPrecoLocacao());

      switch (index.get()) {
        case 3:
          valorFilme = valorFilme.multiply(new BigDecimal(0.75));
          break;

        case 4:
          valorFilme = valorFilme.multiply(new BigDecimal(0.5));
          break;

        case 5:
          valorFilme = valorFilme.multiply(new BigDecimal(0.25));
          break;

        case 6:
          valorFilme = valorFilme.multiply(BigDecimal.ZERO);
          break;
      }
      valorTotalLocacao[0] = valorTotalLocacao[0].add(valorFilme);
    });

    locacao.setValor(valorTotalLocacao[0]);

    // Entrega no dia seguinte,
    LocalDate dataEntrega = LocalDate.now();
    dataEntrega = UtilDate.verificarDiaDaSemana(dataEntrega, DayOfWeek.SATURDAY) ? dataEntrega.plusDays(2) : dataEntrega.plusDays(1);
    locacao.setDataRetorno(dataEntrega);

    // Salvando a locacao...
    // TODO adicionar método para salvar

    return locacao;
  }
}