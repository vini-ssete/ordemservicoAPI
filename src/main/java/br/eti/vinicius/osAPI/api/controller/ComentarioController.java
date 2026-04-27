package br.eti.vinicius.osAPI.api.controller;

import br.eti.vinicius.osAPI.domain.dto.ComentarioInputDTO;
import br.eti.vinicius.osAPI.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private OrdemServicoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@PathVariable Long ordemServicoId, 
                          @RequestBody @Valid ComentarioInputDTO input) {
        
        // Chama o service passando o ID da URL e a descrição do JSON
        service.adicionarComentario(ordemServicoId, input.descricao());
    }
}