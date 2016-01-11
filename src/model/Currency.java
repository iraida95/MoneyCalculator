package model;

public class Currency {

    private final String code;
    private final String name;
    private final String symbol;

    public Currency(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public String getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.code + ") ";
    }

}
