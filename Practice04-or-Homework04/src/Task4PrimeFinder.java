import java.util.Scanner;
    public class Task4PrimeFinder {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== ДЕТЕКТОР ПРОСТИХ ЧИСЕЛ ===");
            int n;
            do {
                System.out.print("Введіть верхню межу N (>= 2): ");
                n = scanner.nextInt();
            } while (n < 2);

            int primeCount = 0;
            int primeSum = 0;

            System.out.println("\nЗнайдені прості числа у проміжку від 2 до " + n + ":");

            boolean first = true;
            for (int i = 2; i <= n; i++) {
                boolean isPrime = true;

                for (int j = 2; j * j <= i; j++) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(i);
                    first = false;

                    primeCount++;
                    primeSum += i;
                }
            }

            int totalNumbers = n - 1;
            double percentage = ((double) primeCount / totalNumbers) * 100;

            System.out.println("\n\n--- СТАТИСТИКА ---");
            System.out.println("Усього чисел у діапазоні:    " + totalNumbers);
            System.out.println("Знайдено простих чисел:      " + primeCount + " шт.");
            System.out.println("Сума всіх простих чисел:     " + primeSum);
            System.out.printf("Частка простих чисел:        %.2f%%\n", percentage);
            System.out.println("==============================");

            scanner.close();
        }
    }
