package repository;

import model.TradeAccount;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TradeAccountRepository<T extends TradeAccount> {
    private final Map<String, T> datastore = new HashMap<>();

    public void createTradeAccount(T account) {
        this.datastore.put(account.getId(), (T) account.clone());
    }

    public T retrieveTradeAccount(String id) {
        return this.datastore.get(id) == null ? null : (T) this.datastore.get(id).clone();
    }

    public void updateTradeAccount(T account) {
        this.datastore.put(account.getId(), (T) account.clone());
    }

    public void deleteTradeAccount(String id) {
        this.datastore.remove(id);
    }
}