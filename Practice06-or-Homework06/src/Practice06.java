
// БЛОК 1: Перші класи, поля та об'єкти (1-8)

// Завдання 1
class Student {
    public String name;
    public int age;
    public double gpa;
}

// Завдання 2
class Car {
    public String brand;
    public String model;
    public int year;
    public int speed;
}

// Завдання 3
class Book {
    public String title;
    public String author;
    public int pages;
    public boolean isRead;
}

// Завдання 4
class Rectangle {
    public double width;
    public double height;
}

// Завдання 5
class Product {
    public String name;
    public double price;
    public int quantity;
}

// Завдання 6
class Smartphone {
    public String model;
    public int batteryLevel;
    public boolean isTurnedOn;
}

// Завдання 7
class Point2D {
    public double x;
    public double y;
}


// БЛОК 2: Базові методи void


// Завдання 9
class Greeter {
    public void sayHello() {
        System.out.println("Привіт усім!");
    }

    public void sayHelloTo(String name) {
        System.out.println("Привіт, " + name + "! Раді тебе бачити.");
    }
}

// Завдання 10
class Animal {
    public String species;
    public String name;

    public void makeSound() {
        System.out.println(name + " видає звук!");
    }

    public void printInfo() {
        System.out.println("Вид: " + species + ", Кличка: " + name);
    }
}

// Завдання 11
class Counter {
    public int count;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public void reset() {
        count = 0;
    }

    public void printCount() {
        System.out.println("Поточний лічильник: " + count);
    }
}

// Завдання 12
class TrafficLight {
    public String currentColor;

    public void changeColor(String newColor) {
        this.currentColor = newColor;
    }

    public void showSignal() {
        if ("Червоний".equalsIgnoreCase(currentColor)) {
            System.out.println("Стій!");
        } else if ("Жовтий".equalsIgnoreCase(currentColor)) {
            System.out.println("Увага!");
        } else if ("Зелений".equalsIgnoreCase(currentColor)) {
            System.out.println("Їдь!");
        } else {
            System.out.println("Невідомий сигнал світлофора");
        }
    }
}

// Завдання 13
class Player {
    public String nickname;
    public int health = 100;

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println(nickname + " загинув!");
        }
    }

    public void heal(int amount) {
        if (health > 0) {
            health += amount;
            if (health > 100) {
                health = 100;
            }
        }
    }
}

// Завдання 14
class Lamp {
    public boolean isOn;

    public void turnOn() {
        isOn = true;
    }

    public void turnOff() {
        isOn = false;
    }

    public void toggle() {
        isOn = !isOn;
    }

    public void status() {
        if (isOn) {
            System.out.println("Лампа світить");
        } else {
            System.out.println("Лампа вимкнена");
        }
    }
}

// Завдання 15
class Stopwatch {
    public int seconds;

    public void tick() {
        seconds++;
    }

    public void addSeconds(int s) {
        if (s > 0) {
            seconds += s;
        }
    }

    public void displayTime() {
        int minutes = seconds / 60;
        int secs = seconds % 60;
        System.out.printf("%02d:%02d\n", minutes, secs);
    }
}

// Завдання 16
class Printer {
    public String model;
    public int paperCount;

    public void printDocument(String title, int pages) {
        if (paperCount >= pages) {
            paperCount -= pages;
            System.out.println("Успішно надруковано документ \"" + title + "\" (" + pages + " стор.). Залишилося паперу: " + paperCount);
        } else {
            System.out.println("Помилка: Недостатньо паперу в принтері " + model + "!");
        }
    }
}


// БЛОК 3: Методи з поверненням значень (17-24)


// Завдання 17
class SimpleCalculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            System.out.println("Помилка: Ділення на нуль!");
            return 0.0;
        }
        return (double) a / b;
    }
}

// Завдання 18
class Circle {
    public double radius;

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }
}

// Завдання 19
class TemperatureConverter {
    public double celsiusToFahrenheit(double c) {
        return c * 1.8 + 32;
    }

