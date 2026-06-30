import model.CashAccount;
import model.MarginAccount;
import repository.TradeAccountRepository;
import service.CashAccountService;
import service.MarginAccountService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    static Path[] paths = new Path[] {Paths.get("data/accounts.txt"), Paths.get("data/transactions.txt")};

    static TradeAccountRepository<CashAccount> cashRepo = new TradeAccountRepository<>();
    static TradeAccountRepository<MarginAccount> marginRepo = new TradeAccountRepository<>();

    static CashAccountService cashAccountService = new CashAccountService(cashRepo);
    static MarginAccountService marginAccountService = new MarginAccountService(marginRepo);

    public static void main(String[] args) {
        try {
            loadTradeAccounts();
            applyTransactions();
            finalTest();
        } catch (IOException exception) {
            System.out.println("Error reading files: " + exception.getMessage());
        }
    }

    public static void loadTradeAccounts() throws IOException {
        Files.lines(paths[0])
                .map(line -> line.split(","))
                .forEach(parts -> {
                    String type = parts[0];
                    String id = parts[1];
                    BigDecimal balance = new BigDecimal(parts[2]);

                    if ("CASH".equalsIgnoreCase(type)) {
                        cashAccountService.createTradeAccount(new CashAccount(id, balance));
                    } else if ("MARGIN".equalsIgnoreCase(type)) {
                        marginAccountService.createTradeAccount(new MarginAccount(id, balance));
                    }
                });
    }

    public static void applyTransactions() throws IOException {
        Files.lines(paths[1])
                .map(line -> line.split(","))
                .forEach(parts -> {
                    String type = parts[0];
                    String id = parts[1];
                    String action = parts[2];
                    BigDecimal amount = new BigDecimal(parts[3]);

                    if ("CASH".equalsIgnoreCase(type)) {
                        if ("DEPOSIT".equalsIgnoreCase(action)) cashAccountService.deposit(id, amount);
                        else if ("WITHDRAW".equalsIgnoreCase(action)) cashAccountService.withdraw(id, amount);
                    } else if ("MARGIN".equalsIgnoreCase(type)) {
                        if ("DEPOSIT".equalsIgnoreCase(action)) marginAccountService.deposit(id, amount);
                        else if ("WITHDRAW".equalsIgnoreCase(action)) marginAccountService.withdraw(id, amount);
                    }
                });
    }

    public static void finalTest() {
        System.out.println("Account A1234B Cash Balance: " + cashAccountService.retrieveTradeAccount("A1234B").getCashBalance());
        System.out.println("Account E3456F Cash Balance: " + cashAccountService.retrieveTradeAccount("E3456F").getCashBalance());
        System.out.println("Account I5678J Cash Balance: " + cashAccountService.retrieveTradeAccount("I5678J").getCashBalance());
        System.out.println("Account C2345D Margin: " + marginAccountService.retrieveTradeAccount("C2345D").getMargin());
        System.out.println("Account G4567H Margin: " + marginAccountService.retrieveTradeAccount("G4567H").getMargin());
    }
}