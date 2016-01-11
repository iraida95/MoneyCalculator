package application;

import control.Command;
import control.ExchangeCommand;
import control.ClearCommand;
import view.ExchangeDisplay;
import view.ExchangeDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Application extends JFrame {

    private final Map<String, Command> commands = new HashMap<>();
    private ExchangeDialog exchangeDialog;
    private ExchangeDisplay exchangeDisplay;

    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application() {
        this.deployComponents();
        this.createCommands();
    }

    private void deployComponents() {
        this.setTitle("Money Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 145));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(exchangePanel(), BorderLayout.CENTER);
        this.getContentPane().add(toolbar(), BorderLayout.SOUTH);
    }

    private void createCommands() {
        this.commands.put("exchange", new ExchangeCommand(this.exchangeDialog, this.exchangeDisplay, new SQLiteExchangeRateReader()));
        this.commands.put("clear", new ClearCommand(this.exchangeDisplay));
    }

    private JPanel exchangePanel() {
        ExchangePanel panel = new ExchangePanel();
        this.exchangeDialog = panel;
        this.exchangeDisplay = panel;
        return panel;
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(calculateButton());
        panel.add(clearButton());
        return panel;
    }

    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(doCommand("exchange"));
        return button;
    }

    private JButton clearButton() {
        JButton button = new JButton("Clear");
        button.addActionListener(doCommand("clear"));
        return button;
    }

    private ActionListener doCommand(String operation) {
        return (ActionEvent event) -> Application.this.commands.get(operation).execute();
    }

}
