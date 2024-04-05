package cz.cvut.fel.omo.homeworks.refactor.transaction;

import cz.cvut.fel.omo.homeworks.common.client.ModernPaymentClient;

import java.util.Optional;

public class ModernTransactionSystem extends AbstractTransactionSystem {
    private final ModernPaymentClient modernPaymentClient = new ModernPaymentClient();
    private final String surl;
    private final String furl;
    public ModernTransactionSystem(Long totalAmount, String currencyCode, String surl, String furl) {
        super(totalAmount, currencyCode);
        this.surl = surl;
        this.furl = furl;
    }

    @Override
    protected Optional<String> buildTransaction() {
        if (totalAmount == null && currencyCode == null && surl == null && furl == null) {
            return Optional.empty();
        }
        String result = totalAmount + currencyCode + surl + furl;
        return Optional.of(result);
    }

    @Override
    protected Optional<String> execute(String input) {
        Optional<String> modernTransaction = buildTransaction();
        return modernTransaction.map(modernPaymentClient::execute);
    }
}
