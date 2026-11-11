public class practice02 {
    public static void main(String[] args) {

        //Блок 1: Оголошення змінних та примітивні типи даних (Завдання 1–8)
        // Завдання 1: Особиста картка студента
        System.out.println("\n==== Завдання 1 ====\n");

        int age = 18;
        double height = 1.78;
        char firstLetter = 'О';
        boolean isStudent = true;

        System.out.println(age);
        System.out.println(height);
        System.out.println(firstLetter);
        System.out.println(isStudent);

        // Завдання 2: Діапазони цілих чисел
        System.out.println("\n==== Завдання 2 ====\n");

        byte temperature = -15;
        short currentYear = 2026;
        int cityPopulation = 1200000;
        long worldPopulation = 8100000000L;

        System.out.println(temperature);
        System.out.println(currentYear);
        System.out.println(cityPopulation);
        System.out.println(worldPopulation);

        //Завдання 3: Точність чисел з плаваючою комою
        System.out.println("\n==== Завдання 3 ====\n");

        float floatValue = 1.1234567f;
        double doubleValue = 1.123456789012345;

        System.out.println(floatValue);
        System.out.println(doubleValue);

        //Завдання 4:Символьний тип char та коди ASCII
        System.out.println("\n==== Завдання 4 ====\n");

        char symbol1 = 'J';
        char symbol2 = 74;
        char symbol3 = '\u004A';

        System.out.println(symbol1);
        System.out.println(symbol2);
        System.out.println(symbol3);

        //Завдання 5: Логічні прапорці (boolean)
        System.out.println("\n==== Завдання 5 ====\n");

        boolean isJavaFun = true;
        boolean isFishFly = false;
        boolean isSkyBlue = true;

        System.out.println(isFishFly);
        System.out.println(isJavaFun);
        System.out.println(isSkyBlue);

        //Завдання 6: Константи програми (final)
        System.out.println("\n==== Завдання 6 ====\n");

        final double GRAVITY = 9.81;
        final int DAYS_IN_WEEK = 7;
        final String UNIVERSITY_NAME = "Львівська Політехнітка";

        System.out.println(GRAVITY);
        System.out.println(DAYS_IN_WEEK);
        System.out.println(UNIVERSITY_NAME);

        //Завдання 7: Неявне розширення типів (Widening Casting)
        System.out.println("\n==== Завдання 7 ====\n");

        System.out.println("\nОчікуваний результат:\n");

        int intVal = 250;
        double doubleVal = intVal;
        System.out.println(intVal);
        System.out.println(doubleVal);

        //Завдання 8: Явне звуження типів (Narrowing Casting)
        System.out.println("\n==== Завдання 8 ====\n");

        double priceWithPennies = 49.99;
        int roundedPrice = (int) priceWithPennies;

        System.out.println(roundedPrice);

        // Блок 2: Конкатенація рядків та правила іменування (Завдання 9–16)

        //Завдання 9: Виправлення помилок стилю іменування
        System.out.println();
        int firstNumber = 5;
        double userWeight = 72.5;
        String userCity = "Kyiv";
        boolean isValiDUser = true;
        System.out.println(firstNumber + "," + userWeight + "," + userCity + "," + isValiDUser);

        //Завдання 10: Формування повної візитки через
        System.out.println("\n==== Завдання 10 ====\n");

        String firstName = "Тарас";
        String lastName = "Шевченко";
        age = 47;

        System.out.println("Мене звати " + firstName + " " + lastName + ", мені " + age + " років.");

        //Завдання 11: Пастка додавання чисел і рядків
        System.out.println("\n==== Завдання 11 ====\n");

        int a = 10;
        int b = 20;
        System.out.println("Сума без дужок: " + a + b);
        System.out.println("Сума з дужками: " + (a + b));
        /*
        Ну типу в першому там пріорітет на те, що просто  зєеднати два числа тобто а і б типу 10+30 =1030
        воно його баче як не математичну дію, а просто текст, а в другому вже є пріорітети, є дужки які дають
        команду, що це математичний вираз і тому (10+20=30)
         */

        //Завдання 12: Генератор адреси електронної пошти
        System.out.println("\n==== Завдання 12 ====\n");

        String login = "ivan.petrenko";
        String domain = "gmail.com";
        String role = "student";

        System.out.println("Користувач " + login + " (роль: " + role + ") має пошту " + login + "@" + domain);

        //Завдання 13: Багаторядкова конкатенація з ескейп-символами
        System.out.println("\n==== Завдання 13 ====\n");

        String productName = "Ноутбук";
        int quantity = 2;
        double pricePerUnit = 24500.50;
        System.out.println("ЧЕК:\nТовар: " + productName + "\nКількість: " + quantity + " шт." + "\nЦіна: " + pricePerUnit + " грн");

        //Завдання 14: Баланс банківського рахунку
        System.out.println("\n==== Завдання 14 ====\n");

        String currency = "UAH";
        int hryvnias = 1500;
        int kopecks = 75;
        System.out.println("Поточний баланс: " + hryvnias + "." + kopecks + " " + currency);

        //Завдання 15: Збирання URL-адреси веб-сторінки
        System.out.println("\n==== Завдання 15 ====\n");

        String protocol = "https://";
        String host = "api.example.com";
        String endpoint = "/users/";
        int userId = 42;
        System.out.println("Запит відправлено на: " + protocol + host + endpoint + userId);

        //Завдання 16: Формування резюме навичок
        System.out.println("\n==== Завдання 16 ====\n");

        String language = "Java";
        int level = 1;
        boolean isReady = true;
        System.out.println("Мова: " + language + " | " + "Рівень: " + level + " | " + "Готовність до практики: " + isReady);

        //Блок 3: Арифметичні оператори, остача % та інкремент/декремент (Завдання 17–24)

        //Завдання 17: Парність числа через остачу %
        System.out.println("\n==== Завдання 17 ====\n");

        int number = 19;
        int remainder = number % 2;
        System.out.println("Число " + number + " при діленні на 2 дає остачу: " + remainder);

        //Завдання 18: Конвертер секунд у хвилини та секунди
        System.out.println("\n==== Завдання 18 ====\n");

        int totalSeconds = 385;
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        System.out.println(totalSeconds + " секунд " + "= " + minutes + " хв " + seconds + " сек ");

        //Завдання 19: Сума цифр двозначного числа
        System.out.println("\n==== Завдання 19 ====\n");

        int n = 47;
        int tens = n / 10;
        int units = n % 10;
        int suma = tens + units;
        System.out.println("Число: " + n + ". Десятки: " + tens + ", Одиниці: " + units + ". Сума цифр: " + suma);

        //Завдання 20: Скорочені оператори присвоєння (Гра з очками)
        System.out.println("\n==== Завдання 20 ====\n");

        int score = 100;
        score += 50;
        System.out.println("Після додавання " + score);
        score -= 20;
        System.out.println("Після віднімання " + score);
        score *= 2;
        System.out.println("Після множення " + score);
        score /= 4;
        System.out.println("Після ділення " + score);

        //Завдання 21: Дослідження постфіксного інкременту x++
        System.out.println("\n==== Завдання 21 ====\n");

        int counter = 5;
        System.out.println(counter++);
        System.out.println(counter);
        /*
          Постфіксний інкремент (counter++) типу спочатку повертає початкове значення
          змінної (5) для використання у виразі/виведенні, і лише після цього збільшує її на 1.
          Тому  вже другий виклик друкує вже оновлене значення на 1 більше тобто (6).
         */

        //Завдання 22: Дослідження префіксного інкременту ++x
        System.out.println("\n==== Завдання 22 ====\n");

        counter = 5;
        System.out.println(++counter);
        /*
           Префіксний інкремент (++counter) спочатку збільшує значення змінної на 1
           (з 5 до 6), а потім повертає оновлене значення для виведення інфи.
         */

        //Завдання 23: Середнє арифметичне трьох чисел
        System.out.println("\n==== Завдання 23 ====\n");

        int grade1 = 4;
        int grade2 = 5;
        int grade3 = 4;
        double avarage = (grade1 + grade2 + grade3) / 3.0;
        System.out.println("Середній бал: " + avarage);

        //Завдання 24: Конвертер температур (Цельсій -> Фаренгейт і Кельвін)
        System.out.println("\n==== Завдання 24 ====\n");

        double celsius = 25.0;
        double fahrenheit = celsius * 1.8 + 32;
        double kelvin = celsius + 273.15;
        System.out.println(celsius + "°C = " + fahrenheit + "°F");
        System.out.println(celsius + "°C = " + kelvin + " K");

        //Блок 4: Оператори порівняння та логічні вирази (Завдання 25–32)

        //Завдання 25: Перевірка повноліття
        System.out.println("\n==== Завдання 25 ====\n");

        int userAge = 17;
        boolean isAdult = userAge >= 18;
        System.out.println("Вік: " + userAge + "." + " Чи є повнолітнім: " + isAdult);


        //Завдання 26: Порівняння двох чисел
        System.out.println("\n==== Завдання 26 ====\n");

        int x = 15;
        int y = 20;
        System.out.println("x == y:  " + (x == y));
        System.out.println("x != y:  " + (x != y));
        System.out.println("x > y:   " + (x > y));

        //Завдання 27: Перевірка діапазону (Оператор &&)
        System.out.println("\n==== Завдання 27 ====\n");

        int speed = 75;
        boolean isSafeSpeed = (speed >= 20) && (speed <= 80);
        System.out.println(isSafeSpeed);

        //Завдання 28: Пільговий квиток (Оператор ||)
        System.out.println("\n==== Завдання 28 ====\n");

        int personAge = 70;
        boolean hasFreePass = (personAge < 7) || (personAge >= 65);
        System.out.println("Вік: " + personAge + ". " + "Право на безкоштовний проїзд: " + hasFreePass);

        //Завдання 29: Інверсія прапорця (Оператор !)
        System.out.println("\n==== Завдання 29 ====\n");

        boolean isDoorLocked = true;
        boolean canEnter = !isDoorLocked;
        System.out.println("Двері зачинені:" + isDoorLocked);
        System.out.println("Чи можна увійти: " + canEnter);


        //Завдання 30: Перевірка кратності на 3 і на 5 одночасно
        System.out.println("\n==== Завдання 30 ====\n");

        int numbe = 25;
        boolean isDivisibleByBoth = (numbe % 3 == 0) && (numbe % 5 == 0);
        System.out.println("Чи ділиться 25 на 3 і на 5 без остачі: " + isDivisibleByBoth);

        int numbers = 30;
        boolean isDivisibleByBoths = (numbers % 3 == 0) && (numbers % 5 == 0);
        System.out.println("Чи ділиться 30 на 3 і на 5 без остачі: " + isDivisibleByBoths);


        // Завдання 31: Доступ до системи (Багатофакторна перевірка)
        System.out.println("\n==== Завдання 31 ====\n");
        boolean hasPassword = true;
        boolean hasSmsCode = true;
        boolean isAccountBlocked = false;
        boolean canLogin = hasPassword && hasSmsCode && !isAccountBlocked;
        System.out.println("Статус доступу: " + canLogin);


        // Завдання 32: Логічний детектор високосного року
        System.out.println("\n==== Завдання 32 ====\n");
        int year = 2024;
        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        System.out.println("Рік " + year + " є високосним: " + isLeapYear);
        ;


        //Блок 5: Клас Math та практичні математичні формули (Завдання 33–40)

        //Завдання 33: Теорема Піфагора (Math.pow та Math.sqrt)
        System.out.println("\n==== Завдання 33 ====\n");

        double a1 = 3.0;
        double b1 = 4.0;
        double c = Math.sqrt(Math.pow(a1, 2) + Math.pow(b1, 2));
        System.out.println("Катети: " + a1 + " та " + b1 + ". Гіпотенуза: " + c);

        //Завдання 34: Площа та довжина кола (Math.PI)
        System.out.println("\n==== Завдання 34 ====\n");

        double radius = 5.0;
        double circum = 2 * Math.PI * radius;
        double area = Math.PI * Math.pow(radius, 2);
        System.out.println("Радіус: " + radius);
        System.out.println("Довжина кола: " + circum);
        System.out.println("Площа круга: " + area);

        //Завдання 35: Різниця температур (Math.abs) та екстремуми (Math.min, Math.max)
        System.out.println("\n==== Завдання 35 ====\n");

        double dayTemp = 18.5;
        double nightTemp = -3.2;
        double diffrent = Math.abs(dayTemp - nightTemp);
        double maxTemp = Math.max(dayTemp, nightTemp);
        double minTemp = Math.min(dayTemp, nightTemp);
        System.out.println("Перепад температури: " + diffrent);
        System.out.println("Максимальна температура: " + maxTemp);
        System.out.println("Мінімальна температура: " + minTemp);

        //Завдання 36: Порівняння методів округлення (round, ceil, floor)
        System.out.println("\n==== Завдання 36 ====\n");

        double num = 5.67;
        double mat = Math.round(num);
        double up = Math.ceil(num);
        double min = Math.floor(num);
        System.out.println("\nДодатнє число (" + num + ")");
        System.out.println("Математичне: " + mat);
        System.out.println("Вгору: " + up);
        System.out.println("Вниз: " + min);

        double negNum = -5.67;
        System.out.println("\nВід'ємне число (" + negNum + ")");
        System.out.println("Математичне: " + Math.round(negNum));
        System.out.println("Вгору:  " + Math.ceil(negNum));  // -5.0 округлення вгору до більшого числа
        System.out.println("Вниз: " + Math.floor(negNum)); // -6.0 округлення вниз до меншого числа

        //Завдання 37: Дискримінант квадратного рівняння
        System.out.println("\n==== Завдання 37 ====\n");

        double a2 = 1.0;
        double b2 = -7.0;
        double c2 = 10.0;
        double d = Math.pow(b2, 2) - 4 * a2 * c2;

        double x1 = (-b2 + Math.sqrt(d)) / (2 * a2);
        double x2 = (-b2 - Math.sqrt(d)) / (2 * a2);

        System.out.println("Дискримінант D = " + d);
        System.out.println("Корінь x1 = " + x1);
        System.out.println("Корінь x2 = " + x2);


        //Завдання 38: Кидок шестигранного кубика (Math.random)
        System.out.println("\n==== Завдання 38 ====\n");

        int dice = (int) (Math.random() * 6) + 1;
        System.out.println("Випало на кубику: " + dice);


        //Завдання 39: Генерація випадкового числа в довільному діапазоні
        System.out.println("\n==== Завдання 39 ====\n");

        int minn = -10;
        int max = 35;
        int randomTemp = (int) (Math.random() * (max - minn + 1)) + minn;
        System.out.println("Погода на завтра: " + randomTemp + "°C");


        //Завдання 40: Підсумковий фінансовий калькулятор (Складні відсотки)
        System.out.println("\n==== Завдання 40 ====\n");

        double P = 10000.0; // Початковий депозит
        double r = 0.12;    // Річна ставка 12%
        double t = 3;          // Термін у роках


        double finalAmount = P * Math.pow(1 + r, t);
        double netProfit = finalAmount - P;

        System.out.println("Початковий депозит: " + P + " грн");
        System.out.println("Сума через " + t + " роки: " + finalAmount + " грн");
        System.out.println("Чистий прибуток: " + netProfit + " грн");
    }
}