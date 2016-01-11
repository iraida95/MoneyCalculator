package view;

import model.Currency;
import model.ExchangeRate;

public interface ExchangeRateReader {

    public ExchangeRate get(Currency from, Currency to);
    
}
