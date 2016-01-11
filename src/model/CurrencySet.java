package model;

import java.util.ArrayList;
import java.util.List;

public class CurrencySet {

    private final List<Currency> list = new ArrayList<>();

    public void add(Currency currency) {
        this.list.add(currency);
    }

    public Currency[] toArray() {
        return this.list.toArray(new Currency[this.list.size()]);
    }

}
