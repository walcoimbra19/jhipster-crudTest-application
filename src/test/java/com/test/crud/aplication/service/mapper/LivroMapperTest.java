package com.test.crud.aplication.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LivroMapperTest {

    private LivroMapper livroMapper;

    @BeforeEach
    public void setUp() {
        livroMapper = new LivroMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(livroMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(livroMapper.fromId(null)).isNull();
    }
}
