package com.practice.bank.enums;

public enum Permission {
    CLIENT_PROFILE_READ("client-profile:read"),
    CLIENT_PROFILE_WRITE("client-profile:write"),
    ALL_CLIENT_PROFILE_READ("all-client-profile:read"),
    BANK_ACCOUNT_READ("bank-account:read"),
    BANK_ACCOUNT_WRITE("bank-account:write"),
    BANK_CARD_READ("bank-card:read"),
    BANK_CARD_WRITE("bank-card:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
