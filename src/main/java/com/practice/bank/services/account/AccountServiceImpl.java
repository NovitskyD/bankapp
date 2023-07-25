package com.practice.bank.services.account;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.dto.ClientDto;
import com.practice.bank.entity.AccountEntity;
import com.practice.bank.entity.ClientEntity;
import com.practice.bank.helpers.AccountNumberGenerator;
import com.practice.bank.helpers.NumberGenerator;
import com.practice.bank.helpers.NumberGeneratorFactory;
import com.practice.bank.mapper.DtoToEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.AccountRepository;
import com.practice.bank.repository.ClientRepository;
import com.practice.bank.services.client.ClientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;
    private final ClientService clientService;
    private final NumberGeneratorFactory numberGeneratorFactory;

    @Override
    public List<AccountDto> getAccountsByClientId(String clientId){
        List<AccountEntity> accountEntities = accountRepository.findByClientId(UUID.fromString(clientId));
        accountEntities.stream()
        .map(AccountEntity::getAccountNumber)
                .forEach(System.out::println);
        return EntityToDto.accountsEntitiesToDtoList(accountEntities);
    }
    @Override
    public AccountDto getDataById(String id) {
        AccountEntity accountEntity = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Account was not found"));

        return EntityToDto.accountsEntityToDto(accountEntity);
    }

    @Override
    public AccountEntity getAccountEntityById(String id) {
        return accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Account was not found"));
    }

    @Override
    public void insertData(AccountDto element) {
        UUID clientId = UUID.fromString(element.getClient().getId());
        ClientEntity clientEntity = clientRepository.findById(clientId).
                orElseThrow(() -> new EntityNotFoundException("Client was not found"));
        AccountEntity accountEntity = DtoToEntity.accountsDtoToEntity(element);
        accountEntity.setClient(clientEntity);

        clientEntity.getAccounts().add(accountEntity);

        clientRepository.save(clientEntity);
        logger.info("insertData: {}", accountEntity.getAccountNumber());
    }

    @Override
    public void updateData(AccountDto element) {
        return;
    }

    @Override
    public AccountDto createAccountDtoForForm(String clientId){
        ClientDto clientDto = clientService.getDataById(clientId);

        return AccountDto.builder()
                .client(clientDto)
                .accountNumber(generateAccountNumber())
                .build();
    }

    private String generateAccountNumber(){
        NumberGenerator accountNumberGenerator = numberGeneratorFactory.getGenerator(AccountNumberGenerator.class);
        return accountNumberGenerator.generateNumber();
    }
}
