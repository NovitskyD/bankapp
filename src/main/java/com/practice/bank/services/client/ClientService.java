package com.practice.bank.services.client;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.services.BankService;

import java.security.Principal;
import java.util.List;

public interface ClientService extends BankService<ClientDto> {
    List<ClientDto> getAllClients();
    boolean createClient(ClientDto clientDto);
    ClientDto getDataByPrincipal(Principal principal);
}
