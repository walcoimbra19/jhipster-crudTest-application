package com.test.crud.aplication.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A Venda.
 */
@Entity
@Table(name = "venda")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Venda implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "livro")
    private String livro;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "total")
    private String total;

    @ManyToOne
    @JsonIgnoreProperties("vendas")
    private Cliente cliente;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLivro() {
        return livro;
    }

    public Venda livro(String livro) {
        this.livro = livro;
        return this;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public LocalDate getData() {
        return data;
    }

    public Venda data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTotal() {
        return total;
    }

    public Venda total(String total) {
        this.total = total;
        return this;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Venda cliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Venda)) {
            return false;
        }
        return id != null && id.equals(((Venda) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Venda{" +
            "id=" + getId() +
            ", livro='" + getLivro() + "'" +
            ", data='" + getData() + "'" +
            ", total='" + getTotal() + "'" +
            "}";
    }
}
