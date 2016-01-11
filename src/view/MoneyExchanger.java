package view;

import model.Money;
import model.ExchangeRate;

public class MoneyExchanger {

    public static Money exchange(Money money, ExchangeRate exchangeRate) {
        double amount = money.getAmount() * exchangeRate.getRate();
        return new Money(amount, exchangeRate.getTo());
    }

}
