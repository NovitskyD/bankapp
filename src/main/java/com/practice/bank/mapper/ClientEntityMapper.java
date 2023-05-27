package com.practice.bank.mapper;

import com.practice.bank.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    ClientEntityMapper INSTANCE = Mappers.getMapper(ClientEntityMapper.class);

    @Mapping(target = "id", ignore = true)
    void mapClientEntityToExistingClientEntity(@MappingTarget ClientEntity source, ClientEntity target);
}
