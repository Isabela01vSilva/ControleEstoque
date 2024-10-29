package br.com.controle_de_estoque.domain.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "produtos")
@Entity(name = "Produto")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "preco")
    private Double valorProduto;

    @Column(name = "quantidade")
    private Integer qtdDisponivelDoProduto;

    public Produto(DadosCadastroProduto dados) {
        this.nome = dados.nome();
        this.valorProduto = dados.valorProduto();
        this.qtdDisponivelDoProduto = dados.qtdDisponivelDoProduto();
    }

    public void atualizarInformacoes(DadosAtualizacaoProduto dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.valorProduto() != null) {
            this.valorProduto = dados.valorProduto();
        }
        if (dados.qtdDisponivelDoProduto() != null) {
            this.qtdDisponivelDoProduto = dados.qtdDisponivelDoProduto();
        }
    }

}
