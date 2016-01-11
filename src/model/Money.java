package model;

public class Money {

    private final double amount;
    private final Currency currency;

    public Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

}
