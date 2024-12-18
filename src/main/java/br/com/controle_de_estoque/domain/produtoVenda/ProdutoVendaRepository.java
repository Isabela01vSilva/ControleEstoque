package br.com.controle_de_estoque.domain.produtoVenda;

import br.com.controle_de_estoque.domain.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

    void deleteByVendaId(Long id);

    List<ProdutoVenda> findByVendaId(Long id);
}
