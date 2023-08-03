import java.text.DecimalFormat;
import java.util.Scanner;

public class CurrencyConverter {
    private final String[] currencies = {"USD", "EUR", "JPY", "GBP", "CAD", "AUD", "CHF", "CNY", "INR"};
    private final double[] exchangeRates = {1.00, 0.84, 109.65, 0.72, 1.27, 1.30, 0.92, 6.47, 87.14};
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        converter.startConversion();
    }

    public void startConversion() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Currency Converter CLI!");
        System.out.println("Supported currencies: USD, EUR, JPY, GBP, CAD, AUD, CHF, CNY, INR");

        System.out.print("Enter the source currency: ");
        String fromCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the target currency: ");
        String toCurrency = scanner.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = scanner.nextDouble();

        double result = convertCurrency(amount, fromCurrency, toCurrency);
        System.out.println(decimalFormat.format(amount) + " " + fromCurrency + " is equal to " +
                decimalFormat.format(result) + " " + toCurrency);

        scanner.close();
    }

    public double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        int fromIndex = getIndex(fromCurrency);
        int toIndex = getIndex(toCurrency);

        if (fromIndex == -1 || toIndex == -1) {
            throw new IllegalArgumentException("Invalid currency input.");
        }

        double exchangeRate = exchangeRates[toIndex] / exchangeRates[fromIndex];
        return amount * exchangeRate;
    }

    private int getIndex(String currency) {
        for (int i = 0; i < currencies.length; i++) {
            if (currency.equals(currencies[i])) {
                return i;
            }
        }
        return -1;
    }
}
