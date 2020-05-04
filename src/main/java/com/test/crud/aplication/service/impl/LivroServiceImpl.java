package com.test.crud.aplication.service.impl;

import com.test.crud.aplication.service.LivroService;
import com.test.crud.aplication.domain.Livro;
import com.test.crud.aplication.repository.LivroRepository;
import com.test.crud.aplication.service.dto.LivroDTO;
import com.test.crud.aplication.service.mapper.LivroMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Livro}.
 */
@Service
@Transactional
public class LivroServiceImpl implements LivroService {

    private final Logger log = LoggerFactory.getLogger(LivroServiceImpl.class);

    private final LivroRepository livroRepository;

    private final LivroMapper livroMapper;

    public LivroServiceImpl(LivroRepository livroRepository, LivroMapper livroMapper) {
        this.livroRepository = livroRepository;
        this.livroMapper = livroMapper;
    }

    /**
     * Save a livro.
     *
     * @param livroDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LivroDTO save(LivroDTO livroDTO) {
        log.debug("Request to save Livro : {}", livroDTO);
        Livro livro = livroMapper.toEntity(livroDTO);
        livro = livroRepository.save(livro);
        return livroMapper.toDto(livro);
    }

    /**
     * Get all the livros.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LivroDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Livros");
        return livroRepository.findAll(pageable)
            .map(livroMapper::toDto);
    }

    /**
     * Get one livro by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LivroDTO> findOne(Long id) {
        log.debug("Request to get Livro : {}", id);
        return livroRepository.findById(id)
            .map(livroMapper::toDto);
    }

    /**
     * Delete the livro by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Livro : {}", id);
        livroRepository.deleteById(id);
    }
}
