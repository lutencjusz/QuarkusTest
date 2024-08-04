package org.acme;

import java.util.UUID;

public record Account(UUID id, String name, String accountNumber) {
    /**
     * Konstruktor bezparametrowy klasy Account - żeby działało serializowanie do JSON.
     */
    public Account {
    }
}
