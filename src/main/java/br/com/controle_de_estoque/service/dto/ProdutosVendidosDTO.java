package br.com.controle_de_estoque.service.dto;

public record ProdutosVendidosDTO(Long idProduto,
                                  String nome,
                                  Integer qtdVendida,
                                  Double valorProduto) {
}
