package com.practice.bank.controllers;

import com.practice.bank.dto.ClientDto;
import com.practice.bank.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public final class ClientsController {

    private final ClientService clientService;

    @GetMapping("/all")
    public String getAllClients(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        return "clients/clients_list";
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable("id") String id, Model model) {
        ClientDto clientDto = clientService.getDataById(id);
        model.addAttribute("client", clientDto);
        return "clients/client_display";
    }

    @GetMapping("/create")
    public String showCreateClientForm(@ModelAttribute("client") ClientDto clientDto) {
        return "clients/add_client_form";
    }

    @PostMapping()
    public String createClient(@ModelAttribute("client") ClientDto clientDto) {
        clientService.insertData(clientDto);
        return "redirect:/clients/all";
    }

    @GetMapping("/{id}/edit")
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
