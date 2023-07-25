package com.practice.bank.controllers;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.dto.ClientDto;
import com.practice.bank.services.account.AccountService;
import com.practice.bank.services.client.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Slf4j
public final class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;

    @GetMapping("/{id}")
    public String getAccountsByIdClient(@PathVariable("id") String id, Model model){
        ClientDto clientDto = clientService.getDataById(id);
        model.addAttribute("client", clientDto);
        List<AccountDto> accounts = accountService.getAccountsByClientId(id);
        Map<String, List<AccountDto>> accountsByType = accounts.stream()
                .collect(Collectors.groupingBy(AccountDto::getAccountType));

        model.addAttribute("accountsByType", accountsByType);

        //logging
        accounts.stream()
                .flatMap(account -> account.getCards().stream())
                .forEach(card -> log.info("Card: {}", card));
        log.info("AccountsByType: {}", accountsByType);

        return "accounts/accounts_list";
    }

    @GetMapping("{id}/create")
    public String showCreateAccountForm(@PathVariable("id") String clientId, Model model){
        AccountDto accountDto = accountService.createAccountDtoForForm(clientId);
        ClientDto clientDto = clientService.getDataById(clientId);

        model.addAttribute("account", accountDto);
        model.addAttribute("client", clientDto);
        return "accounts/add_account_form";
    }

    @PostMapping()
    public String createAccount(@ModelAttribute("account") @Valid AccountDto accountDto,
                                BindingResult result){
        if(result.hasErrors()){
            return "accounts/add_account_form";
        }
        log.info("Client: {}", accountDto.getClient());
        log.info("Account: {}", accountDto);

        accountService.insertData(accountDto);
        return "redirect:/clients/" + accountDto.getClient().getId();
    }
}
