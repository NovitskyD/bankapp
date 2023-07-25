package com.practice.bank.controllers;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public final class ClientsController {

    private final ClientService clientService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('all-client-profile:read')")
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/clients_list";
    }

    @GetMapping("/profile")
    public String getClientById(Principal principal, Model model) {
        ClientDto clientDto = clientService.getDataByPrincipal(principal);
        model.addAttribute("client", clientDto);
        return "clients/client_display";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAuthority('client-profile:write')")
    public String edit(@PathVariable("id") String id,
                       Model model) {

        model.addAttribute("client", clientService.getDataById(id));
        return "clients/edit";
    }

    @PostMapping("/{id}/update")
    public String updateClient(@PathVariable String id, @ModelAttribute("client") @Valid ClientDto clientDto,
                               BindingResult result){
        if(result.hasErrors()){
            return "clients/edit";
        }
        clientService.updateData(clientDto);
        return "redirect:/clients/" + id;
    }
}
