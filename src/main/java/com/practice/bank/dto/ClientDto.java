package com.practice.bank.dto;

import com.practice.bank.enums.Role;
import com.practice.bank.enums.Status;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {
    private String id;
    @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private LocalDate birthDate;
    @NonNull private String address;
    @NonNull private String phone;
    @Email(message = "Email should be valid")
    @NonNull private String email;
    @NonNull private String password;
    private Role role;
    private Status status;
    private List<AccountDto> accounts;
}
