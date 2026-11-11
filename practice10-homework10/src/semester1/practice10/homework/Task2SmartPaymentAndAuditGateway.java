package semester1.practice10.homework;

// --- 1. ІНТЕРФЕЙСИ ФІНАНСОВИХ МОЖЛИВОСТЕЙ (CAN-DO) ---
interface CashbackCapable {
    double MAX_CASHBACK_UAH = 500.0;
    double getCashbackRate();

    default double calculateCashback(double baseAmount) {
        return Math.min(MAX_CASHBACK_UAH, baseAmount * getCashbackRate());
    }
}

interface Refundable {
    boolean requestRefund(String reason);
    boolean isRefunded();
}

interface BlockchainVerifiable {
    String getTxHash();
    int getConfirmations();

    default boolean isFinalized() {
        return getConfirmations() >= 12;
    }
}

// --- 2. БАЗОВИЙ АБСТРАКТНИЙ КЛАС ТРАНЗАКЦІЙ (IS-A) ---
abstract class AbstractPaymentTransaction {
    private final String txId;
    private final double baseAmountUah;
    private boolean processed = false;

    public AbstractPaymentTransaction(String txId, double baseAmountUah) {
        this.txId = txId;
        this.baseAmountUah = baseAmountUah;
    }

    public abstract double calculateFee();
    public abstract String getPaymentChannel();

    // Захищений final шаблонний метод фінансової безпеки
    public final boolean process() {
        if (baseAmountUah <= 0 || processed) return false;
        processed = true;
        return true;
    }

    public final double getTotalCharged() {
        return baseAmountUah + calculateFee();
    }

    public String getTxId() { return txId; }
    public double getBaseAmountUah() { return baseAmountUah; }
    public boolean isProcessed() { return processed; }
}

// --- 3. КОНКРЕТНІ КЛАСИ ТРАНЗАКЦІЙ ---
class PremiumCardPayment extends AbstractPaymentTransaction implements CashbackCapable, Refundable {
    private final String maskedPan;
    private boolean refunded = false;

    public PremiumCardPayment(String txId, double amount, String maskedPan) {
        super(txId, amount);
        this.maskedPan = maskedPan;
    }

    @Override public double calculateFee() { return getBaseAmountUah() * 0.015; } // 1.5%
    @Override public String getPaymentChannel() { return "Visa Infinite (" + maskedPan + ")"; }
    @Override public double getCashbackRate() { return 0.03; } // 3%

    @Override
    public boolean requestRefund(String reason) {
        if (isProcessed() && !refunded) {
            refunded = true;
            return true;
        }
        return false;
    }

    @Override public boolean isRefunded() { return refunded; }
}

class CryptoUsdtPayment extends AbstractPaymentTransaction implements CashbackCapable, BlockchainVerifiable {
    private final String txHash;
    private final int confirmations;

    public CryptoUsdtPayment(String txId, double amount, String txHash, int confirmations) {
        super(txId, amount);
        this.txHash = txHash;
        this.confirmations = confirmations;
    }

    @Override public double calculateFee() { return 40.0; } // Фіксований газ 40 грн
    @Override public String getPaymentChannel() { return "USDT TRC-20"; }
    @Override public double getCashbackRate() { return 0.01; } // 1% крипто-бонус
    @Override public String getTxHash() { return txHash; }
    @Override public int getConfirmations() { return confirmations; }
}

final class TaxTreasuryPayment extends AbstractPaymentTransaction implements Refundable {
    private final String taxCode;
    private boolean refunded = false;

    public TaxTreasuryPayment(String txId, double amount, String taxCode) {
        super(txId, amount);
        this.taxCode = taxCode;
    }

    @Override public double calculateFee() { return 0.0; } // Безкоштовно
    @Override public String getPaymentChannel() { return "ДержКазначейство (" + taxCode + ")"; }

    @Override
    public boolean requestRefund(String reason) {
        if (isProcessed() && !refunded) {
            refunded = true;
            return true;
        }
        return false;
    }

    @Override public boolean isRefunded() { return refunded; }
}

// --- 4. КЛАС ЗАПУСКУ ЗАВДАННЯ 2 ---
public class Task2SmartPaymentAndAuditGateway {
    public static void main(String[] args) {
        System.out.println("================ ПРОЦЕСИНГОВИЙ ЦЕНТР FINTECH (POLYMORPHISM & INTERFACES) ================");

        AbstractPaymentTransaction[] ledger = {
                new PremiumCardPayment("TX-101", 20000.0, "4149-xxxx-8812"),
                new CryptoUsdtPayment("TX-102", 8500.0, "0x9f8a...c4", 18),
                new TaxTreasuryPayment("TX-103", 5000.0, "EDRPOU-375001")
        };

        System.out.println("[1. ПРОВЕДЕННЯ ПАКЕТУ ТРАНЗАКЦІЙ (final process())]:");
        double totalTurnover = 0;
        double totalFees = 0;

        for (AbstractPaymentTransaction tx : ledger) {
            boolean success = tx.process();
            if (success) {
                totalTurnover += tx.getBaseAmountUah();
                totalFees += tx.calculateFee();
                System.out.printf("  ✅ [%s | %s] Сума: %.2f грн | Комісія: %.2f грн | Всього: %.2f грн\n",
                        tx.getTxId(), tx.getPaymentChannel(), tx.getBaseAmountUah(), tx.calculateFee(), tx.getTotalCharged());
            }
        }

        System.out.println("\n[2. АВТОМАТИЧНИЙ АУДИТ МОЖЛИВОСТЕЙ (Pattern Matching instanceof)]:");
        double totalCashbackPaid = 0;

        for (AbstractPaymentTransaction tx : ledger) {
            // Перевірка CashbackCapable
            if (tx instanceof CashbackCapable cb) {
                double cbAmount = cb.calculateCashback(tx.getBaseAmountUah());
                totalCashbackPaid += cbAmount;
                System.out.printf("  💰 [CashbackCapable] %s: нараховано бонуси = %.2f грн (з урахуванням лімітів)\n", tx.getTxId(), cbAmount);
            }

            // Перевірка BlockchainVerifiable
            if (tx instanceof BlockchainVerifiable bv) {
                System.out.printf("  ⛓️ [BlockchainVerifiable] %s: Hash=%s, Підтверджень=%d (Finalized: %s)\n",
                        bv.getTxHash(), bv.getTxHash(), bv.getConfirmations(), bv.isFinalized());
            }

            // Перевірка Refundable (тестове повернення для першої транзакції)
            if (tx instanceof Refundable ref && tx.getTxId().equals("TX-101")) {
                boolean refunded = ref.requestRefund("Подвійне списання коштів");
                System.out.printf("  🔄 [Refundable] Спроба повернення %s ('Подвійне списання'): УСПІШНО ПОВЕРНЕНО: %s\n", tx.getTxId(), refunded);
            }
        }

        System.out.println("\n[ПІДСУМКОВИЙ ЗВІТ ШЛЮЗУ]:");
        System.out.printf("  Сумарний базовий оборот:  %.2f грн\n", totalTurnover);
        System.out.printf("  Сумарна комісія шлюзу:      %.2f грн\n", totalFees);
        System.out.printf("  Сумарний виплачений кешбек: %.2f грн\n", totalCashbackPaid);
        System.out.println("=========================================================================================");
    }
}