    public double fahrenheitToCelsius(double f) {
        return (f - 32) / 1.8;
    }
}

// Завдання 20
class NumberChecker {
    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    public boolean isPositive(int n) {
        return n > 0;
    }

    public boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

// Завдання 21
class CurrencyExchange {
    public double usdRate = 41.5;

    public double toUSD(double uah) {
        return uah / usdRate;
    }

    public double toUAH(double usd) {
        return usd * usdRate;
    }
}

// Завдання 22
class Employee {
    public String name;
    public double baseSalary;
    public double bonusPercentage;

    public double calculateTotalSalary() {
        return baseSalary * (1 + bonusPercentage / 100);
    }

    public double calculateTax() {
        return calculateTotalSalary() * 0.195;
    }
}

// Завдання 23
class TextFormatter {
    public String formatTitle(String text) {
        return "*** " + text.toUpperCase() + " ***";
    }

    public int countVowels(String text) {
        if (text == null) return 0;
        int count = 0;
        String vowels = "аоуеиієюяAOUEIaoeui";
        for (char c : text.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}

// Завдання 24
class Triangle {
    public double a;
    public double b;
    public double c;

    public boolean isValid() {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public double getArea() {
        if (!isValid()) return 0.0;
        double p = getPerimeter() / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
}


// БЛОК 4: Модифікатори доступу та this (25-32)


// Завдання 25
class SecretVault {
    private String secretCode = "Pass123";

    public boolean verifyCode(String attempt) {
        return this.secretCode.equals(attempt);
    }
}

// Завдання 26
class UserAccount {
    private String username;
    private String password;

    public void setCredentials(String u, String p) {
        this.username = u;
        this.password = p;
    }

    public boolean login(String enteredUser, String enteredPass) {
        return this.username != null && this.username.equals(enteredUser)
                && this.password != null && this.password.equals(enteredPass);
    }

    public void changePassword(String oldPass, String newPass) {
        if (this.password != null && this.password.equals(oldPass)) {
            this.password = newPass;
            System.out.println("Пароль успішно змінено.");
        } else {
            System.out.println("Помилка: старий пароль невірний!");
        }
    }
}

// Завдання 27
class BankAccount {
    private String accountNumber;
    private double balance;

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Відмовлено: недостатньо коштів на рахунку!");
            return false;
        }
        balance -= amount;
        return true;
    }

    public double getBalance() {
        return balance;
    }
}

// Завдання 28
class Thermometer {
    private double temperature;

    public void setTemperature(double t) {
        if (t < -273.15) {
            System.out.println("Помилка: температура нижча за абсолютний нуль (-273.15°C)!");
        } else {
            this.temperature = t;
        }
    }

    public double getTemperature() {
        return temperature;
    }
}

// Завдання 29
class CarSpeedometer {
    private int speed = 0;

    public void accelerate(int delta) {
        if (delta > 0) {
            speed += delta;
            if (speed > 240) {
                speed = 240;
            }
        }
    }

    public void brake(int delta) {
        if (delta > 0) {
            speed -= delta;
            if (speed < 0) {
                speed = 0;
            }
        }
    }

    public int getSpeed() {
        return speed;
    }
}

// Завдання 30
class Person {
    private String name;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        if (age >= 0 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("Помилка: некоректний вік " + age);
        }
    }

    public void printSummary() {
        System.out.println("Особа: " + name + ", Вік: " + age);
    }
}

// Завдання 31
class DiscountCard {
    private String ownerName;
    private double totalSpent;
    private int discountPercent = 1;

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void addPurchase(double cost) {
        if (cost > 0) {
            totalSpent += cost;
            if (totalSpent >= 10000) {
                discountPercent = 10;
            } else if (totalSpent >= 5000) {
                discountPercent = 5;
            } else {
                discountPercent = 1;
            }
        }
    }

