package control;

import view.ExchangeDialog;
import view.ExchangeDisplay;
import view.ExchangeRateReader;
import view.MoneyExchanger;
import model.Money;
import model.ExchangeRate;

public class ExchangeCommand implements Command {

    private final ExchangeDialog exchangeDialog;
    private final ExchangeDisplay exchangeDisplay;
    private final ExchangeRateReader exchangeRateReader;

    public ExchangeCommand(ExchangeDialog exchangeDialog, ExchangeDisplay exchangeDisplay, ExchangeRateReader exchangeRateLoader) {
        this.exchangeDialog = exchangeDialog;
        this.exchangeDisplay = exchangeDisplay;
        this.exchangeRateReader = exchangeRateLoader;
    }

    @Override
    public void execute() {
        Money money = this.exchangeDialog.getInputMoney();
        ExchangeRate exchangeRate = this.exchangeRateReader.get(money.getCurrency(), this.exchangeDialog.getOutputCurrency());
        this.exchangeDisplay.show(MoneyExchanger.exchange(money, exchangeRate));
    }

}
