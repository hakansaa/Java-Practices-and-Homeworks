package semester1.practice10;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Тестування поліморфної системи платежів ===");

        // Поліморфний масив (Upcasting)
        PaymentInstrument[] payments = {
                new BankCardInstrument("Олександр"),
                new CryptoTokenInstrument("Ірина", 120.0),
                new IbanDirectInstrument("Максим")
        };

        double baseAmount = 60000.0;

        // Єдиний цикл обробляє різні типи платежів завдяки динамічному поліморфізму
        for (PaymentInstrument p : payments) {
            System.out.printf("Платник: %s | Метод: %s | Загалом до сплати: %.2f грн\n",
                    p.getPayerName(), p.getClass().getSimpleName(), p.processPayment(baseAmount));
        }
    }
}