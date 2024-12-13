package br.com.controle_de_estoque.controller;


import br.com.controle_de_estoque.controller.request.VendaRequest;
import br.com.controle_de_estoque.controller.response.VendaResponse;
import br.com.controle_de_estoque.domain.venda.*;
import br.com.controle_de_estoque.service.VendaService;

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
        Venda venda = vendaService.vender(request.stream().map(req -> new QtdVenda(req.id(), req.qtdVendido())).toList())
        return ResponseEntity.ok(new VendaResponse(venda.getId(), venda.getValorDaVenda()));
    }

    @PutMapping("{idVenda}")
    public ResponseEntity atualizarVenda(@PathVariable Long id, @RequestBody List<VendaRequest> request) {
       Venda atualizarVenda = vendaService.atualizarVenda(id);

    }

}
