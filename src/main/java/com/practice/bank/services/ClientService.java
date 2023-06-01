package com.practice.bank.services;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.entity.ClientEntity;
import com.practice.bank.mapper.ClientEntityMapper;
import com.practice.bank.mapper.DtoToEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.AccountRepository;
import com.practice.bank.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService implements BankService<ClientDto> {
    private final ClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;
    private final AccountRepository accountRepository;

    public ClientService(ClientRepository clientRepository, ClientEntityMapper clientEntityMapper, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.clientEntityMapper = clientEntityMapper;
        this.accountRepository = accountRepository;
    }

    public List<ClientDto> getAllClients(){
        List<ClientEntity> clientEntities = clientRepository.findAll();
        return EntityToDto.clientsEntitiesToDtoList(clientEntities);
    }

    @Override
    public ClientDto getDataById(String id) {
        return EntityToDto.clientsEntityToDto(clientRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Client was not found")));
    }

    @Override
    public void insertData(ClientDto element) {
        clientRepository.save(DtoToEntity.clientsDtoToEntity(element));
    }

    @Override
    public void updateData(ClientDto element) {
        UUID id = UUID.fromString(element.getId());
        ClientEntity existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client was not found"));


        clientEntityMapper.INSTANCE.mapClientEntityToExistingClientEntity(existingClient,
                DtoToEntity.clientsDtoToEntity(element));

        clientRepository.save(existingClient);
    }
}
