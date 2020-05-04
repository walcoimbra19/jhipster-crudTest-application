package com.test.crud.aplication.service.mapper;


import com.test.crud.aplication.domain.*;
import com.test.crud.aplication.service.dto.VendaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Venda} and its DTO {@link VendaDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClienteMapper.class})
public interface VendaMapper extends EntityMapper<VendaDTO, Venda> {

    @Mapping(source = "cliente.id", target = "clienteId")
    VendaDTO toDto(Venda venda);

    @Mapping(source = "clienteId", target = "cliente")
    Venda toEntity(VendaDTO vendaDTO);

    default Venda fromId(Long id) {
        if (id == null) {
            return null;
        }
        Venda venda = new Venda();
        venda.setId(id);
        return venda;
    }
}
