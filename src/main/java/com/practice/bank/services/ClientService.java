package com.practice.bank.services;

import com.practice.bank.dto.ClientDto;

import java.security.Principal;
import java.util.List;

public interface ClientService extends BankService<ClientDto>{
    List<ClientDto> getAllClients();
    boolean createClient(ClientDto clientDto);
    ClientDto getDataByPrincipal(Principal principal);
}
