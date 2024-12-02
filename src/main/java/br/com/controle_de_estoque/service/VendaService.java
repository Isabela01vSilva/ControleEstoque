package br.com.controle_de_estoque.service;

import br.com.controle_de_estoque.domain.produto.Produto;
import br.com.controle_de_estoque.domain.produto.ProdutoRepository;
import br.com.controle_de_estoque.domain.produtoVenda.ProdutoVenda;
import br.com.controle_de_estoque.domain.produtoVenda.ProdutoVendaRepository;
import br.com.controle_de_estoque.domain.venda.QtdVenda;
import br.com.controle_de_estoque.domain.venda.Venda;
import br.com.controle_de_estoque.domain.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public Venda vender(List<QtdVenda> request) {
        List<Produto> produtos = produtoRepository.findAllById(request.stream().map(QtdVenda::id).toList());
        Venda vendas = new Venda(0.00);
        vendaRepository.save(vendas);

        Double total = 0.0;

        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);

            Integer qtdVendida = request.stream().filter(
                            qtdVenda -> qtdVenda.id().equals(produto.getId())).map(QtdVenda::qtd)
                    .toList().get(0);

            produto.temEstoque(qtdVendida);

            total += qtdVendida.doubleValue() * produto.getValorProduto();

            ProdutoVenda produtoVenda = new ProdutoVenda();
            produtoVenda.setVenda(vendas);
            produtoVenda.setProduto(produto);
            produtoVenda.setQtdVendida(qtdVendida);
            produtoVendaRepository.save(produtoVenda);

            produto.setQtdDisponivelDoProduto(produto.getQtdDisponivelDoProduto() - qtdVendida);
            produtoRepository.save(produto);
        }

        vendas.setValorDaVenda(total);
        return vendaRepository.save(vendas);
    }
}
