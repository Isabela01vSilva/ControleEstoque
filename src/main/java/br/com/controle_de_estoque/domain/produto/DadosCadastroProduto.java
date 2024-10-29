package br.com.controle_de_estoque.domain.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(

        @NotBlank
        String nome,
        @NotNull
        Double valorProduto,
        @NotNull
        Integer qtdDisponivelDoProduto
) {

}
