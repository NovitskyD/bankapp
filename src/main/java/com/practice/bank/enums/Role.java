package com.practice.bank.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.CLIENT_PROFILE_READ, Permission.CLIENT_PROFILE_WRITE,
            Permission.BANK_ACCOUNT_READ,
            Permission.BANK_CARD_READ)),
    ADMIN(Set.of(Permission.CLIENT_PROFILE_READ, Permission.CLIENT_PROFILE_WRITE, Permission.ALL_CLIENT_PROFILE_READ,
            Permission.BANK_ACCOUNT_READ, Permission.BANK_ACCOUNT_WRITE,
            Permission.BANK_CARD_READ, Permission.BANK_CARD_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions(){
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
