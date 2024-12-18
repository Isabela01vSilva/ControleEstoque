package br.com.controle_de_estoque.controller.response;

public record ProdutosVendidosResponse (Long idProduto,
                                        String nome,
                                        Integer quantidadeVendida,
                                        String valorUnitario) {
}

