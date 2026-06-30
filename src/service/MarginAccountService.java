package service;

import model.MarginAccount;
import repository.TradeAccountRepository;
import java.math.BigDecimal;

public class MarginAccountService implements TradeAccountService {
    private final TradeAccountRepository<MarginAccount> repository;

    public MarginAccountService(TradeAccountRepository<MarginAccount> repository) {
        this.repository = repository;
    }

    public void createTradeAccount(MarginAccount account) {
        repository.createTradeAccount(account);
    }

    public MarginAccount retrieveTradeAccount(String id) {
        return repository.retrieveTradeAccount(id);
    }

    public void updateTradeAccount(MarginAccount account) {
        repository.updateTradeAccount(account);
    }

    public void deleteTradeAccount(String id) {
        repository.deleteTradeAccount(id);
    }

    @Override
    public void deposit(String id, BigDecimal amount) {
        MarginAccount account = repository.retrieveTradeAccount(id);
        if (account != null) {
            account.setMargin(account.getMargin().add(amount));
            repository.updateTradeAccount(account);
        }
    }

    @Override
    public void withdraw(String id, BigDecimal amount) {
        MarginAccount account = repository.retrieveTradeAccount(id);
        if (account != null) {
            account.setMargin(account.getMargin().subtract(amount));
            repository.updateTradeAccount(account);
        }
    }
}