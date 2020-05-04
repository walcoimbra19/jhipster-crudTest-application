package com.test.crud.aplication.web.rest;

import com.test.crud.aplication.service.LivroService;
import com.test.crud.aplication.web.rest.errors.BadRequestAlertException;
import com.test.crud.aplication.service.dto.LivroDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.test.crud.aplication.domain.Livro}.
 */
@RestController
@RequestMapping("/api")
public class LivroResource {

    private final Logger log = LoggerFactory.getLogger(LivroResource.class);

    private static final String ENTITY_NAME = "livro";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LivroService livroService;

    public LivroResource(LivroService livroService) {
        this.livroService = livroService;
    }

    /**
     * {@code POST  /livros} : Create a new livro.
     *
     * @param livroDTO the livroDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new livroDTO, or with status {@code 400 (Bad Request)} if the livro has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/livros")
    public ResponseEntity<LivroDTO> createLivro(@RequestBody LivroDTO livroDTO) throws URISyntaxException {
        log.debug("REST request to save Livro : {}", livroDTO);
        if (livroDTO.getId() != null) {
            throw new BadRequestAlertException("A new livro cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LivroDTO result = livroService.save(livroDTO);
        return ResponseEntity.created(new URI("/api/livros/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /livros} : Updates an existing livro.
     *
     * @param livroDTO the livroDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated livroDTO,
     * or with status {@code 400 (Bad Request)} if the livroDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the livroDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/livros")
    public ResponseEntity<LivroDTO> updateLivro(@RequestBody LivroDTO livroDTO) throws URISyntaxException {
        log.debug("REST request to update Livro : {}", livroDTO);
        if (livroDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LivroDTO result = livroService.save(livroDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, livroDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /livros} : get all the livros.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of livros in body.
     */
    @GetMapping("/livros")
    public ResponseEntity<List<LivroDTO>> getAllLivros(Pageable pageable) {
        log.debug("REST request to get a page of Livros");
        Page<LivroDTO> page = livroService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /livros/:id} : get the "id" livro.
     *
     * @param id the id of the livroDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the livroDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/livros/{id}")
    public ResponseEntity<LivroDTO> getLivro(@PathVariable Long id) {
        log.debug("REST request to get Livro : {}", id);
        Optional<LivroDTO> livroDTO = livroService.findOne(id);
        return ResponseUtil.wrapOrNotFound(livroDTO);
    }

    /**
     * {@code DELETE  /livros/:id} : delete the "id" livro.
     *
     * @param id the id of the livroDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/livros/{id}")
    public ResponseEntity<Void> deleteLivro(@PathVariable Long id) {
        log.debug("REST request to delete Livro : {}", id);
        livroService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
