package br.com.controle_de_estoque.controller;


import br.com.controle_de_estoque.domain.venda.*;
import br.com.controle_de_estoque.service.VendaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    @Transactional
    public ResponseEntity vender(@RequestBody List<QtdVenda> request) {
        return ResponseEntity.ok(vendaService.vender(request));
    }

}
