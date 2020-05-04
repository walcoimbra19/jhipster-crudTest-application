package com.test.crud.aplication.web.rest;

import com.test.crud.aplication.JhipsterCrudTestApplicationApp;
import com.test.crud.aplication.domain.Livro;
import com.test.crud.aplication.repository.LivroRepository;
import com.test.crud.aplication.service.LivroService;
import com.test.crud.aplication.service.dto.LivroDTO;
import com.test.crud.aplication.service.mapper.LivroMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link LivroResource} REST controller.
 */
@SpringBootTest(classes = JhipsterCrudTestApplicationApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class LivroResourceIT {

    private static final String DEFAULT_TITULO = "AAAAAAAAAA";
    private static final String UPDATED_TITULO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PRECO = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRECO = new BigDecimal(2);

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroMapper livroMapper;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLivroMockMvc;

    private Livro livro;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Livro createEntity(EntityManager em) {
        Livro livro = new Livro()
            .titulo(DEFAULT_TITULO)
            .descricao(DEFAULT_DESCRICAO)
            .preco(DEFAULT_PRECO);
        return livro;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Livro createUpdatedEntity(EntityManager em) {
        Livro livro = new Livro()
            .titulo(UPDATED_TITULO)
            .descricao(UPDATED_DESCRICAO)
            .preco(UPDATED_PRECO);
        return livro;
    }

    @BeforeEach
    public void initTest() {
        livro = createEntity(em);
    }

    @Test
    @Transactional
    public void createLivro() throws Exception {
        int databaseSizeBeforeCreate = livroRepository.findAll().size();

        // Create the Livro
        LivroDTO livroDTO = livroMapper.toDto(livro);
        restLivroMockMvc.perform(post("/api/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livroDTO)))
            .andExpect(status().isCreated());

        // Validate the Livro in the database
        List<Livro> livroList = livroRepository.findAll();
        assertThat(livroList).hasSize(databaseSizeBeforeCreate + 1);
        Livro testLivro = livroList.get(livroList.size() - 1);
        assertThat(testLivro.getTitulo()).isEqualTo(DEFAULT_TITULO);
        assertThat(testLivro.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
        assertThat(testLivro.getPreco()).isEqualTo(DEFAULT_PRECO);
    }

    @Test
    @Transactional
    public void createLivroWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = livroRepository.findAll().size();

        // Create the Livro with an existing ID
        livro.setId(1L);
        LivroDTO livroDTO = livroMapper.toDto(livro);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLivroMockMvc.perform(post("/api/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Livro in the database
        List<Livro> livroList = livroRepository.findAll();
        assertThat(livroList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLivros() throws Exception {
        // Initialize the database
        livroRepository.saveAndFlush(livro);

        // Get all the livroList
        restLivroMockMvc.perform(get("/api/livros?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(livro.getId().intValue())))
            .andExpect(jsonPath("$.[*].titulo").value(hasItem(DEFAULT_TITULO)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)))
            .andExpect(jsonPath("$.[*].preco").value(hasItem(DEFAULT_PRECO.intValue())));
    }
    
    @Test
    @Transactional
    public void getLivro() throws Exception {
        // Initialize the database
        livroRepository.saveAndFlush(livro);

        // Get the livro
        restLivroMockMvc.perform(get("/api/livros/{id}", livro.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(livro.getId().intValue()))
            .andExpect(jsonPath("$.titulo").value(DEFAULT_TITULO))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO))
            .andExpect(jsonPath("$.preco").value(DEFAULT_PRECO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLivro() throws Exception {
        // Get the livro
        restLivroMockMvc.perform(get("/api/livros/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLivro() throws Exception {
        // Initialize the database
        livroRepository.saveAndFlush(livro);

        int databaseSizeBeforeUpdate = livroRepository.findAll().size();

        // Update the livro
        Livro updatedLivro = livroRepository.findById(livro.getId()).get();
        // Disconnect from session so that the updates on updatedLivro are not directly saved in db
        em.detach(updatedLivro);
        updatedLivro
            .titulo(UPDATED_TITULO)
            .descricao(UPDATED_DESCRICAO)
            .preco(UPDATED_PRECO);
        LivroDTO livroDTO = livroMapper.toDto(updatedLivro);

        restLivroMockMvc.perform(put("/api/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livroDTO)))
            .andExpect(status().isOk());

        // Validate the Livro in the database
        List<Livro> livroList = livroRepository.findAll();
        assertThat(livroList).hasSize(databaseSizeBeforeUpdate);
        Livro testLivro = livroList.get(livroList.size() - 1);
        assertThat(testLivro.getTitulo()).isEqualTo(UPDATED_TITULO);
        assertThat(testLivro.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
        assertThat(testLivro.getPreco()).isEqualTo(UPDATED_PRECO);
    }

    @Test
    @Transactional
    public void updateNonExistingLivro() throws Exception {
        int databaseSizeBeforeUpdate = livroRepository.findAll().size();

        // Create the Livro
        LivroDTO livroDTO = livroMapper.toDto(livro);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLivroMockMvc.perform(put("/api/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(livroDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Livro in the database
        List<Livro> livroList = livroRepository.findAll();
        assertThat(livroList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLivro() throws Exception {
        // Initialize the database
        livroRepository.saveAndFlush(livro);

        int databaseSizeBeforeDelete = livroRepository.findAll().size();

        // Delete the livro
        restLivroMockMvc.perform(delete("/api/livros/{id}", livro.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Livro> livroList = livroRepository.findAll();
        assertThat(livroList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
