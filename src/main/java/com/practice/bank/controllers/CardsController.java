package com.practice.bank.controllers;

import com.practice.bank.dto.*;
import com.practice.bank.services.card.CardService;
import com.practice.bank.services.currency.CurrencyService;
import com.practice.bank.services.limit.LimitService;
import com.practice.bank.services.loan.LoanService;
import com.practice.bank.services.paymentSystem.PaymentSystemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/accounts/cards")
@RequiredArgsConstructor
@Slf4j
public class CardsController {
    private final CardService cardService;
    private final PaymentSystemService paymentSystemService;
    private final LimitService limitService;
    private final CurrencyService currencyService;
    private final LoanService loanService;

    @GetMapping("/{id}/create")
    public String showCreateCardForm(@PathVariable("id") String accountId, Model model){
        CardDto cardDto = cardService.createCardDtoForForm(accountId);
        List<PaymentSystemDto> paymentSystems = paymentSystemService.getAllPaymentSystems();
        List<LimitDto> limits = limitService.getAllLimits();
        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        LoanDto loanDto = loanService.createLoanDtoForForm();

        model.addAttribute("currencies", currencies);
        model.addAttribute("card", cardDto);
        model.addAttribute("paymentSystems", paymentSystems);
        model.addAttribute("limits", limits);
        model.addAttribute("loan", loanDto);
        return "cards/create_card_form";
    }

    @PostMapping()
    public String createCard(@ModelAttribute("card") @Valid CardDto cardDto,
                            @ModelAttribute("loan") @Valid LoanDto loanDto,
                            BindingResult result){
        if(result.hasErrors()){
            return "cards/create_card_form";
        }
        log.info("CardDto: {}", cardDto);
        if("Credit".equals(cardDto.getAccount().getAccountType()) && loanDto != null) {
            cardDto.setLoan(loanDto);
        }
        cardService.insertData(cardDto);
        return "redirect:/accounts/" + cardDto.getAccount().getClient().getId();
    }

    @GetMapping("/top-up")
    public String showTopUpForm(Model model){
        List<CurrencyDto> currencies = currencyService.getAllCurrencies();
        model.addAttribute("topUpDto", new TopUpDto());
        model.addAttribute("currencies", currencies);
        return "cards/top_up_form";
    }

    @PostMapping("/top-up")
    public String processTopUpForm(@ModelAttribute("topUpDto") @Valid TopUpDto topUpDto, BindingResult result){
        if (result.hasErrors()){
            return "cards/top_up_form";
        }
        cardService.topUpBalance(topUpDto);

        return "messages/success";
    }
}
