package cz.cvut.fel.omo.homeworks.old;

import cz.cvut.fel.omo.homeworks.refactor.transaction.LegacyTransactionSystem;
import cz.cvut.fel.omo.homeworks.refactor.transaction.ModernTransactionSystem;

public class Application {

    public static void main(String[] args) {
//        TransactionSystem transactionSystem = new TransactionSystem();
//        transactionSystem.setTransactionParams(1000L, "CZK");
//        System.out.println(transactionSystem.executeTransaction());
        LegacyTransactionSystem legacyTransactionSystem = new LegacyTransactionSystem(1000L, "CZK");
        System.out.println(legacyTransactionSystem.executeTransaction());
        ModernTransactionSystem modernTransactionSystem = new ModernTransactionSystem(null, null, null, null);
        System.out.println(modernTransactionSystem.executeTransaction());
    }
}
