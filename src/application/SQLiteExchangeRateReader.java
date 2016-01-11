package application;

import view.ExchangeRateReader;
import model.Currency;
import model.ExchangeRate;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javaslang.control.Try;

public class SQLiteExchangeRateReader implements ExchangeRateReader {

    @Override
    public ExchangeRate get (Currency from, Currency to) {
        try {
            ExchangeRate exchangeRate;
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:currencies.db");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * FROM currency_rates where currency_from='" + from.getCode() + "' and currency_to='" + to.getCode() + "'");
            if (resultSet.next()) {
                exchangeRate = new ExchangeRate(from, to, resultSet.getDouble("rate"));
            } else {
                System.out.println("ERROR");
                return null;
            }
            return exchangeRate;

        } catch (Exception e) {
            System.out.println("ERROR");
            return null;
        }
    }


    private ExchangeRate getExchangeRate(Statement statement, Currency from, Currency to) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM EXCHANGE_RATES "
                + "WHERE currency_from='" + from.getCode() + "'"
                + "AND currency_to='" + to.getCode() + "'");
        return new ExchangeRate(from, to, resultSet.getDouble("exchange_rate"));
    }

}
