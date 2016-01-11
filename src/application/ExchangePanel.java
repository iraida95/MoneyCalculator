package application;

import view.ExchangeDisplay;
import view.ExchangeDialog;
import model.Currency;
import model.Money;
import javax.swing.*;

public class ExchangePanel extends JPanel implements ExchangeDialog, ExchangeDisplay {

    private final Currency[] currencies = new SQLiteCurrencySetLoader().load().toArray();
    private final JComboBox<Currency> from = new JComboBox<>(currencies);
    private final JComboBox<Currency> to = new JComboBox<>(currencies);
    private final JTextField input = new JTextField("1,0");

    public ExchangePanel() {
        this.deployComponents();
    }

    @Override
    public void show(Money output) {
        if (output == null) {
            this.input.setText("1,0");
            this.input.setEditable(true);
        } else {
            this.input.setText(String.format("%.2f ", getInputAmount()) + getCurrency(this.from).getCode() + " = "
                    + String.format("%.5f ", output.getAmount()) + output.getCurrency().getCode());
            this.input.setEditable(false);
        }
    }

    @Override
    public Money getInputMoney() {
        return new Money(getInputAmount(), getCurrency(this.from));
    }

    @Override
    public Currency getOutputCurrency() {
        return getCurrency(this.to);
    }

    private void deployComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.input);
        this.add(this.from);
        this.add(this.to);
    }

    private double getInputAmount() {
        return Double.parseDouble(this.input.getText().replaceAll(",", "."));
    }

    private Currency getCurrency(JComboBox<Currency> comboBox) {
        return (Currency) comboBox.getSelectedItem();
    }

}
