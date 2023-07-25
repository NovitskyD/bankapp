package com.practice.bank.controllers;

import com.practice.bank.dto.AccountDto;
import com.practice.bank.dto.CardDto;
import com.practice.bank.dto.TransactionDto;
import com.practice.bank.dto.TransactionForm;
import com.practice.bank.services.account.AccountService;
import com.practice.bank.services.card.CardService;
import com.practice.bank.services.client.ClientService;
import com.practice.bank.services.transaction.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/accounts/cards/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final ClientService clientService;
    private final AccountService accountService;
    private final CardService cardService;
    private final TransactionService transactionService;

    @GetMapping("/{id}")
    public String getAccountsByClientId(@PathVariable("id") String accountId, Model model){
        AccountDto accountDto = accountService.getDataById(accountId);
        List<CardDto> cardSender = cardService.getCardsByAccountId(accountId);
        List<CardDto> cardRecipient = cardService.getAllCardsForClient(accountDto.getClient().getId());
        Map<String, List<CardDto>> cardRecipientMap = cardRecipient.stream()
                .collect(Collectors.groupingBy(card -> card.getAccount().getAccountType()));

        model.addAttribute("cardSender", cardSender);
        model.addAttribute("cardRecipients", cardRecipientMap);
        model.addAttribute("transactionForm", new TransactionForm());

        return "transactions/transaction_form";
    }

    @PostMapping("/")
    public String processTransactionForm(@ModelAttribute("transactionForm") @Valid TransactionForm transactionForm,
                                         BindingResult result){
        if(result.hasErrors()){
            return "transactions/transaction_form";
        }

        TransactionDto transactionDto = transactionService.processTransaction(transactionForm);
        transactionService.insertData(transactionDto);

        return "messages/success";
    }
}
