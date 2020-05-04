package com.test.crud.aplication.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.test.crud.aplication.domain.Autor} entity.
 */
public class AutorDTO implements Serializable {
    
    private Long id;

    private String nome;

    private String email;

    
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AutorDTO autorDTO = (AutorDTO) o;
        if (autorDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), autorDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AutorDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
