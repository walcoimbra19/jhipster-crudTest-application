package com.test.crud.aplication.service;

import com.test.crud.aplication.service.dto.LivroDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.test.crud.aplication.domain.Livro}.
 */
public interface LivroService {

    /**
     * Save a livro.
     *
     * @param livroDTO the entity to save.
     * @return the persisted entity.
     */
    LivroDTO save(LivroDTO livroDTO);

    /**
     * Get all the livros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LivroDTO> findAll(Pageable pageable);

    /**
     * Get the "id" livro.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LivroDTO> findOne(Long id);

    /**
     * Delete the "id" livro.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
