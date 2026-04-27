package br.eti.vinicius.osAPI.api.controller;

import br.eti.vinicius.osAPI.domain.dto.ComentarioInputDTO;
import br.eti.vinicius.osAPI.domain.model.Comentario;
import br.eti.vinicius.osAPI.domain.repository.ComentarioRepository;
import br.eti.vinicius.osAPI.domain.repository.OrdemServicoRepository;
import br.eti.vinicius.osAPI.domain.service.OrdemServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordens-servico") // Base mais simples
public class ComentarioController {

    @Autowired
    private OrdemServicoService service;

    // URL para Adicionar: POST http://localhost:8080/ordens-servico/1/comentarios
    @PostMapping("/{ordemServicoId}/comentarios")
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@PathVariable Long ordemServicoId, 
                          @RequestBody @Valid ComentarioInputDTO input) {
        service.adicionarComentario(ordemServicoId, input.descricao());
    }

    // URL para Excluir: DELETE http://localhost:8080/ordens-servico/excluir-comentarios/5
    @DeleteMapping("/excluir-comentarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirComentario(@PathVariable Long id) {
        service.excluirComentario(id);
    }
    
    // URL Final: PUT http://localhost:8080/ordens-servico/ediar-comentarios/1
    @PutMapping("/editar-comentarios/{id}")
    public Comentario atualizar(@PathVariable Long id, 
                                @RequestBody @Valid ComentarioInputDTO input) {
    
    // Chama o service passando o ID do comentário e a nova descrição 
    return service.atualizarComentario(id, input.descricao());
}
}