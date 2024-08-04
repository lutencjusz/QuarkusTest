package org.acme;

import java.util.UUID;

public record Account(UUID id, String name, String accountNumber) {
    public Account {}
}
