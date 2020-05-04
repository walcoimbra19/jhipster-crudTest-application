package com.test.crud.aplication.service.mapper;


import com.test.crud.aplication.domain.*;
import com.test.crud.aplication.service.dto.AutorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Autor} and its DTO {@link AutorDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AutorMapper extends EntityMapper<AutorDTO, Autor> {


    @Mapping(target = "livros", ignore = true)
    @Mapping(target = "removeLivro", ignore = true)
    Autor toEntity(AutorDTO autorDTO);

    default Autor fromId(Long id) {
        if (id == null) {
            return null;
        }
        Autor autor = new Autor();
        autor.setId(id);
        return autor;
    }
}
