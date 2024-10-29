package br.com.controle_de_estoque.domain.produto;

public record DadosDetalhamentoProduto (Long id,
                                        String nome,
                                        Double valorDoProduto,
                                        Integer qtdDisponivelDoProduto){

    public DadosDetalhamentoProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getValorProduto(), produto.getQtdDisponivelDoProduto());
    }
}
