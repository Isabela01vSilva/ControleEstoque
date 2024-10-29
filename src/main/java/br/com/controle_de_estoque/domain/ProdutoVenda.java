package br.com.controle_de_estoque.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Long idProduto;
    private Long idVenda;

}
