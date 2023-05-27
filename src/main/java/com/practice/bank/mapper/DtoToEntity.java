package com.practice.bank.mapper;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.entity.ClientEntity;
import org.springframework.util.StringUtils;

import java.time.ZoneId;
import java.util.UUID;

public class DtoToEntity {
    public static ClientEntity clientsDtoToEntity(ClientDto clientDto){
        return ClientEntity.builder()
                .firstName(clientDto.getFirstName())
                .lastName(clientDto.getLastName())
                .birthDate(clientDto.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant())
                .address(clientDto.getAddress())
                .phone(clientDto.getPhone())
                .email(clientDto.getEmail())
                .password(clientDto.getPassword())
                .build();
    }
}
