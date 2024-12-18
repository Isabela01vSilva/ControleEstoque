package br.com.controle_de_estoque.service.dto;

import java.util.List;

public record ProdutoVendaDTO (Long idVenda,
        Double valorDaVenda,
        List<ProdutosVendidosDTO> produtosVendidos,
        Integer qtdVendida){

}
