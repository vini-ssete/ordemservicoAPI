/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.vinicius.osAPI.api.controller;

import br.eti.vinicius.osAPI.domain.model.Cliente;
import br.eti.vinicius.osAPI.domain.model.OrdemServico;
import br.eti.vinicius.osAPI.domain.service.OrdemServicoService;
import br.eti.vinicius.osAPI.domain.repository.OrdemServicoRepository;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {
    
    @Autowired
    private OrdemServicoService ordemServicoService;
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@RequestBody OrdemServico ordemServico) {
        return ordemServicoService.criar(ordemServico);
    }
    
    @GetMapping
    public List<OrdemServico> listas() {
        return ordemServicoRepository.findAll();
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<OrdemServico> buscar(@PathVariable Long id) {
        
        Optional<OrdemServico> ordemServico = ordemServicoRepository.findById(id);
        
        if (ordemServico.isPresent()) {
            return ResponseEntity.ok(ordemServico.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/cliente/{clienteId}")
    public List<OrdemServico> listarPorCliente(@PathVariable Long clienteId) {
        return ordemServicoRepository.findByClienteId(clienteId);
}
    
    @PutMapping("/{id}")
    public ResponseEntity<OrdemServico> atualizar(@Valid @PathVariable Long id,
                                             @RequestBody OrdemServico ordemServico) {
        if (!ordemServicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ordemServico.setId(id);
        ordemServico = ordemServicoService.salvar(ordemServico);
        return ResponseEntity.ok(ordemServico);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
         if (!ordemServicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ordemServicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
