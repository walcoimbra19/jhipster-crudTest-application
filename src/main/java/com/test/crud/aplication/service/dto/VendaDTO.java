package com.test.crud.aplication.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.test.crud.aplication.domain.Venda} entity.
 */
public class VendaDTO implements Serializable {
    
    private Long id;

    private String livro;

    private LocalDate data;

    private String total;


    private Long clienteId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VendaDTO vendaDTO = (VendaDTO) o;
        if (vendaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendaDTO{" +
            "id=" + getId() +
            ", livro='" + getLivro() + "'" +
            ", data='" + getData() + "'" +
            ", total='" + getTotal() + "'" +
            ", clienteId=" + getClienteId() +
            "}";
    }
}
