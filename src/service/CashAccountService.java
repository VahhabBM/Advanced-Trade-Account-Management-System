package service;

import model.CashAccount;
import repository.TradeAccountRepository;
import java.math.BigDecimal;

public class CashAccountService implements TradeAccountService {
    private final TradeAccountRepository<CashAccount> repository;

    public CashAccountService(TradeAccountRepository<CashAccount> repository) {
        this.repository = repository;
    }

    public void createTradeAccount(CashAccount account) {
        repository.createTradeAccount(account);
    }

    public CashAccount retrieveTradeAccount(String id) {
        return repository.retrieveTradeAccount(id);
    }

    public void updateTradeAccount(CashAccount account) {
        repository.updateTradeAccount(account);
    }

    public void deleteTradeAccount(String id) {
        repository.deleteTradeAccount(id);
    }

    @Override
    public void deposit(String id, BigDecimal amount) {
        CashAccount account = repository.retrieveTradeAccount(id);
        if (account != null) {
            account.setCashBalance(account.getCashBalance().add(amount));
            repository.updateTradeAccount(account);
        }
    }

    @Override
    public void withdraw(String id, BigDecimal amount) {
        CashAccount account = repository.retrieveTradeAccount(id);
        if (account != null) {
            account.setCashBalance(account.getCashBalance().subtract(amount));
            repository.updateTradeAccount(account);
        }
    }
}