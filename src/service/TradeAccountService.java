package service;

import java.math.BigDecimal;

public interface TradeAccountService {
    void deposit(String id, BigDecimal amount);
    void withdraw(String id, BigDecimal amount);
}