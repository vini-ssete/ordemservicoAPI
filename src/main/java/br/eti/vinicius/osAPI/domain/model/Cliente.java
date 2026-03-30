package br.eti.vinicius.osAPI.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;// Importação necessária para o novo hashCode/equals
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 60)
    private String nome;
    
    @NotBlank
    @Email
    @Size(max = 255)
    private String email;
    
    @NotBlank
    @Size(max = 20)
    @Column(name = "telefone")
    private String fone;

    public Cliente() {
    }

    // ALTERADO: long -> Long
    public Cliente(Long id, String nome, String email, String fone) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.fone = fone;
    }

    // ALTERADO: long -> Long
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    @Override
    public int hashCode() {
        // ALTERADO: Simplificado para não dar erro de NullPointer quando o ID for nulo
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        // ALTERADO: Uso de Objects.equals para comparar objetos Long com segurança
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(id, other.id);
    }
}