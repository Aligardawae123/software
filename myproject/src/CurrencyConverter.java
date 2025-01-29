import java.awt.EventQueue;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private JFrame frame;
    private JTextField textFieldAmount;
    private JComboBox<String> comboBoxFrom, comboBoxTo;
    private JLabel lblResult;

    private static final Map<String, Double> exchangeRates = new HashMap<>();
    static {
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.92);
        exchangeRates.put("GBP", 0.78);
        exchangeRates.put("INR", 83.0);
        exchangeRates.put("JPY", 147.5);
        exchangeRates.put("PKR", 280.0);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CurrencyConverter window = new CurrencyConverter();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CurrencyConverter() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Currency Converter");
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblEnterAmount = new JLabel("Enter Amount:");
        lblEnterAmount.setBounds(20, 20, 120, 25);
        frame.getContentPane().add(lblEnterAmount);

        textFieldAmount = new JTextField();
        textFieldAmount.setBounds(150, 20, 150, 25);
        frame.getContentPane().add(textFieldAmount);

        JLabel lblFrom = new JLabel("From:");
        lblFrom.setBounds(20, 60, 50, 25);
        frame.getContentPane().add(lblFrom);

        comboBoxFrom = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        comboBoxFrom.setBounds(80, 60, 100, 25);
        frame.getContentPane().add(comboBoxFrom);

        JLabel lblTo = new JLabel("To:");
        lblTo.setBounds(200, 60, 50, 25);
        frame.getContentPane().add(lblTo);

        comboBoxTo = new JComboBox<>(exchangeRates.keySet().toArray(new String[0]));
        comboBoxTo.setBounds(240, 60, 100, 25);
        frame.getContentPane().add(comboBoxTo);

        JButton btnConvert = new JButton("Convert");
        btnConvert.setBounds(150, 100, 100, 30);
        frame.getContentPane().add(btnConvert);

        lblResult = new JLabel("Converted Amount: ");
        lblResult.setBounds(20, 140, 400, 25);
        frame.getContentPane().add(lblResult);

        btnConvert.addActionListener(e -> performConversion());
    }

    private void performConversion() {
        try {
            double amount = Double.parseDouble(textFieldAmount.getText());
            String fromCurrency = (String) comboBoxFrom.getSelectedItem();
            String toCurrency = (String) comboBoxTo.getSelectedItem();
            
            double fromRate = exchangeRates.get(fromCurrency);
            double toRate = exchangeRates.get(toCurrency);
            double convertedAmount = (amount / fromRate) * toRate;
            
            lblResult.setText("Converted Amount: " + String.format("%.2f", convertedAmount) + " " + toCurrency);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}