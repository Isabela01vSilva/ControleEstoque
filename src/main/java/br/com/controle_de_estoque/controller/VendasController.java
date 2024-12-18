package br.com.controle_de_estoque.controller;


import br.com.controle_de_estoque.controller.request.VendaRequest;
import br.com.controle_de_estoque.controller.response.ProdutoVendaResponse;
import br.com.controle_de_estoque.controller.response.ProdutosVendidosResponse;
import br.com.controle_de_estoque.controller.response.VendaResponse;
import br.com.controle_de_estoque.domain.venda.*;
import br.com.controle_de_estoque.service.VendaService;

import br.com.controle_de_estoque.service.dto.ProdutoVendaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaResponse> vender(@RequestBody List<VendaRequest> request) {
        Venda venda = vendaService.vender(request.stream().map(req -> new QtdVenda(req.id(), req.qtdVendido())).toList());
        return ResponseEntity.ok(new VendaResponse(venda.getId(), venda.getValorDaVenda()));
    }

    @PutMapping("{id}")
    public ResponseEntity<VendaResponse> atualizarVenda(@PathVariable Long id, @RequestBody List<VendaRequest> request) {
        Venda atualizarVenda = vendaService.atualizarVenda(id, request.stream().map(req -> new QtdVenda(req.id(), req.qtdVendido())).toList());
        return ResponseEntity.ok(new VendaResponse(atualizarVenda.getId(), atualizarVenda.getValorDaVenda()));

    }

    @GetMapping("{id}")
    public ResponseEntity<ProdutoVendaResponse> buscarVendas(@PathVariable Long id) {
        ProdutoVendaDTO vendaEncotradaDTO = vendaService.buscarVendas(id);
        return ResponseEntity.ok(new ProdutoVendaResponse(
                        vendaEncotradaDTO.idVenda(),
                        "R$: " + vendaEncotradaDTO.valorDaVenda(),
                        vendaEncotradaDTO.produtosVendidos().stream().map(
                                produtosVendidosDTO -> new ProdutosVendidosResponse(
                                        produtosVendidosDTO.idProduto(),
                                        produtosVendidosDTO.nome(),
                                        produtosVendidosDTO.qtdVendida(),
                                        "R$: " + produtosVendidosDTO.valorProduto()
                                )
                        ).toList(),
                        vendaEncotradaDTO.qtdVendida()
                )
        );
    }

}
