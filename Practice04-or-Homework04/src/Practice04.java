import java.util.Scanner;

public class Practice04 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Блок 1
        System.out.println("=== Завдання 1 ==="); task1();
        System.out.println("\n=== Завдання 2 ==="); task2(scanner);
        System.out.println("\n=== Завдання 3 ==="); task3();
        System.out.println("\n=== Завдання 4 ==="); task4(scanner);
        System.out.println("\n=== Завдання 5 ==="); task5(scanner);

        // Блок 2
        System.out.println("\n=== Завдання 6 ==="); task6();
        System.out.println("\n=== Завдання 7 ==="); task7(scanner);
        System.out.println("\n=== Завдання 8 ==="); task8(scanner);
        System.out.println("\n=== Завдання 9 ==="); task9(scanner);
        System.out.println("\n=== Завдання 10 ==="); task10(scanner);
        System.out.println("\n=== Завдання 11 ==="); task11(scanner);
        System.out.println("\n=== Завдання 12 ==="); task12(scanner);
        System.out.println("\n=== Завдання 13 ==="); task13(scanner);

        // Блок 3
        System.out.println("\n=== Завдання 14 ==="); task14(scanner);
        System.out.println("\n=== Завдання 15 ==="); task15(scanner);
        System.out.println("\n=== Завдання 16 ==="); task16(scanner);
        System.out.println("\n=== Завдання 17 ==="); task17(scanner);
        System.out.println("\n=== Завдання 18 ==="); task18();
        System.out.println("\n=== Завдання 19 ==="); task19(scanner);
        System.out.println("\n=== Завдання 20 ==="); task20(scanner);

        // Блок 4
        System.out.println("\n=== Завдання 21 ==="); task21(scanner);
        System.out.println("\n=== Завдання 22 ==="); task22(scanner);
        System.out.println("\n=== Завдання 23 ==="); task23();
        System.out.println("\n=== Завдання 24 ==="); task24();
        System.out.println("\n=== Завдання 25 ==="); task25();
        System.out.println("\n=== Завдання 26 ==="); task26();
        System.out.println("\n=== Завдання 27 ==="); task27(scanner);
        System.out.println("\n=== Завдання 28 ==="); task28(scanner);

        // Блок 5
        System.out.println("\n=== Завдання 29 ==="); task29();
        System.out.println("\n=== Завдання 30 ==="); task30(scanner);
        System.out.println("\n=== Завдання 31 ==="); task31(scanner);
        System.out.println("\n=== Завдання 32 ==="); task32();
        System.out.println("\n=== Завдання 33 ==="); task33();
        System.out.println("\n=== Завдання 34 ==="); task34();
        System.out.println("\n=== Завдання 35 ==="); task35();

        // Блок 6
        System.out.println("\n=== Завдання 36 ==="); task36();
        System.out.println("\n=== Завдання 37 ==="); task37(scanner);
        System.out.println("\n=== Завдання 38 ==="); task38(scanner);
        System.out.println("\n=== Завдання 39 ==="); task39(scanner);
        System.out.println("\n=== Завдання 40 ==="); task40(scanner);

        scanner.close();
    }


    // Блок 1: DRY та концепція повторень


    public static void task1() {
        for (int i = 0; i < 7; i++) {
            System.out.println("Java - надійна та кросплатформна мова!");
        }
    }

    public static void task2(Scanner scanner) {
        System.out.print("Введіть текст: ");
        String text = scanner.nextLine();
        System.out.print("Введіть кількість повторень N: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("[" + i + "] " + text);
        }
    }

    public static void task3() {
        int balance = 0;
        for (int month = 1; month <= 12; month++) {
            balance += 500;
            System.out.println("Місяць " + month + ": " + balance + " грн");
        }
    }

    public static void task4(Scanner scanner) {
        System.out.print("Введіть курс євро до гривні: ");
        double rate = scanner.nextDouble();

        System.out.println("EUR\tUAH");
        for (int eur = 10; eur <= 100; eur += 10) {
            System.out.printf("%d\t%.2f грн\n", eur, eur * rate);
        }
    }

    public static void task5(Scanner scanner) {
        System.out.print("Введіть ширину рамки W: ");
        int w = scanner.nextInt();

        for (int i = 0; i < w; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    // Блок 2: Цикл while


    public static void task6() {
        int count = 10;
        while (count >= 1) {
            System.out.println(count);
            count--;
        }
        System.out.println("Поїхали! Ракета запущена!");
    }

    public static void task7(Scanner scanner) {
        int sum = 0;
        System.out.println("Вводьте числа (0 для завершення):");
        int num = scanner.nextInt();
        while (num != 0) {
            sum += num;
            num = scanner.nextInt();
        }
        System.out.println("Сума введених чисел: " + sum);
    }

    public static void task8(Scanner scanner) {
        System.out.print("Введіть число M: ");
        int m = scanner.nextInt();
        int power = 1;

        while (power <= m) {
            System.out.print(power + " ");
            power *= 2;
        }
        System.out.println();
    }

    public static void task9(Scanner scanner) {
        System.out.print("Введіть додатне число N: ");
        int n = Math.abs(scanner.nextInt());
        int temp = n;
        int count = 0;
        int sum = 0;

        if (temp == 0) {
            count = 1;
        } else {
            while (temp > 0) {
                sum += temp % 10;
                count++;
                temp /= 10;
            }
        }

        System.out.println("Кількість цифр: " + count);
        System.out.println("Сума цифр: " + sum);
    }

    public static void task10(Scanner scanner) {
        System.out.print("Введіть додатне число: ");
        int n = scanner.nextInt();
        int reversed = 0;

        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n /= 10;
        }
        System.out.println("Реверснуте число: " + reversed);
    }

    public static void task11(Scanner scanner) {
        System.out.print("Введіть початкову суму S: ");
        double s = scanner.nextDouble();
        System.out.print("Введіть річний відсоток P (%): ");
        double p = scanner.nextDouble();

        double target = s * 2;
        int years = 0;

        while (s < target) {
            s += s * (p / 100);
            years++;
        }
        System.out.println("Капітал подвоїться через " + years + " років.");
    }

    public static void task12(Scanner scanner) {
        System.out.print("Введіть число N (>1): ");
        int n = scanner.nextInt();
        int divisor = 2;

        while (n % divisor != 0) {
            divisor++;
        }
        System.out.println("Найменший дільник (>1): " + divisor);
    }

    public static void task13(Scanner scanner) {
        int secret = (int) (Math.random() * 100) + 1;
        int attempts = 0;
        int guess = 0;

        System.out.println("Вгадайте число від 1 до 100!");

        while (guess != secret) {
            System.out.print("Ваш варіант: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < secret) {
                System.out.println("Більше!");
            } else if (guess > secret) {
                System.out.println("Менше!");
            }
        }

        System.out.println("Вітаємо! Ви вгадали число " + secret + " за " + attempts + " спроб.");
    }


    // Блок 3: Цикл do-while та валідація


    public static void task14(Scanner scanner) {
        int choice;
        do {
            System.out.println("\n--- МЕНЮ ---");
            System.out.println("1. Розпочати нову гру");
            System.out.println("2. Завантажити збереження");
            System.out.println("3. Налаштування");
            System.out.println("0. Вихід");
            System.out.print("Оберіть пункт: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Розпочинаємо нову гру...");
                case 2 -> System.out.println("Завантаження збереженої гри...");
                case 3 -> System.out.println("Відкриваємо налаштування...");
                case 0 -> System.out.println("Завершення роботи.");
                default -> System.out.println("Некоректний вибір, спробуйте ще раз.");
            }
        } while (choice != 0);
    }

    public static void task15(Scanner scanner) {
        int age;
        do {
            System.out.print("Введіть свій вік (1-120): ");
            age = scanner.nextInt();
        } while (age < 1 || age > 120);

        System.out.println("Вік підтверджено: " + age);
    }

    public static void task16(Scanner scanner) {
        final int CORRECT_PIN = 1234;
        int attempts = 0;
        boolean accessGranted = false;

        do {
            System.out.print("Введіть PIN-код: ");
            int pin = scanner.nextInt();
            attempts++;

            if (pin == CORRECT_PIN) {
                accessGranted = true;
                break;
            } else {
                System.out.println("Невірний PIN! Залишилося спроб: " + (3 - attempts));
            }
        } while (attempts < 3);

        if (accessGranted) {
            System.out.println("Доступ надано");
        } else {
            System.out.println("Карту заблоковано!");
        }
    }

    public static void task17(Scanner scanner) {
        char continueOp;
        do {
            System.out.print("Введіть перше число: ");
            double a = scanner.nextDouble();
            System.out.print("Введіть друге число: ");
            double b = scanner.nextDouble();

            System.out.println("Сума: " + (a + b));

            System.out.print("Бажаєте продовжити? (y/n): ");
            continueOp = scanner.next().toLowerCase().charAt(0);
        } while (continueOp == 'y');
    }

    public static void task18() {
        int die1, die2;
        int attempts = 0;

        do {
            die1 = (int) (Math.random() * 6) + 1;
            die2 = (int) (Math.random() * 6) + 1;
            attempts++;
            System.out.println("Спроба " + attempts + ": [ " + die1 + " : " + die2 + " ]");
        } while (die1 != die2);

        System.out.println("Випав дубль! Всього спроб: " + attempts);
    }

    public static void task19(Scanner scanner) {
        System.out.print("Введіть чисельник: ");
        double numerator = scanner.nextDouble();
        double denominator;

        do {
            System.out.print("Введіть знаменник (не 0): ");
            denominator = scanner.nextDouble();
            if (denominator == 0) {
                System.out.println("Помилка: знаменник не може дорівнювати нулю!");
            }
        } while (denominator == 0);

        System.out.println("Результат ділення: " + (numerator / denominator));
    }

    public static void task20(Scanner scanner) {
        double total = 0;
        int count = 0;
        double score;

        System.out.println("Вводьте бали студента (0-100). Введіть від'ємне число для виходу:");

        do {
            System.out.print("Бал: ");
            score = scanner.nextDouble();

            if (score >= 0) {
                if (score <= 100) {
                    total += score;
                    count++;
                } else {
                    System.out.println("Некоректний бал! Введіть значення від 0 до 100.");
                }
            }
        } while (score >= 0);

        if (count > 0) {
            System.out.println("Середній бал: " + (total / count));
        } else {
            System.out.println("Не введено жодного коректного балу.");
        }
    }


    // Блок 4: Цикл for, break та continue


    public static void task21(Scanner scanner) {
        System.out.print("Введіть A: ");
        int a = scanner.nextInt();
        System.out.print("Введіть B (B >= A): ");
        int b = scanner.nextInt();

        for (int i = a; i <= b; i++) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void task22(Scanner scanner) {
        System.out.print("Введіть N (0 <= N <= 20): ");
        int n = scanner.nextInt();
        long factorial = 1;

        for (int i = 1; i <= n; i++) {
            factorial *= i;
        }
        System.out.println(n + "! = " + factorial);
    }

    public static void task23() {
        System.out.printf("%-10s | %-10s | %-10s\n", "Число", "Квадрат", "Куб");
        System.out.println("-------------------------------------");
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%-10d | %-10d | %-10d\n", i, i * i, i * i * i);
        }
    }

    public static void task24() {
        for (int i = 1; i <= 50; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void task25() {
        for (int i = 100; i <= 200; i++) {
            if (i % 13 == 0 && i % 7 == 0) {
                System.out.println("Перше число, кратне 13 та 7: " + i);
                break;
            }
        }
    }

    public static void task26() {
        for (char c = 'A'; c <= 'Z'; c++) {
            System.out.println(c + " -> " + (int) c);
        }
    }

    public static void task27(Scanner scanner) {
        System.out.print("Введіть кількість елементів N: ");
        int n = scanner.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                sum += i;
            } else {
                sum -= i;
            }
        }
        System.out.println("Сума ряду: " + sum);
    }

    public static void task28(Scanner scanner) {
        System.out.print("Введіть перший член a1: ");
        double a1 = scanner.nextDouble();
        System.out.print("Введіть різницю d: ");
        double d = scanner.nextDouble();
        System.out.print("Введіть кількість членів n: ");
        int n = scanner.nextInt();

        double sum = 0;
        System.out.print("Члени прогресії: ");
        for (int i = 0; i < n; i++) {
            double current = a1 + i * d;
            System.out.print(current + " ");
            sum += current;
        }
        System.out.println("\nСума прогресії: " + sum);
    }


    // Блок 5: Приведення типів (Type Casting)


    public static void task29() {
        byte b = 125;
        for (int i = 0; i < 6; i++) {
            System.out.println("Крок " + i + ": b = " + b);
            b = (byte) (b + 1);
        }
    }

    public static void task30(Scanner scanner) {
        System.out.print("Введіть кількість чисел N: ");
        int n = scanner.nextInt();
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            System.out.print("Число " + i + ": ");
            sum += scanner.nextInt();
        }

        double wrongAverage = sum / n;
        double rightAverage = (double) sum / n;

        System.out.println("Без приведення (невірно): " + wrongAverage);
        System.out.println("З приведенням (вірно): " + rightAverage);
    }

    public static void task31(Scanner scanner) {
        System.out.print("Введіть слово з 5 символів: ");
        String str = scanner.next();

        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            encrypted.append((char) (ch + 1));
        }
        System.out.println("Зашифрований рядок: " + encrypted);
    }

    public static void task32() {
        double[] prices = {19.99, 45.50, 12.10, 99.95, 3.40};
        int totalHryvnias = 0;
        double totalKopecks = 0.0;

        for (double price : prices) {
            int uah = (int) price;
            double kop = price - uah;

            totalHryvnias += uah;
            totalKopecks += kop;
        }

        System.out.println("Загальна сума цілих гривень: " + totalHryvnias);
        System.out.printf("Сума відкинутих копійок: %.2f грн\n", totalKopecks);
    }

    public static void task33() {
        int totalMb = 50;
        for (int i = 1; i <= totalMb; i++) {
            int percent = (int) (((double) i / totalMb) * 100);
            System.out.println("Завантажено: " + percent + "%");
        }
    }

    public static void task34() {
        long bigNumber = 3_000_000_000L;
        int truncatedNumber = (int) bigNumber;

        System.out.println("Оригінальне long значення: " + bigNumber);
        System.out.println("Після явного приведення до int: " + truncatedNumber);
    }

    public static void task35() {
        System.out.println("--- Цикл з double (накопичення похибки) ---");
        for (double d = 0.0; d <= 1.0; d += 0.1) {
            System.out.println(d);
        }

        System.out.println("\n--- Правильний цикл з int ---");
        for (int i = 0; i <= 10; i++) {
            double val = i / 10.0;
            System.out.println(val);
        }
    }


    // Блок 6: Вкладені цикли


    public static void task36() {
        System.out.println("=== Таблиця Піфагора ===");
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.printf("%4d", i * j);
            }
            System.out.println();
        }
    }

    public static void task37(Scanner scanner) {
        System.out.print("Введіть висоту H: ");
        int h = scanner.nextInt();
        System.out.print("Введіть ширину W: ");
        int w = scanner.nextInt();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (i == 0 || i == h - 1 || j == 0 || j == w - 1) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public static void task38(Scanner scanner) {
        System.out.print("Введіть висоту N: ");
        int n = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void task39(Scanner scanner) {
        System.out.print("Введіть N: ");
        int n = scanner.nextInt();

        System.out.print("Прості числа: ");
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void task40(Scanner scanner) {
        System.out.print("Введіть розмір дошки N: ");
        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("# ");
                } else {
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }
}