package br.com.controle_de_estoque.domain.produto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
        @NotNull
        Long id,
        String nome,
        Double valorProduto,
        Integer qtdDisponivelDoProduto
) {
}
