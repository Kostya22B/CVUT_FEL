package cz.cvut.fel.omo.homeworks.refactor.transaction;

import java.util.Optional;

public abstract class AbstractTransactionSystem implements TransactionSystem{
    protected Long totalAmount;
    protected String currencyCode;

    protected AbstractTransactionSystem(Long totalAmount, String currencyCode) {
        this.totalAmount = totalAmount;
        this.currencyCode = currencyCode;
    }

    public String executeTransaction() {
        Optional<String> transaction = buildTransaction();
        return transaction.map(s -> execute(s).orElse("Transaction execution failed.")).orElse("Transaction execution failed.");
    }


    protected abstract Optional<String> execute(String input);

    protected abstract Optional<String> buildTransaction();
}
