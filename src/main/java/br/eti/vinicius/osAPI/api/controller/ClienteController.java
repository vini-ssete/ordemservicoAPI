/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.vinicius.osAPI.api.controller;

import br.eti.vinicius.osAPI.domain.model.Cliente;
import br.eti.vinicius.osAPI.domain.repository.ClienteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author digma
 */
@RestController
public class ClienteController {
    
//    @PersistenceContext
//    private EntityManager manager;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
//     List<Cliente> listaClientes;
    
    @GetMapping ("/clientes")
    public List<Cliente> listas() {
        return clienteRepository.findAll();
    }
        
    @GetMapping ("/clientes/{clienteID}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long clienteID) {
        
        Optional<Cliente> cliente = clienteRepository.findById(clienteID);
        
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/clientes")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    @PutMapping("/clientes/{clienteID}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteID,
                                             @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteID);
        cliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
    
    @DeleteMapping("/clientes/{clienteID}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteID) {
         if (!clienteRepository.existsById(clienteID)) {
            return ResponseEntity.notFound().build();
        }
        clienteRepository.deleteById(clienteID);
        return ResponseEntity.noContent().build();
    }
    
//        return clienteRepository.findByNome("KGe");
//        return clienteRepository.findByNomeContaining("Silva");
        
//        return manager.createQuery("from Cliente", Cliente.class)
//                .getResultList();
        
//        listaClientes = new ArrayList<Cliente>();
//        listaClientes.add(new Cliente(1, "KGe", "kge@teste.com", "11-99999-9999"));
//        listaClientes.add(new Cliente(1, "Maria", "maria@teste.com", "11-88888-8888"));
//        listaClientes.add(new Cliente(1, "Joao", "joao@teste.com", "11-77777-7777"));
        
//        return listaClientes;
      
}
