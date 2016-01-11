package application;

import view.CurrencySetLoader;
import model.Currency;
import model.CurrencySet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javaslang.control.Try;

public class SQLiteCurrencySetLoader implements CurrencySetLoader {

    @Override
    public CurrencySet load() {
        return Try.of(() -> DriverManager.getConnection("jdbc:sqlite:currencies.db")) 
                .mapTry((Connection connection) -> connection.createStatement())
                .mapTry((Statement statement) -> getCurrencySet(statement))
                .get();
    }

    private CurrencySet getCurrencySet(Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CURRENCIES");
        CurrencySet currencySet = new CurrencySet();
        while (resultSet.next()) currencySet.add(new Currency(resultSet.getString("code"), resultSet.getString("name"), resultSet.getString("symbol")));
        return currencySet;
    }

}
