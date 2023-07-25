package com.practice.bank.services.client;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.entity.ClientEntity;
import com.practice.bank.mapper.DtoToEntity;
import com.practice.bank.mapper.EntityMapper;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.AccountRepository;
import com.practice.bank.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final EntityMapper clientEntityMapper;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<ClientDto> getAllClients(){
        List<ClientEntity> clientEntities = clientRepository.findAll();
        log.info("Retrieved all clients");
        return EntityToDto.clientsEntitiesToDtoList(clientEntities);
    }

    @Override
    public boolean createClient(ClientDto clientDto) {
        String clientEmail = clientDto.getEmail();
        Optional<ClientEntity> clientOptional = clientRepository.findByEmail(clientEmail);
        if (clientOptional.isPresent()){
            return false;
        }
        else {
            clientDto.setPassword(passwordEncoder.encode(clientDto.getPassword()));
            log.info("Saving new Client with email: {}", clientEmail);
            insertData(clientDto);
            return true;
        }
    }

    @Override
    public ClientDto getDataByPrincipal(Principal principal) {
        if (principal == null || principal.getName() == null || principal.getName().isEmpty()){
            return null;
        }
        String clientEmail = principal.getName();
        Optional<ClientEntity> clientOptional = clientRepository.findByEmail(clientEmail);
        ClientEntity clientEntity = clientOptional
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        return EntityToDto.clientsEntityToDto(clientEntity);
    }

    @Override
    public ClientDto getDataById(String id) {
        log.info("Retrieved client with id: {}", id);
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
        log.info("Updated client with id: {}", id);
    }
}
