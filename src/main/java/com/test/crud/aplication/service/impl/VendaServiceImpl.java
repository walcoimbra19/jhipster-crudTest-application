package com.test.crud.aplication.service.impl;

import com.test.crud.aplication.service.VendaService;
import com.test.crud.aplication.domain.Venda;
import com.test.crud.aplication.repository.VendaRepository;
import com.test.crud.aplication.service.dto.VendaDTO;
import com.test.crud.aplication.service.mapper.VendaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Venda}.
 */
@Service
@Transactional
public class VendaServiceImpl implements VendaService {

    private final Logger log = LoggerFactory.getLogger(VendaServiceImpl.class);

    private final VendaRepository vendaRepository;

    private final VendaMapper vendaMapper;

    public VendaServiceImpl(VendaRepository vendaRepository, VendaMapper vendaMapper) {
        this.vendaRepository = vendaRepository;
        this.vendaMapper = vendaMapper;
    }

    /**
     * Save a venda.
     *
     * @param vendaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public VendaDTO save(VendaDTO vendaDTO) {
        log.debug("Request to save Venda : {}", vendaDTO);
        Venda venda = vendaMapper.toEntity(vendaDTO);
        venda = vendaRepository.save(venda);
        return vendaMapper.toDto(venda);
    }

    /**
     * Get all the vendas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<VendaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Vendas");
        return vendaRepository.findAll(pageable)
            .map(vendaMapper::toDto);
    }

    /**
     * Get one venda by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VendaDTO> findOne(Long id) {
        log.debug("Request to get Venda : {}", id);
        return vendaRepository.findById(id)
            .map(vendaMapper::toDto);
    }

    /**
     * Delete the venda by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Venda : {}", id);
        vendaRepository.deleteById(id);
    }
}
