package br.eti.vinicius.osAPI.domain.repository;

import br.eti.vinicius.osAPI.domain.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}