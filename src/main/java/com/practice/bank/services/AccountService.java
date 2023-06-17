package com.practice.bank.services;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.entity.AccountEntity;
import com.practice.bank.entity.ClientEntity;
import com.practice.bank.mapper.DtoToEntity;
import com.practice.bank.mapper.EntityToDto;
import com.practice.bank.repository.AccountRepository;
import com.practice.bank.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements BankService<AccountDto> {

    private final AccountRepository accountRepository;
    private final ClientRepository clientRepository;

    public List<AccountDto> getAccountsByClientId(String clientId){
        List<AccountEntity> accountEntities = accountRepository.findByClientId(UUID.fromString(clientId));
        accountEntities.stream()
        .map(AccountEntity::getAccountNumber)
                .forEach(System.out::println);
        return EntityToDto.accountsEntitiesToDtoList(accountRepository.findByClientId(UUID.fromString(clientId)));
    }
    @Override
    public AccountDto getDataById(String id) {
        AccountEntity accountEntity = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new EntityNotFoundException("Account was not found"));

        return EntityToDto.accountsEntityToDto(accountEntity);
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
        accountRepository.save(accountEntity);
        System.out.println("insertData: " + accountEntity.getAccountNumber());
    }

    //        AccountEntity accountEntity = AccountEntity.builder()
//                .client(clientsDtoToEntity(element.getClient()))
//                .accountNumber(element.getAccountNumber())
//                .accountType(element.getAccountType())
//                .balance(element.getBalance())
//                        .build();

    @Override
    public void updateData(AccountDto element) {
        return;
    }
}
