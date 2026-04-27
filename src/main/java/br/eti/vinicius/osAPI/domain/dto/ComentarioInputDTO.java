package br.eti.vinicius.osAPI.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record ComentarioInputDTO(
    @NotBlank String descricao
) {}