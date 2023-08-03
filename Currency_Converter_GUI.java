import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Currency_Converter_GUI extends JFrame {
    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton;
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR"};
    private double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14};

    public Currency_Converter_GUI() {
        setTitle("Currency Converter");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        amountLabel = new JLabel("Amount:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(amountLabel, gbc);

        amountField = new JTextField(10);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(amountField, gbc);

        fromLabel = new JLabel("From:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fromLabel, gbc);

        fromComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(fromComboBox, gbc);

        toLabel = new JLabel("To:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(toLabel, gbc);

        toComboBox = new JComboBox<>(currencies);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(toComboBox, gbc);

        convertButton = new JButton("Convert");
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(convertButton, gbc);

        resultLabel = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(resultLabel, gbc);

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String fromCurrency = (String) fromComboBox.getSelectedItem();
                    String toCurrency = (String) toComboBox.getSelectedItem();
                    double exchangeRate = exchangeRates[getIndex(toCurrency)] / exchangeRates[getIndex(fromCurrency)];
                    double result = amount * exchangeRate;
                    resultLabel.setText(decimalFormat.format(result) + " " + toCurrency);
                } catch (Exception ex) {
                    resultLabel.setText("Invalid input");
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Currency_Converter_GUI().setVisible(true));
    }
}