    public void printCardInfo() {
        System.out.println("Власник: " + ownerName + " | Витрачено: " + totalSpent + " грн | Знижка: " + discountPercent + "%");
    }
}

// Завдання 32
class SmartDoorLock {
    private int pinCode = 1234;
    private boolean isLocked = true;
    private int failedAttempts = 0;

    public void enterPin(int enteredPin) {
        if (failedAttempts >= 3) {
            System.out.println("СИСТЕМА ЗАБЛОКОВАНА! Викличте охорону.");
            return;
        }

        if (enteredPin == pinCode) {
            isLocked = false;
            failedAttempts = 0;
            System.out.println("Замок відчинено!");
        } else {
            failedAttempts++;
            System.out.println("Невірний PIN! Невірних спроб: " + failedAttempts);
            if (failedAttempts >= 3) {
                System.out.println("СИСТЕМА ЗАБЛОКОВАНА! Викличте охорону.");
            }
        }
    }
}


// БЛОК 5: Робота з масивами у класах (33-40)


// Завдання 33
class GradeBook {
    private String studentName;
    private int[] grades;

    public void setData(String name, int[] g) {
        this.studentName = name;
        this.grades = g;
    }

    public double getAverage() {
        if (grades == null || grades.length == 0) return 0.0;
        int sum = 0;
        for (int mark : grades) {
            sum += mark;
        }
        return (double) sum / grades.length;
    }

    public int getHighestGrade() {
        if (grades == null || grades.length == 0) return 0;
        int max = grades[0];
        for (int mark : grades) {
            if (mark > max) max = mark;
        }
        return max;
    }

    public int getLowestGrade() {
        if (grades == null || grades.length == 0) return 0;
        int min = grades[0];
        for (int mark : grades) {
            if (mark < min) min = mark;
        }
        return min;
    }
}

// Завдання 34
class ArrayStats {
    private int[] numbers;

    public void setNumbers(int[] nums) {
        this.numbers = nums;
    }

    public int countEvens() {
        if (numbers == null) return 0;
        int count = 0;
        for (int n : numbers) {
            if (n % 2 == 0) count++;
        }
        return count;
    }

    public int countOdds() {
        if (numbers == null) return 0;
        int count = 0;
        for (int n : numbers) {
            if (n % 2 != 0) count++;
        }
        return count;
    }

    public int calculateSum() {
        if (numbers == null) return 0;
        int sum = 0;
        for (int n : numbers) {
            sum += n;
        }
        return sum;
    }
}

// Завдання 35
class ShoppingCart {
    private double[] itemPrices = new double[10];
    private int count = 0;

    public void addItem(double price) {
        if (count < itemPrices.length) {
            itemPrices[count] = price;
            count++;
        } else {
            System.out.println("Кошик переповнений!");
        }
    }

    public double getTotalCost() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += itemPrices[i];
        }
        return total;
    }

    public void applyDiscount(double percent) {
        for (int i = 0; i < count; i++) {
            itemPrices[i] -= itemPrices[i] * (percent / 100.0);
        }
    }
}

// Завдання 36
class TemperatureTracker {
    private double[] dailyTemps = new double[7];

    public void setTemps(double[] temps) {
        if (temps != null && temps.length == 7) {
            this.dailyTemps = temps;
        }
    }

    public int countDaysAbove(double threshold) {
        int count = 0;
        for (double temp : dailyTemps) {
            if (temp > threshold) count++;
        }
        return count;
    }

    public void printWeeklyReport() {
        System.out.print("Температури за тиждень: ");
        double max = dailyTemps[0];
        int maxDayIndex = 0;
        for (int i = 0; i < dailyTemps.length; i++) {
            System.out.print(dailyTemps[i] + "°C ");
            if (dailyTemps[i] > max) {
                max = dailyTemps[i];
                maxDayIndex = i;
            }
        }
        System.out.println("\nНайтепліший день: День " + (maxDayIndex + 1) + " (" + max + "°C)");
    }
}

// Завдання 37
class ArraySearcher {
    private int[] data;

    public void setData(int[] data) {
        this.data = data;
    }

