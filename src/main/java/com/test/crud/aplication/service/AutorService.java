package com.test.crud.aplication.service;

import com.test.crud.aplication.service.dto.AutorDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.test.crud.aplication.domain.Autor}.
 */
public interface AutorService {

    /**
     * Save a autor.
     *
     * @param autorDTO the entity to save.
     * @return the persisted entity.
     */
    AutorDTO save(AutorDTO autorDTO);

    /**
     * Get all the autors.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AutorDTO> findAll(Pageable pageable);

    /**
     * Get the "id" autor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AutorDTO> findOne(Long id);

    /**
     * Delete the "id" autor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
