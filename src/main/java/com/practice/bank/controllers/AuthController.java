package com.practice.bank.controllers;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.services.client.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final ClientService clientService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registration(@ModelAttribute("client") ClientDto clientDto) {
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String createClient(@ModelAttribute("client") @Valid ClientDto clientDto,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "auth/registration";
        }
        clientService.createClient(clientDto);
        return "redirect:/auth/login";
    }
}