    public boolean contains(int target) {
        return findIndex(target) != -1;
    }

    public int findIndex(int target) {
        if (data == null) return -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == target) return i;
        }
        return -1;
    }
}

// Завдання 38
class Vector3D {
    private double[] coords = new double[3];

    public void setCoords(double x, double y, double z) {
        coords[0] = x;
        coords[1] = y;
        coords[2] = z;
    }

    public double getMagnitude() {
        return Math.sqrt(coords[0] * coords[0] + coords[1] * coords[1] + coords[2] * coords[2]);
    }

    public void multiplyByScalar(double scalar) {
        coords[0] *= scalar;
        coords[1] *= scalar;
        coords[2] *= scalar;
    }

    public void printVector() {
        System.out.println("(" + coords[0] + ", " + coords[1] + ", " + coords[2] + ")");
    }
}

// Завдання 39
class Matrix2D {
    private int[][] grid;

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void printMatrix() {
        if (grid == null) return;
        for (int[] row : grid) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public int getTotalSum() {
        if (grid == null) return 0;
        int sum = 0;
        for (int[] row : grid) {
            for (int val : row) {
                sum += val;
            }
        }
        return sum;
    }

    public int getRowSum(int rowIndex) {
        if (grid == null || rowIndex < 0 || rowIndex >= grid.length) return 0;
        int sum = 0;
        for (int val : grid[rowIndex]) {
            sum += val;
        }
        return sum;
    }
}

// Завдання 40
class MatrixDiagonals {
    private int[][] matrix;

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int getMainDiagonalSum() {
        if (matrix == null) return 0;
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }

    public int getAntiDiagonalSum() {
        if (matrix == null) return 0;
        int sum = 0;
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][n - 1 - i];
        }
        return sum;
    }

    public boolean isDiagonalSymmetric() {
        return getMainDiagonalSum() == getAntiDiagonalSum();
    }
}

// ==========================================
// ГОЛОВНИЙ КЛАС ДЛЯ ЗАПУСКУ
// ==========================================

