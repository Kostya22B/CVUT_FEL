package cz.cvut.fel.omo.homeworks.refactor.transaction;

import cz.cvut.fel.omo.homeworks.common.client.LegacyPaymentClient;
import cz.cvut.fel.omo.homeworks.common.session.UserSession;
import java.util.Optional;

public class LegacyTransactionSystem extends AbstractTransactionSystem{
    private final UserSession userSession = new UserSession();
    private final LegacyPaymentClient legacyPaymentClient = new LegacyPaymentClient();

    public LegacyTransactionSystem(Long totalAmount, String currencyCode) {
        super(totalAmount, currencyCode);
    }

    @Override
    protected Optional<String> buildTransaction() {
        if (totalAmount == null && currencyCode == null) {
            return Optional.empty();
        }
        String result = totalAmount + currencyCode;
        return Optional.of(result);
    }

    @Override
    protected Optional<String> execute(String input) {
        Optional<String> legacyTransaction = buildTransaction();
        if (legacyTransaction.isPresent()) {
            String senderIP = userSession.getIP();
            return Optional.of(legacyPaymentClient.execute(legacyTransaction.get(), senderIP));
        }
        return Optional.empty();
    }
}