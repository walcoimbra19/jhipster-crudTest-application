package com.test.crud.aplication.service;

import com.test.crud.aplication.service.dto.VendaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.test.crud.aplication.domain.Venda}.
 */
public interface VendaService {

    /**
     * Save a venda.
     *
     * @param vendaDTO the entity to save.
     * @return the persisted entity.
     */
    VendaDTO save(VendaDTO vendaDTO);

    /**
     * Get all the vendas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VendaDTO> findAll(Pageable pageable);

    /**
     * Get the "id" venda.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VendaDTO> findOne(Long id);

    /**
     * Delete the "id" venda.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
