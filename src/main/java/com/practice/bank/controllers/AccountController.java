package com.practice.bank.controllers;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.dto.ClientDto;
import com.practice.bank.services.AccountService;
import com.practice.bank.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts")
@RequiredArgsConstructor
public final class AccountController {
    private final AccountService accountService;
    private final ClientService clientService;

    @GetMapping("/{id}")
    public String getAccountsByIdClient(@PathVariable("id") String id, Model model){
        ClientDto clientDto = clientService.getDataById(id);
        List<AccountDto> accounts = accountService.getAccountsByClientId(id);
        Set<String> accountNumbers = new HashSet<>();
        accounts.removeIf(account -> !accountNumbers.add(account.getAccountNumber()));
        Map<String, List<AccountDto>> accountsByType = accounts.stream()
                .collect(Collectors.groupingBy(AccountDto::getAccountType));

        model.addAttribute("client", clientDto);
        model.addAttribute("accountsByType", accountsByType);

        return "accounts/accounts_list";
    }

    @GetMapping("{id}/create")
    public String showCreateAccountForm(@PathVariable("id") String id, Model model){
        ClientDto clientDto = clientService.getDataById(id);
        String accountNumber = UUID.randomUUID().toString();
        BigDecimal balance = BigDecimal.ZERO;
        AccountDto accountDto = AccountDto.builder()
                .client(clientDto)
                .accountNumber(accountNumber)
                .balance(balance)
                .build();
        model.addAttribute("account", accountDto);
        return "accounts/add_account_form";
    }

    @PostMapping()
    public String createAccount(@ModelAttribute("account") @Valid AccountDto accountDto,
                                BindingResult result){
        if(result.hasErrors()){
            return "accounts/add_account_form";
        }
        System.out.println("client: " + accountDto.getClient());
        System.out.println("account: " + accountDto);
        accountService.insertData(accountDto);
        return "redirect:/clients/all";
    }
}
