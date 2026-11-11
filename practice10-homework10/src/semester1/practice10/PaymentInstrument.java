package semester1.practice10;

public abstract class PaymentInstrument {
    private final String payerName;

    public PaymentInstrument(String payerName) {
        this.payerName = payerName;
    }

    // Абстрактний метод, який кожен нащадок реалізує по-своєму
    public abstract double calculateFee(double amount);

    // Загальний метод обробки платежу
    public double processPayment(double amount) {
        return amount + calculateFee(amount);
    }

    public String getPayerName() {
        return payerName;
    }
}