package com.test.crud.aplication.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AutorMapperTest {

    private AutorMapper autorMapper;

    @BeforeEach
    public void setUp() {
        autorMapper = new AutorMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(autorMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(autorMapper.fromId(null)).isNull();
    }
}
