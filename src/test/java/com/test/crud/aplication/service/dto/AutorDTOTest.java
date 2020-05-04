package com.test.crud.aplication.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.test.crud.aplication.web.rest.TestUtil;

public class AutorDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AutorDTO.class);
        AutorDTO autorDTO1 = new AutorDTO();
        autorDTO1.setId(1L);
        AutorDTO autorDTO2 = new AutorDTO();
        assertThat(autorDTO1).isNotEqualTo(autorDTO2);
        autorDTO2.setId(autorDTO1.getId());
        assertThat(autorDTO1).isEqualTo(autorDTO2);
        autorDTO2.setId(2L);
        assertThat(autorDTO1).isNotEqualTo(autorDTO2);
        autorDTO1.setId(null);
        assertThat(autorDTO1).isNotEqualTo(autorDTO2);
    }
}
