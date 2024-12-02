package br.com.controle_de_estoque.domain.produtoVenda;

import br.com.controle_de_estoque.domain.produto.Produto;
import br.com.controle_de_estoque.domain.venda.Venda;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "produtosVendidos")
@Entity(name = "ProdutoVenda")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProdutoVendido")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    private Integer qtdVendida;


}
