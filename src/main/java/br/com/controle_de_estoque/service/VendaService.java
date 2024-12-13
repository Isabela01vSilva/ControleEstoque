package br.com.controle_de_estoque.service;

import br.com.controle_de_estoque.domain.produto.Produto;
import br.com.controle_de_estoque.domain.produto.ProdutoRepository;
import br.com.controle_de_estoque.domain.produtoVenda.ProdutoVenda;
import br.com.controle_de_estoque.domain.produtoVenda.ProdutoVendaRepository;
import br.com.controle_de_estoque.domain.venda.QtdVenda;
import br.com.controle_de_estoque.domain.venda.Venda;
import br.com.controle_de_estoque.domain.venda.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoVendaRepository produtoVendaRepository;

    public Venda vender(List<QtdVenda> request) {

        //Buscando todos os produtos vendidos pelo ID
        List<Produto> produtos = produtoRepository.findAllById(request.stream().map(QtdVenda::idVenda).toList());

        //Criando uma venda
        Venda vendas = new Venda(0.00);
        vendaRepository.save(vendas);

        //Criando a variavel do total da venda
        Double total = 0.0;

        for (int i = 0; i < produtos.size(); i++) {

            Produto produto = produtos.get(i);

            //Encontrando a quantidade vendida para o produto atual
            Integer qtdVendida = request.stream().filter(
                            qtdVenda -> qtdVenda.idVenda().equals(produto.getId())).map(QtdVenda::quantidadeVendido)
                    .toList().get(0);

            //Verificando se há estoque suficiente
            produto.temEstoque(qtdVendida);

            //Calculando o valor total da venda
            total += qtdVendida.doubleValue() * produto.getValorProduto();

            //Criando e salvando a venda
            ProdutoVenda produtoVenda = new ProdutoVenda();
            produtoVenda.setVenda(vendas);
            produtoVenda.setProduto(produto);
            produtoVenda.setQtdVendida(qtdVendida);
            produtoVendaRepository.save(produtoVenda);

            //Atualizando o Estoque
            produto.setQtdDisponivelDoProduto(produto.getQtdDisponivelDoProduto() - qtdVendida);
            produtoRepository.save(produto);
        }

        //Atualizando o valor total da venda
        vendas.setValorDaVenda(total);
        return vendaRepository.save(vendas);
    }

    public Venda atualizarVenda(Long id, List<QtdVenda> qtdVendido) {

        //Buscando a venda pelo ID
        Venda buscarVenda = vendaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        //Buscando todos os produtos vendidos pelo ID
        List<Produto> produtos = produtoRepository.findAllById(qtdVendido.stream().map(QtdVenda::idVenda).toList());

        //Iniciando o valor total da venda
        Double total = 0.0;

        for (int i = 0; i < produtos.size(); i++) {

            //Encontrando a quantidade vendida por Produto
            Produto produto = produtos.get(i);
            Integer qtdVendida = qtdVendido.stream().filter(
                            qtdVenda -> qtdVenda.idVenda().equals(produto.getId())).map(QtdVenda::quantidadeVendido)
                    .toList().get(0);

            //Devolvendo Produtos para o Estoque
            produto.setQtdDisponivelDoProduto(produto.getQtdDisponivelDoProduto() + qtdVendida);
            produtoRepository.save(produto);

            //Limpando a tabela ProdutoVendas
            produtoVendaRepository.deleteByVendaId(id);

            // Calculando o valor total da venda
            total += qtdVendida.doubleValue() * produto.getValorProduto();

            //Criando uma nova venda
            ProdutoVenda produtoVenda = new ProdutoVenda();
            produtoVenda.setVenda(buscarVenda);
            produtoVenda.setProduto(produto);
            produtoVenda.setQtdVendida(qtdVendida);
            produtoVendaRepository.save(produtoVenda);

            //Realizando a venda nova
            produto.setQtdDisponivelDoProduto(produto.getQtdDisponivelDoProduto() - qtdVendida);
            produtoRepository.save(produto);

        }

        //Retornando a venda Atualizada
        buscarVenda.setValorDaVenda(total);
        return vendaRepository.save(buscarVenda);
    }

}
