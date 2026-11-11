package semester1.practice10;

public class BankCardInstrument extends PaymentInstrument {
    public BankCardInstrument(String payerName) {
        super(payerName);
    }

    @Override
    public double calculateFee(double amount) {
        // Комісія 1.5% + 5 грн фіксовано
        return amount * 0.015 + 5.0;
    }
}