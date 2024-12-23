package br.com.controle_de_estoque.domain.venda;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idVenda")
    private Long id;

    private Double valorDaVenda;

    public Venda(Double valorDaVenda) {
        this.valorDaVenda = valorDaVenda;
    }

}