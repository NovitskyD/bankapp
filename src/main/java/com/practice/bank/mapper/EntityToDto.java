package com.practice.bank.mapper;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.entity.ClientEntity;

import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

public class EntityToDto {
    public static ClientDto clientsEntityToDto(ClientEntity clientEntity){
        return ClientDto.builder()
                .id(clientEntity.getId().toString())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .birthDate(clientEntity.getBirthDate().atZone(ZoneId.systemDefault()).toLocalDate())
                .address(clientEntity.getAddress())
                .phone(clientEntity.getPhone())
                .email(clientEntity.getEmail())
                .password(clientEntity.getPassword())
                .build();
    }

    public static List<ClientDto> mapToDtoList(List<ClientEntity> clientEntities){
        return clientEntities.stream()
                .map(EntityToDto::clientsEntityToDto)
                .collect(Collectors.toList());
    }
}
