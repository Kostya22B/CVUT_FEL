package cz.cvut.fel.omo.homeworks.common.client;

import java.util.UUID;

public class ModernPaymentClient {
    public String execute(String transaction) {
        System.out.printf("Executing transaction: %s %n", transaction);
        // This only simulates the client
        return String.valueOf(UUID.randomUUID());
    }
}
