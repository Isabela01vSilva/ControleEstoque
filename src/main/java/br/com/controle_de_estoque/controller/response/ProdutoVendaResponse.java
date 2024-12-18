package br.com.controle_de_estoque.controller.response;

import java.util.List;

public record ProdutoVendaResponse(Long idVenda,
                                   String valorTotal,
                                   List<ProdutosVendidosResponse> produtosVendidos,
                                   Integer totalDeProdutosVendidos) {
}
