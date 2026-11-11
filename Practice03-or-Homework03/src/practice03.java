import java.util.Scanner;


public class practice03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Блок 1: Взаємодія з консоллю через Scanner (Завдання 1–8)

        // Завдання 1: Особиста картка користувача
        System.out.println("=== Завдання 1 ===");
        System.out.print("Введіть ім'я: ");
        String firstName = scanner.nextLine();
        System.out.print("Введіть прізвище: ");
        String lastName = scanner.nextLine();
        System.out.print("Введіть вік: ");
        int ageTask1 = scanner.nextInt();
        scanner.nextLine(); // Очищення буфера
        System.out.print("Введіть місто: ");
        String city = scanner.nextLine();

        System.out.println("=== ВХІДНІ ДАНІ ===");
        System.out.println("Ім'я: " + firstName);
        System.out.println("Прізвище: " + lastName);
        System.out.println("Вік: " + ageTask1);
        System.out.println("Місто: " + city);
        System.out.println("-------------------");
        System.out.println("Привіт, " + firstName + " " + lastName + " з міста " + city + "! Твій вік: " + ageTask1 + " р.");

        // Завдання 2: Ехо-калькулятор суми та добутку
        System.out.println("\n=== Завдання 2 ===");
        System.out.print("Введіть перше число: ");
        int numA = scanner.nextInt();
        System.out.print("Введіть друге число: ");
        int numB = scanner.nextInt();
        System.out.println("Сума: " + (numA + numB));
        System.out.println("Різниця: " + (numA - numB));
        System.out.println("Добуток: " + (numA * numB));
        System.out.println("Остача від ділення: " + (numA % numB));

        // Завдання 3: Конвертер валют
        System.out.println("\n=== Завдання 3 ===");
        System.out.print("Введіть суму в гривнях (UAH): ");
        double uah = scanner.nextDouble();
        System.out.print("Введіть курс USD: ");
        double rate = scanner.nextDouble();
        double usd = uah / rate;
        System.out.printf("Сума до видачі: %.2f USD\n", usd);

        // Завдання 4: Подолання пастки буфера Scanner
        System.out.println("\n=== Завдання 4 ===");
        System.out.print("Введіть номер квитка: ");
        int ticketNum = scanner.nextInt();
        scanner.nextLine(); // Очищення залишкового '\n'
        System.out.print("Введіть повне ім'я студента: ");
        String fullName = scanner.nextLine();
        System.out.print("Введіть спеціальність: ");
        String specialty = scanner.nextLine();
        System.out.println("Студент: " + fullName + ", квиток №" + ticketNum + ", спеціальність: " + specialty);

        // Завдання 5: Калькулятор індексу маси тіла (BMI)
        System.out.println("\n=== Завдання 5 ===");
        System.out.print("Ваш зріст (м): ");
        double height = scanner.nextDouble();
        System.out.print("Ваша вага (кг): ");
        double weight = scanner.nextDouble();
        double bmi = weight / (height * height);
        System.out.printf("Розрахований BMI: %.2f\n", bmi);

        // Завдання 6: Зчитування одного символу (char)
        System.out.println("\n=== Завдання 6 ===");
        System.out.print("Введіть символ: ");
        char ch = scanner.next().charAt(0);
        System.out.println("Символ: " + ch + " | Код символу: " + (int) ch);

        // Завдання 7: Периметр та площа прямокутного трикутника
        System.out.println("\n=== Завдання 7 ===");
        System.out.print("Катет A: ");
        double legA = scanner.nextDouble();
        System.out.print("Катет B: ");
        double legB = scanner.nextDouble();
        double hypotenuse = Math.sqrt(Math.pow(legA, 2) + Math.pow(legB, 2));
        double perimeter = legA + legB + hypotenuse;
        double area = 0.5 * legA * legB;
        System.out.println("Гіпотенуза: " + hypotenuse);
        System.out.println("Периметр: " + perimeter);
        System.out.println("Площа: " + area);

        // Завдання 8: Розрахунок чеку супермаркету
        System.out.println("\n=== Завдання 8 ===");
        scanner.nextLine(); // Очищення буфера після nextDouble()
        System.out.print("Товар: ");
        String product = scanner.nextLine();
        System.out.print("Ціна: ");
        double price = scanner.nextDouble();
        System.out.print("Кількість: ");
        int quantity = scanner.nextInt();
        double totalPrice = price * quantity;
        double vat = totalPrice * 0.20;
        System.out.println("Загальна сума: " + totalPrice + " грн (з них ПДВ 20%: " + vat + " грн)");

        // Блок 2: Умовні оператори if, if-else, if-else-if ladder (Завдання 9–16)

        // Завдання 9: Перевірка знаку числа
        System.out.println("\n=== Завдання 9 ===");
        System.out.print("Введіть число: ");
        int num9 = scanner.nextInt();
        if (num9 > 0) {
            System.out.println("Число додатне.");
        } else if (num9 < 0) {
            System.out.println("Число від'ємне.");
        } else {
            System.out.println("Число дорівнює нулю.");
        }

        // Завдання 10: Парність числа з особливим статусом нуля
        System.out.println("\n=== Завдання 10 ===");
        System.out.print("Введіть число: ");
        int num10 = scanner.nextInt();
        if (num10 == 0) {
            System.out.println("Число є нулем.");
        } else if (num10 % 2 == 0) {
            System.out.println("Число " + num10 + " є парним.");
        } else {
            System.out.println("Число " + num10 + " є непарним.");
        }

        // Завдання 11: Віковий ценз та категорії
        System.out.println("\n=== Завдання 11 ===");
        System.out.print("Введіть вік: ");
        int ageUser = scanner.nextInt();
        if (ageUser < 0 || ageUser > 120) {
            System.out.println("Некоректний вік");
        } else if (ageUser <= 12) {
            System.out.println("Дитина");
        } else if (ageUser <= 17) {
            System.out.println("Підліток");
        } else if (ageUser <= 64) {
            System.out.println("Дорослий");
        } else {
            System.out.println("Пенсіонер");
        }

        // Завдання 12: Академічний рейтинг
        System.out.println("\n=== Завдання 12 ===");
        System.out.print("Введіть бал (0-100): ");
        int score = scanner.nextInt();
        if (score < 0 || score > 100) {
            System.out.println("Помилка: некоректний бал!");
        } else if (score >= 90) {
            System.out.println("Відмінно (A)");
        } else if (score >= 82) {
            System.out.println("Дуже добре (B)");
        } else if (score >= 74) {
            System.out.println("Добре (C)");
        } else if (score >= 64) {
            System.out.println("Задовільно (D)");
        } else if (score >= 60) {
            System.out.println("Достатньо (E)");
        } else {
            System.out.println("Незадовільно (F)");
        }

        // Завдання 13: Пошук максимуму та мінімуму серед трьох чисел
        System.out.println("\n=== Завдання 13 ===");
        System.out.print("Введіть 3 числа: ");
        int valA = scanner.nextInt();
        int valB = scanner.nextInt();
        int valC = scanner.nextInt();

        int maxVal = valA;
        if (valB > maxVal) maxVal = valB;
        if (valC > maxVal) maxVal = valC;

        int minVal = valA;
        if (valB < minVal) minVal = valB;
        if (valC < minVal) minVal = valC;

        System.out.println("Максимум: " + maxVal);
        System.out.println("Мінімум: " + minVal);

        // Завдання 14: Визначення чверті декартової площини
        System.out.println("\n=== Завдання 14 ===");
        System.out.print("Введіть X: ");
        double coordX = scanner.nextDouble();
        System.out.print("Введіть Y: ");
        double coordY = scanner.nextDouble();

        if (coordX > 0 && coordY > 0) {
            System.out.println("Точка (" + coordX + "; " + coordY + ") знаходиться у I чверті.");
        } else if (coordX < 0 && coordY > 0) {
            System.out.println("Точка (" + coordX + "; " + coordY + ") знаходиться у II чверті.");
        } else if (coordX < 0 && coordY < 0) {
            System.out.println("Точка (" + coordX + "; " + coordY + ") знаходиться у III чверті.");
        } else if (coordX > 0 && coordY < 0) {
            System.out.println("Точка (" + coordX + "; " + coordY + ") знаходиться у IV чверті.");
        } else if (coordX == 0 && coordY == 0) {
            System.out.println("Точка знаходиться у початку координат (0, 0).");
        } else {
            System.out.println("Точка лежить на осі координат.");
        }

        // Завдання 15: Дослідження Dangling Else
        System.out.println("\n=== Завдання 15 ===");
        int x15 = 5;
        int y15 = 20;
        //  У коді без дужок 'else' прив'язується до внутрішнього 'if (y15 > 0)', а не зовнішнього.
        // Виправлений варіант із фігурними дужками:
        if (x15 > 10) {
            if (y15 > 0) {
                System.out.println("x > 10 та y > 0");
            }
        } else {
            System.out.println("x <= 10 (Тепер else прив'язаний до зовнішнього if!)");
        }

        // Завдання 16: Прогресивний тариф електроенергії
        System.out.println("\n=== Завдання 16 ===");
        System.out.print("Спожито (кВт·год): ");
        double kwh = scanner.nextDouble();
        double totalCost;
        if (kwh <= 100) {
            totalCost = kwh * 2.64;
        } else {
            totalCost = (100 * 2.64) + ((kwh - 100) * 4.32);
        }
        System.out.println("Сума до сплати: " + totalCost + " грн");

        // Блок 3: Тернарний оператор (?:) (Завдання 17–24)

        // Завдання 17: Більше з двох чисел
        System.out.println("\n=== Завдання 17 ===");
        System.out.print("Введіть x та y: ");
        int tX = scanner.nextInt();
        int tY = scanner.nextInt();
        int maxT = (tX > tY) ? tX : tY;
        System.out.println("Більше число: " + maxT);

        // Завдання 18: Модуль числа
        System.out.println("\n=== Завдання 18 ===");
        System.out.print("Введіть число: ");
        int tN = scanner.nextInt();
        int absVal = (tN < 0) ? -tN : tN;
        System.out.println("Модуль числа: " + absVal);

        // Завдання 19: Парне чи непарне в один рядок
        System.out.println("\n=== Завдання 19 ===");
        System.out.print("Введіть число: ");
        int tNum19 = scanner.nextInt();
        String result19 = (tNum19 % 2 == 0) ? "Парне" : "Непарне";
        System.out.println("Результат: " + result19);

        // Завдання 20: Статус складання заліку
        System.out.println("\n=== Завдання 20 ===");
        System.out.print("Введіть бал: ");
        int tScore = scanner.nextInt();
        System.out.println("Результат: " + ((tScore >= 60) ? "Зараховано" : "Незараховано"));

        // Завдання 21: Вкладений тернарний оператор для знаку
        System.out.println("\n=== Завдання 21 ===");
        System.out.print("Введіть число: ");
        int tSign = scanner.nextInt();
        String signStatus = (tSign > 0) ? "Додатне" : (tSign < 0) ? "Від'ємне" : "Нуль";
        System.out.println("Характеристика: " + signStatus);

        // Завдання 22: Розрахунок знижки магазину
        System.out.println("\n=== Завдання 22 ===");
        System.out.print("Сума покупки: ");
        double purchase = scanner.nextDouble();
        double finalPrice = (purchase > 1000) ? purchase * 0.90 : purchase * 0.98;
        System.out.println("До сплати зі знижкою: " + finalPrice + " грн");

        // Завдання 23: Безпечне ділення без винятку
        System.out.println("\n=== Завдання 23 ===");
        System.out.print("Введіть a та b: ");
        int divA = scanner.nextInt();
        int divB = scanner.nextInt();
        System.out.println((divB != 0) ? ("Результат: " + ((double) divA / divB)) : "Помилка: ділення на нуль неможливе!");

        // Завдання 24: Граматичне узгодження слів
        System.out.println("\n=== Завдання 24 ===");
        System.out.print("Кількість помилок: ");
        int errCount = scanner.nextInt();
        String errText = (errCount == 1) ? "Знайдено 1 помилку" : ("Знайдено " + errCount + " помилок");
        System.out.println(errText);

        // Блок 4: Логічні vs порозрядні оператори, Short-Circuit (Завдання 25–32)

        // Завдання 25: Демонстрація Short-Circuit AND (&&)
        System.out.println("\n=== Завдання 25 ===");
        int x25 = 10;
        int divisor25 = 0;
        // Оскільки divisor25 != 0 повернуло false, права частина (x25 / divisor25 > 1)
        // Не обчислюється, забігаючи наперед від ArithmeticException.
        if (divisor25 != 0 && (x25 / divisor25 > 1)) {
            System.out.println("Успішне ділення");
        } else {
            System.out.println("Ділення безпечно пропущено завдяки short-circuit!");
        }

        // Завдання 26: Побічний ефект: && проти &
        System.out.println("\n=== Завдання 26 ===");
        int counter1 = 0;
        int counter2 = 0;
        boolean b1 = false && (++counter1 > 0); // Лінивий: ++counter1 НЕ виконується
        boolean b2 = false & (++counter2 > 0);  // Жадібний: ++counter2 виконується завжди
        System.out.println("counter1 (після &&): " + counter1 + " (залишився 0, бо обчислення зупинилося)");
        System.out.println("counter2 (після &): " + counter2 + " (збільшився до 1, бо & обчислює обидві частини)");

        // Завдання 27: Демонстрація Short-Circuit OR (||)
        System.out.println("\n=== Завдання 27 ===");
        int number27 = 5;
        // Ліва частина true -> права частина (10 / 0 == 0) пропускається і помилки немає!
        if (number27 > 0 || (10 / 0 == 0)) {
            System.out.println("Умова виконана, аварійне ділення не викликалося!");
        }

        // Завдання 28: Побічний ефект: || проти |
        System.out.println("\n=== Завдання 28 ===");
        int counterA = 0;
        int counterB = 0;
        boolean test1 = true || (++counterA > 0);
        boolean test2 = true | (++counterB > 0);
        System.out.println("counterA (||): " + counterA); // 0
        System.out.println("counterB (|): " + counterB);   // 1

        // Завдання 29: Валідація часу
        System.out.println("\n=== Завдання 29 ===");
        System.out.print("Введіть години (0-23) та хвилини (0-59): ");
        int hours = scanner.nextInt();
        int minutes = scanner.nextInt();
        boolean isValidTime = (hours >= 0 && hours <= 23) && (minutes >= 0 && minutes <= 59);
        if (isValidTime) {
            System.out.printf("Введений час: %02d:%02d\n", hours, minutes);
        } else {
            System.out.println("Помилка: некоректні дані часу!");
        }

        // Завдання 30: Нерівність трикутника
        System.out.println("\n=== Завдання 30 ===");
        System.out.print("Введіть сторони трикутника a, b, c: ");
        double sideA = scanner.nextDouble();
        double sideB = scanner.nextDouble();
        double sideC = scanner.nextDouble();
        boolean triangleExists = (sideA + sideB > sideC) && (sideA + sideC > sideB) && (sideB + sideC > sideA);
        System.out.println("Трикутник існує: " + triangleExists);

        // Завдання 31: Порозрядна перевірка парності через &
        System.out.println("\n=== Завдання 31 ===");
        System.out.print("Введіть число: ");
        int num31 = scanner.nextInt();
        boolean isEvenBitwise = (num31 & 1) == 0;
        System.out.println("Число парне (побітово & 1): " + isEvenBitwise);
        System.out.println("Число парне (через % 2): " + (num31 % 2 == 0));
        // Пояснення: num31 & 1 перевіряє тільки наймолодший біт. Якщо він 0 - число парне.

        // Завдання 32: Багатофакторний дозвіл на фінансову операцію
        System.out.println("\n=== Завдання 32 ===");
        boolean isPinCorrect = true;
        boolean isCardActive = true;
        boolean hasSufficientBalance = true;
        boolean isDailyLimitExceeded = false;

        boolean isTransactionAllowed = isPinCorrect && isCardActive && hasSufficientBalance && !isDailyLimitExceeded;
        System.out.println("Транзакція дозволена: " + isTransactionAllowed);

        // Блок 5: Конструкція switch (Завдання 33–40)

        // Завдання 33: Класичний switch з break
        System.out.println("\n=== Завдання 33 ===");
        System.out.print("Введіть номер дня (1-7): ");
        int day33 = scanner.nextInt();
        switch (day33) {
            case 1: System.out.println("Понеділок"); break;
            case 2: System.out.println("Вівторок"); break;
            case 3: System.out.println("Середа"); break;
            case 4: System.out.println("Четвер"); break;
            case 5: System.out.println("П'ятниця"); break;
            case 6: System.out.println("Субота"); break;
            case 7: System.out.println("Неділя"); break;
            default: System.out.println("Некоректний номер дня"); break;
        }

        // Завдання 34: Навмисний Fall-Through у класичному switch
        System.out.println("\n=== Завдання 34 ===");
        System.out.print("Введіть номер дня (1-7): ");
        int day34 = scanner.nextInt();
        switch (day34) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Будній (робочий) день");
                break;
            case 6:
            case 7:
                System.out.println("Вихідний день");
                break;
            default:
                System.out.println("Некоректний день");
                break;
        }

        // Завдання 35: Modern Switch -> дні у місяці
        System.out.println("\n=== Завдання 35 ===");
        System.out.print("Введіть номер місяця (1-12): ");
        int month = scanner.nextInt();
        int days = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> 28;
            default -> 0;
        };
        System.out.println((days > 0) ? ("Кількість днів: " + days) : "Помилка: неіснуючий місяць!");

        // Завдання 36: Modern Switch як вираз — Пора року
        System.out.println("\n=== Завдання 36 ===");
        scanner.nextLine(); // Очищення буфера
        System.out.print("Введіть назву місяця: ");
        String monthName = scanner.nextLine().toLowerCase();
        String season = switch (monthName) {
            case "грудень", "січень", "лютий" -> "Зима";
            case "березень", "квітень", "травень" -> "Весна";
            case "червень", "липень", "серпень" -> "Літо";
            case "вересень", "жовтень", "листопад" -> "Осінь";
            default -> "Невідомий місяць";
        };
        System.out.println("Пора року: " + season);

        // Завдання 37: Switch вираз з yield
        System.out.println("\n=== Завдання 37 ===");
        System.out.print("Оберіть доставку (1 - Самовивіз, 2 - Кур'єр, 3 - Поштомат): ");
        int deliveryOption = scanner.nextInt();
        double weightProduct = 6.0; // Приклад ваги
        int deliveryCost = switch (deliveryOption) {
            case 1 -> 0;
            case 2 -> {
                if (weightProduct > 5.0) {
                    yield 150;
                } else {
                    yield 90;
                }
            }
            case 3 -> 60;
            default -> -1;
        };
        System.out.println("Вартість доставки: " + deliveryCost + " грн");

        // Завдання 38: Міні-калькулятор на Modern Switch
        System.out.println("\n=== Завдання 38 ===");
        System.out.print("Число 1: ");
        double calcA = scanner.nextDouble();
        System.out.print("Оберіть операцію (+, -, *, /): ");
        char op = scanner.next().charAt(0);
        System.out.print("Число 2: ");
        double calcB = scanner.nextDouble();

        String calcResult = switch (op) {
            case '+' -> String.valueOf(calcA + calcB);
            case '-' -> String.valueOf(calcA - calcB);
            case '*' -> String.valueOf(calcA * calcB);
            case '/' -> (calcB != 0) ? String.valueOf(calcA / calcB) : "Помилка: Ділення на нуль!";
            default -> "Невідома операція!";
        };
        System.out.println("Результат: " + calcResult);

        // Завдання 39: [⭐ Додаткова тема] equals vs switch
        System.out.println("\n=== Завдання 39 ===");
        scanner.nextLine(); // Очищення буфера
        System.out.print("Введіть команду (start/pause/stop/exit): ");
        String command = scanner.nextLine();

        // 1. command == "start" повертає false, тому що '==' порівнює посилання в пам'яті (Heap vs String Pool), а не контент!
        // 2. Безпечне порівняння:
        if (command.equalsIgnoreCase("start")) {
            System.out.println("[через equalsIgnoreCase]: Команда 'start' розпізнана!");
        }

        // 3. Через modern switch:
        switch (command.toLowerCase()) {
            case "start" -> System.out.println("Система успішно запущена!");
            case "pause" -> System.out.println("Систему поставлено на паузу.");
            case "stop"  -> System.out.println("Систему зупинено.");
            case "exit"  -> System.out.println("Вихід з програми.");
            default      -> System.out.println("Невідома команда!");
        }

        // Завдання 40: Підсумковий міні-проєкт: «Консольний банківський термінал»
        System.out.println("\n================ БАНКОМАТ ================");
        System.out.print("Введіть PIN: ");
        int pin = scanner.nextInt();

        if (pin != 1234) {
            System.out.println("Невірний PIN! Доступ заблоковано.");
        } else {
            System.out.println("Авторизація успішна!");
            double balance = 5000.0;

            System.out.println("Оберіть дію (1 - Баланс, 2 - Поповнення, 3 - Зняття, 4 - Вихід): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Ваш баланс: " + balance + " грн");
                case 2 -> {
                    System.out.print("Введіть суму поповнення: ");
                    double deposit = scanner.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("Рахунок поповнено! Поточний баланс: " + balance + " грн");
                    } else {
                        System.out.println("Некоректна сума!");
                    }
                }
                case 3 -> {
                    System.out.print("Введіть суму зняття: ");
                    double withdraw = scanner.nextDouble();

                    // Лінива перевірка з &&
                    if (withdraw > 0 && withdraw <= balance) {
                        // Тернарний оператор для комісії
                        double fee = (withdraw > 1000) ? withdraw * 0.01 : 0.0;
                        double totalDeduction = withdraw + fee;

                        if (totalDeduction <= balance) {
                            balance -= totalDeduction;
                            System.out.println("Комісія операції: " + fee + " грн");
                            System.out.println("Видано: " + withdraw + " грн. Залишок на рахунку: " + balance + " грн");
                        } else {
                            System.out.println("Недостатньо коштів для покриття комісії!");
                        }
                    } else {
                        System.out.println("Некоректна сума або недостатньо коштів на балансі!");
                    }
                }
                case 4 -> System.out.println("Дякуємо за користування банкоматом! До побачення.");
                default -> System.out.println("Невірний вибір операції.");
            }
        }
        System.out.println("==========================================");

        // Закриваємо сканер в самому кінці програми
        scanner.close();

    }
}