public class Practice06 {
    public static void main(String[] args) {
        System.out.println("=== БЛОК 1: ПЕРШІ КЛАСИ ТА ОБ'ЄКТИ ===");

        // Завдання 1
        System.out.println("\n--- Завдання 1 ---");
        Student s1 = new Student();
        s1.name = "Денис";
        s1.age = 19;
        s1.gpa = 4.75;
        System.out.println("Студент: " + s1.name + ", " + s1.age + " років, GPA: " + s1.gpa);

        // Завдання 2
        System.out.println("\n--- Завдання 2 ---");
        Car car1 = new Car();
        car1.brand = "Toyota";
        car1.model = "Camry";
        car1.year = 2021;
        car1.speed = 180;

        Car car2 = new Car();
        car2.brand = "BMW";
        car2.model = "M5";
        car2.year = 2023;
        car2.speed = 250;

        System.out.println("Car 1: " + car1.brand + " " + car1.model + " (" + car1.year + ")");
        System.out.println("Car 2: " + car2.brand + " " + car2.model + " (" + car2.year + ")");
        if (car1.year > car2.year) {
            System.out.println("Новіша машина: " + car1.brand);
        } else if (car2.year > car1.year) {
            System.out.println("Новіша машина: " + car2.brand);
        } else {
            System.out.println("Машини одного року випуску.");
        }

        // Завдання 3
        System.out.println("\n--- Завдання 3 ---");
        Book emptyBook = new Book();
        System.out.println("title: " + emptyBook.title + ", author: " + emptyBook.author +
                ", pages: " + emptyBook.pages + ", isRead: " + emptyBook.isRead);

        // Завдання 4
        System.out.println("\n--- Завдання 4 ---");
        Rectangle rect = new Rectangle();
        rect.width = 7.5;
        rect.height = 4.0;
        double area = rect.width * rect.height;
        double perimeter = 2 * (rect.width + rect.height);
        System.out.println("Площа: " + area + ", Периметр: " + perimeter);

        // Завдання 5
        System.out.println("\n--- Завдання 5 ---");
        Product product = new Product();
        product.name = "Монітор";
        product.price = 6500.0;
        product.quantity = 8;
        System.out.println("Загальна вартість партії " + product.name + ": " + (product.price * product.quantity) + " грн");

        // Завдання 6
        System.out.println("\n--- Завдання 6 ---");
        Smartphone phone = new Smartphone();
        phone.model = "iPhone 13";
        phone.batteryLevel = 100;
        phone.isTurnedOn = true;

        phone.batteryLevel -= 35;
        if (phone.batteryLevel <= 0) {
            phone.isTurnedOn = false;
        }
        System.out.println("Телефон: " + phone.model + ", Заряд: " + phone.batteryLevel + "%, Увімкнений: " + phone.isTurnedOn);

        // Завдання 7
        System.out.println("\n--- Завдання 7 ---");
        Point2D pA = new Point2D();
        pA.x = 0; pA.y = 0;
        Point2D pB = new Point2D();
        pB.x = 3; pB.y = 4;
        double dist = Math.sqrt(Math.pow(pB.x - pA.x, 2) + Math.pow(pB.y - pA.y, 2));
        System.out.println("Відстань між точками A і B: " + dist);

        // Завдання 8
        System.out.println("\n--- Завдання 8 ---");
        Student first = new Student();
        first.name = "Іван";
        Student second = first;
        second.name = "Петро";
        // Пояснення: first.name дорівнює "Петро", оскільки змінні first та second вказують на одну й ту саму область пам'яті (Heap).
        System.out.println("first.name = " + first.name);

        System.out.println("\n=== БЛОК 2: БАЗОВІ МЕТОДИ VOID ===");

        // Завдання 9
        System.out.println("\n--- Завдання 9 ---");
        Greeter greeter = new Greeter();
        greeter.sayHello();
        greeter.sayHelloTo("Олексій");

        // Завдання 10
        System.out.println("\n--- Завдання 10 ---");
        Animal cat = new Animal();
        cat.species = "Кіт"; cat.name = "Мурчик";
        cat.printInfo(); cat.makeSound();

        // Завдання 11
        System.out.println("\n--- Завдання 11 ---");
        Counter counter = new Counter();
        counter.increment();
        counter.increment();
        counter.increment();
        counter.decrement();
        counter.printCount();

        // Завдання 12
        System.out.println("\n--- Завдання 12 ---");
        TrafficLight tl = new TrafficLight();
        tl.changeColor("Червоний"); tl.showSignal();
        tl.changeColor("Жовтий");   tl.showSignal();
        tl.changeColor("Зелений");  tl.showSignal();

        // Завдання 13
        System.out.println("\n--- Завдання 13 ---");
        Player player = new Player();
        player.nickname = "HeroX";
        player.takeDamage(40);
        System.out.println("Здоров'я: " + player.health);
        player.heal(20);
        System.out.println("Здоров'я після лікування: " + player.health);
        player.takeDamage(90);

        // Завдання 14
        System.out.println("\n--- Завдання 14 ---");
        Lamp lamp = new Lamp();
        lamp.status();
        lamp.toggle();
        lamp.status();
        lamp.toggle();
        lamp.status();

        // Завдання 15
        System.out.println("\n--- Завдання 15 ---");
        Stopwatch sw = new Stopwatch();
        sw.addSeconds(125);
        sw.displayTime();

        // Завдання 16
        System.out.println("\n--- Завдання 16 ---");
        Printer printer = new Printer();
        printer.model = "HP LaserJet";
        printer.paperCount = 10;
        printer.printDocument("Звіт", 5);
        printer.printDocument("Книга", 10);

        System.out.println("\n=== БЛОК 3: МЕТОДИ З ПОВЕРНЕННЯМ ЗНАЧЕНЬ ===");

        // Завдання 17
        System.out.println("\n--- Завдання 17 ---");
        SimpleCalculator calc = new SimpleCalculator();
        System.out.println("10 + 5 = " + calc.add(10, 5));
        System.out.println("10 - 5 = " + calc.subtract(10, 5));
        System.out.println("10 * 5 = " + calc.multiply(10, 5));
        System.out.println("10 / 4 = " + calc.divide(10, 4));

        // Завдання 18
        System.out.println("\n--- Завдання 18 ---");
        Circle circle = new Circle();
        circle.radius = 5.0;
        System.out.println("Площа: " + circle.getArea());
        System.out.println("Довжина кола: " + circle.getCircumference());

        // Завдання 19
        System.out.println("\n--- Завдання 19 ---");
        TemperatureConverter tc = new TemperatureConverter();
        double f = tc.celsiusToFahrenheit(25);
        System.out.println("25°C в F: " + f);
        System.out.println("Назад в C: " + tc.fahrenheitToCelsius(f));

        // Завдання 20
        System.out.println("\n--- Завдання 20 ---");
        NumberChecker nc = new NumberChecker();
        System.out.println("7 просте? " + nc.isPrime(7));
        System.out.println("12 парне? " + nc.isEven(12));
        System.out.println("-5 додатне? " + nc.isPositive(-5));

        // Завдання 21
        System.out.println("\n--- Завдання 21 ---");
        CurrencyExchange ex = new CurrencyExchange();
        System.out.printf("10000 UAH = %.2f USD\n", ex.toUSD(10000));

        // Завдання 22
        System.out.println("\n--- Завдання 22 ---");
        Employee emp = new Employee();
        emp.name = "Анна";
        emp.baseSalary = 30000;
        emp.bonusPercentage = 15;
        System.out.println("Повна зарплата: " + emp.calculateTotalSalary());
        System.out.println("Податок: " + emp.calculateTax());

        // Завдання 23
        System.out.println("\n--- Завдання 23 ---");
        TextFormatter tf = new TextFormatter();
        System.out.println(tf.formatTitle("java programming"));
        System.out.println("Голосних у слові 'Програмування': " + tf.countVowels("Програмування"));

        // Завдання 24
        System.out.println("\n--- Завдання 24 ---");
        Triangle tr = new Triangle();
        tr.a = 3; tr.b = 4; tr.c = 5;
        System.out.println("Трикутник 3, 4, 5 валідний? " + tr.isValid());
        System.out.println("Площа: " + tr.getArea());

        System.out.println("\n=== БЛОК 4: ІНКАПСУЛЯЦІЯ, PRIVATE ТА THIS ===");

        // Завдання 25
        System.out.println("\n--- Завдання 25 ---");
        SecretVault vault = new SecretVault();
        // vault.secretCode = "hacked"; // Помилка компіляції: secretCode has private access in SecretVault
        System.out.println("Перевірка коду Pass123: " + vault.verifyCode("Pass123"));

        // Завдання 26
        System.out.println("\n--- Завдання 26 ---");
        UserAccount user = new UserAccount();
        user.setCredentials("admin", "secret123");
        System.out.println("Логін успішний: " + user.login("admin", "secret123"));
        user.changePassword("wrong", "newpass");
        user.changePassword("secret123", "newpass");

        // Завдання 27
        System.out.println("\n--- Завдання 27 ---");
        BankAccount account = new BankAccount();
        account.deposit(1000);
        System.out.println("Баланс: " + account.getBalance());
        account.withdraw(1500);

        // Завдання 28
        System.out.println("\n--- Завдання 28 ---");
        Thermometer thermometer = new Thermometer();
        thermometer.setTemperature(-300);
        thermometer.setTemperature(36.6);
        System.out.println("Температура: " + thermometer.getTemperature() + "°C");

        // Завдання 29
        System.out.println("\n--- Завдання 29 ---");
        CarSpeedometer sp = new CarSpeedometer();
        sp.accelerate(300);
        System.out.println("Швидкість після розгону: " + sp.getSpeed() + " км/год");
        sp.brake(100);
        System.out.println("Швидкість після гальмування: " + sp.getSpeed() + " км/год");

        // Завдання 30
        System.out.println("\n--- Завдання 30 ---");
        Person p = new Person();
        p.setName("Марія");
        p.setAge(25);
        p.printSummary();

        // Завдання 31
        System.out.println("\n--- Завдання 31 ---");
        DiscountCard card = new DiscountCard();
        card.setOwnerName("Олег");
        card.addPurchase(3000);
        card.printCardInfo();
        card.addPurchase(4000);
        card.printCardInfo();

        // Завдання 32
        System.out.println("\n--- Завдання 32 ---");
        SmartDoorLock lock = new SmartDoorLock();
        lock.enterPin(1111);
        lock.enterPin(2222);
        lock.enterPin(3333);
        lock.enterPin(1234);

        System.out.println("\n=== БЛОК 5: РОБОТА З МАСИВАМИ ВСЕРЕДИНІ КЛАСІВ ===");

        // Завдання 33
        System.out.println("\n--- Завдання 33 ---");
        GradeBook gb = new GradeBook();
        gb.setData("Андрій", new int[]{85, 92, 78, 96, 65});
        System.out.println("Середній бал: " + gb.getAverage());
        System.out.println("Найвища оцінка: " + gb.getHighestGrade());

        // Завдання 34
        System.out.println("\n--- Завдання 34 ---");
        ArrayStats stats = new ArrayStats();
        stats.setNumbers(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("Парних: " + stats.countEvens() + ", Непарних: " + stats.countOdds() + ", Сума: " + stats.calculateSum());

        // Завдання 35
        System.out.println("\n--- Завдання 35 ---");
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(100); cart.addItem(200); cart.addItem(300);
        System.out.println("Сума до знижки: " + cart.getTotalCost() + " грн");
        cart.applyDiscount(15);
        System.out.println("Сума після знижки 15%: " + cart.getTotalCost() + " грн");

        // Завдання 36
        System.out.println("\n--- Завдання 36 ---");
        TemperatureTracker tracker = new TemperatureTracker();
        tracker.setTemps(new double[]{18.5, 20.0, 22.5, 19.0, 24.1, 21.0, 17.8});
        tracker.printWeeklyReport();
        System.out.println("Днів з температурою > 20°C: " + tracker.countDaysAbove(20.0));

        // Завдання 37
        System.out.println("\n--- Завдання 37 ---");
        ArraySearcher searcher = new ArraySearcher();
        searcher.setData(new int[]{10, 25, 42, 88, 99});
        System.out.println("Чи є 42? " + searcher.contains(42) + " (Індекс: " + searcher.findIndex(42) + ")");
        System.out.println("Чи є 100? " + searcher.contains(100));

        // Завдання 38
        System.out.println("\n--- Завдання 38 ---");
        Vector3D vec = new Vector3D();
        vec.setCoords(2, 3, 6);
        System.out.println("Довжина вектора (2, 3, 6): " + vec.getMagnitude());
        vec.multiplyByScalar(2);
        System.out.print("Після множення на 2: ");
        vec.printVector();

        // Завдання 39
        System.out.println("\n--- Завдання 39 ---");
        Matrix2D m2d = new Matrix2D();
        m2d.setGrid(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        System.out.println("Матриця:");
        m2d.printMatrix();
        System.out.println("Загальна сума: " + m2d.getTotalSum());
        System.out.println("Сума другого рядка (індекс 1): " + m2d.getRowSum(1));

        // Завдання 40
        System.out.println("\n--- Завдання 40 ---");
        MatrixDiagonals md = new MatrixDiagonals();
        md.setMatrix(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        });
        System.out.println("Сума головної діагоналі: " + md.getMainDiagonalSum());
        System.out.println("Сума побічної діагоналі: " + md.getAntiDiagonalSum());
        System.out.println("Чи симетричні діагоналі? " + md.isDiagonalSymmetric());
    }
}