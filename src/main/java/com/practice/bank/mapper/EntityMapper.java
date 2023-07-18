package com.practice.bank.mapper;

import com.practice.bank.entity.CardEntity;
import com.practice.bank.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);

    @Mapping(target = "id", ignore = true)
    void mapClientEntityToExistingClientEntity(@MappingTarget ClientEntity source, ClientEntity target);

    @Mapping(target = "id", ignore = true)
    void mapCardEntityToExistingCardEntity(@MappingTarget CardEntity source, CardEntity target);
}
