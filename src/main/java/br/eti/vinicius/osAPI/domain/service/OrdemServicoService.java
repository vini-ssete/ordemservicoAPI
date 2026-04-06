/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.eti.vinicius.osAPI.domain.service;

import br.eti.vinicius.osAPI.domain.model.OrdemServico;
import br.eti.vinicius.osAPI.domain.model.StatusOrdemServico;
import br.eti.vinicius.osAPI.domain.repository.OrdemServicoRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author digma
 */
@Service
public class OrdemServicoService {
    
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;
    
    public OrdemServico criar(OrdemServico ordemServico) {
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(LocalDateTime.now());
        
        return ordemServicoRepository.save(ordemServico);
    }
    
    
    public OrdemServico salvar(OrdemServico ordemServico) {
   
    if (ordemServico.getId() != null) {
        OrdemServico osExistente = ordemServicoRepository.findById(ordemServico.getId())
                .orElseThrow(() -> new RuntimeException("Ordem de serviço não encontrada"));
        
        ordemServico.setDataAbertura(osExistente.getDataAbertura());
        
        if (ordemServico.getStatus() == null) {
            ordemServico.setStatus(osExistente.getStatus());
        }
    } else {
        ordemServico.setDataAbertura(LocalDateTime.now());
        ordemServico.setStatus(StatusOrdemServico.ABERTA); // Define um status inicial
    }

    
    return ordemServicoRepository.save(ordemServico);
}

    public void excluir(Long id) {
        ordemServicoRepository.deleteById(id);
    }
}
