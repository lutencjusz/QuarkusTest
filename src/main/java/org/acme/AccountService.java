package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class AccountService {
    private Set<Account> accounts = new HashSet<>() {{
        add(new Account(UUID.randomUUID(), "Alice", "123-456"));
        add(new Account(UUID.randomUUID(), "Bob", "789-123"));
    }};

    Set<Account> getAllAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    Account getAccount(String name) {
        return accounts.stream()
                .filter(account -> account.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    Account addAccount(Account account) {
        accounts.add(account);
        return account;
    }
}
