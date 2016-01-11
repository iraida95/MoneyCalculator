package control;

import view.ExchangeDisplay;

public class ClearCommand implements Command {

    private final ExchangeDisplay exchangeDisplay;

    public ClearCommand(ExchangeDisplay exchangeDisplay) {
        this.exchangeDisplay = exchangeDisplay;
    }

    @Override
    public void execute() {
        this.exchangeDisplay.show(null);
    }

}
