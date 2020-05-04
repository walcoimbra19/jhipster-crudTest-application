package com.test.crud.aplication.service.mapper;


import com.test.crud.aplication.domain.*;
import com.test.crud.aplication.service.dto.LivroDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Livro} and its DTO {@link LivroDTO}.
 */
@Mapper(componentModel = "spring", uses = {AutorMapper.class})
public interface LivroMapper extends EntityMapper<LivroDTO, Livro> {

    @Mapping(source = "autor.id", target = "autorId")
    LivroDTO toDto(Livro livro);

    @Mapping(source = "autorId", target = "autor")
    Livro toEntity(LivroDTO livroDTO);

    default Livro fromId(Long id) {
        if (id == null) {
            return null;
        }
        Livro livro = new Livro();
        livro.setId(id);
        return livro;
    }
}
