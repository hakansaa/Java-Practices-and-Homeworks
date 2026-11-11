package semester1.practice10;

public class IbanDirectInstrument extends PaymentInstrument {
    public IbanDirectInstrument(String payerName) {
        super(payerName);
    }

    @Override
    public double calculateFee(double amount) {
        // 0% комісії для сум до 50 000 грн, інакше 0.5%
        return amount <= 50000 ? 0.0 : amount * 0.005;
    }
}