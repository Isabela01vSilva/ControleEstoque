package br.com.controle_de_estoque.domain.produtoVenda;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

    void deleteByVendaId(Long id);
}
