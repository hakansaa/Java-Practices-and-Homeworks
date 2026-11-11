package semester1.practice07;
import java.util.Arrays;

// ============================================================================
// БЛОК 1: Поля класу та базові конструктори (Завдання 1–8)
// ============================================================================

// Завдання 1
class Book {
    private String title;
    private String author;
    private int year;
    private double price;

    public Book() {
        this.title = "Невідомо";
        this.author = "Невідомий";
        this.year = 2000;
        this.price = 0.0;
    }

    public Book(String title, String author, int year, double price) {
        this.title = (title != null && !title.isBlank()) ? title : "Невідомо";
        this.author = (author != null && !author.isBlank()) ? author : "Невідомий";
        this.year = (year >= 0) ? year : 2000;
        this.price = (price >= 0.0) ? price : 0.0;
    }

    public void printInfo() {
        System.out.printf("Книга: '%s' | Автор: %s | Рік: %d | Ціна: %.2f грн\n", title, author, year, price);
    }
}

// Завдання 2
class Student {
    private String name;
    private int age;
    private double gpa;

    public Student(String name, int age, double gpa) {
        this.name = (name != null) ? name : "Гість";
        this.age = (age >= 15 && age <= 80) ? age : 18;
        this.gpa = (gpa >= 0.0 && gpa <= 5.0) ? gpa : 0.0;
    }

    public boolean isHonorStudent() {
        return gpa >= 4.5;
    }

    public void printStudent() {
        String status = isHonorStudent() ? "Відмінник" : "Звичайний студент";
        System.out.printf("Студент: %s, Вік: %d, GPA: %.2f [%s]\n", name, age, gpa, status);
    }
}

// Завдання 3
class BankAccount {
    private String accountNumber;
    private String ownerName;
    private double balance;

    public BankAccount(String accountNumber, String ownerName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = Math.max(0.0, initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void printAccount() {
        System.out.printf("Рахунок: %s | Власник: %s | Баланс: %.2f грн\n", accountNumber, ownerName, balance);
    }
}

// Завдання 4
class Point2D {
    private double x;
    private double y;

    public Point2D() {
        this(0.0, 0.0);
    }

    public Point2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distanceToOrigin() {
        return Math.sqrt(x * x + y * y);
    }

    public double distanceTo(double targetX, double targetY) {
        return Math.sqrt(Math.pow(targetX - x, 2) + Math.pow(targetY - y, 2));
    }

    public void printPoint() {
        System.out.printf("Point(%.2f; %.2f)\n", x, y);
    }
}

// Завдання 5
class Product {
    private String name;
    private double price;
    private int quantity;
    private String category;

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = Math.max(0.0, price);
        this.quantity = Math.max(0, quantity);
        this.category = category;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    public void applyDiscount(double percent) {
        if (percent >= 0 && percent <= 100) {
            price -= price * (percent / 100.0);
        }
    }

    public void printProduct() {
        System.out.printf("Товар: %s (%s) | Ціна: %.2f | Кількість: %d | Загалом: %.2f\n", name, category, price, quantity, getTotalValue());
    }
}

// Завдання 6
class Time {
    private int hours;
    private int minutes;
    private int seconds;

    public Time(int hours, int minutes, int seconds) {
        this.hours = (hours >= 0 && hours <= 23) ? hours : 0;
        this.minutes = (minutes >= 0 && minutes <= 59) ? minutes : 0;
        this.seconds = (seconds >= 0 && seconds <= 59) ? seconds : 0;
    }

    public void tick() {
        seconds++;
        if (seconds >= 60) {
            seconds = 0;
            minutes++;
            if (minutes >= 60) {
                minutes = 0;
                hours = (hours + 1) % 24;
            }
        }
    }

    public String toUniversalString() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}

// Завдання 7
class Rectangle {
    private double width;
    private double height;

    public Rectangle() {
        this(1.0, 1.0);
    }

    public Rectangle(double size) {
        this(size, size);
    }

    public Rectangle(double width, double height) {
        this.width = Math.max(0.1, width);
        this.height = Math.max(0.1, height);
    }

    public double getArea() { return width * height; }
    public double getPerimeter() { return 2 * (width + height); }
    public boolean isSquare() { return width == height; }
}

// Завдання 8
class Employee {
    private int id;
    private String fullName;
    private String position;
    private double monthlySalary;

    public Employee(int id, String fullName, String position, double monthlySalary) {
        this.id = id;
        this.fullName = fullName;
        this.position = position;
        this.monthlySalary = Math.max(0.0, monthlySalary);
    }

    public void raiseSalary(double percent) {
        if (percent > 0) monthlySalary += monthlySalary * (percent / 100.0);
    }

    public double getAnnualSalary() { return monthlySalary * 12; }

    public void printBadge() {
        System.out.printf("[%d] %s — %s (З/П: %.2f грн)\n", id, fullName, position, monthlySalary);
    }
}

// ============================================================================
// БЛОК 2: Ключове слово this та виклик this(...) у конструкторах (Завдання 9–16)
// ============================================================================

// Завдання 9
class User {
    private String login;
    private String email;
    private String role;
    private boolean isActive;

    public User(String login, String email, String role, boolean isActive) {
        this.login = login;
        this.email = email;
        this.role = role;
        this.isActive = isActive;
    }

    public User(String login, String email) {
        this(login, email, "USER", true);
    }

    public User(String login) {
        this(login, login + "@domain.com");
    }

    public User() {
        this("guest");
    }

    public void printProfile() {
        System.out.printf("User: %s | Email: %s | Role: %s | Active: %s\n", login, email, role, isActive);
    }
}

// Завдання 10
class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            System.out.println("Помилка: знаменник не може бути нулем! Встановлено 1.");
            denominator = 1;
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public Fraction(int wholeNumber) { this(wholeNumber, 1); }
    public Fraction() { this(0, 1); }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public void print() {
        if (denominator == 1) System.out.println(numerator);
        else System.out.println(numerator + "/" + denominator);
    }

    public double toDouble() {
        return (double) numerator / denominator;
    }
}

// Завдання 11
class Car {
    private String brand;
    private String model;
    private int year;
    private double mileage;
    private boolean engineStarted;

    public Car(String brand, String model, int year, double mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.mileage = Math.max(0.0, mileage);
        this.engineStarted = false;
    }

    public Car(String brand, String model, int year) { this(brand, model, year, 0.0); }
    public Car() { this("Unknown", "Standard", 2024); }

    public void startEngine() { this.engineStarted = true; }
    public void stopEngine() { this.engineStarted = false; }

    public void drive(double distance) {
        if (engineStarted && distance > 0) {
            mileage += distance;
            System.out.printf("Проїхали %.1f км. Поточний пробіг: %.1f км\n", distance, mileage);
        } else {
            System.out.println("Спершу заведіть двигун!");
        }
    }
}

// Завдання 12
class SmartBulb {
    private int brightness;
    private int colorTemp;
    private boolean isOn;

    public SmartBulb(int brightness, int colorTemp, boolean isOn) {
        this.brightness = Math.max(0, Math.min(100, brightness));
        this.colorTemp = Math.max(2700, Math.min(6500, colorTemp));
        this.isOn = isOn;
    }

    public SmartBulb(int brightness) { this(brightness, 4000, true); }
    public SmartBulb() { this(50); }

    public void printStatus() {
        System.out.printf("Bulb ON: %s | Brightness: %d%% | Temp: %dK\n", isOn, brightness, colorTemp);
    }
}

// Завдання 13
class QueryBuilder {
    private String table;
    private String fields = "*";
    private String whereClause = "";

    public QueryBuilder(String table) { this.table = table; }

    public QueryBuilder select(String fields) {
        this.fields = fields;
        return this;
    }

    public QueryBuilder where(String condition) {
        this.whereClause = condition;
        return this;
    }

    public String build() {
        return "SELECT " + fields + " FROM " + table + (whereClause.isEmpty() ? "" : " WHERE " + whereClause) + ";";
    }
}

// Завдання 14
class CoffeeOrder {
    private String type;
    private String size;
    private int sugarCount;
    private boolean withSyrup;
    private double finalPrice;

    public CoffeeOrder(String type, String size, int sugarCount, boolean withSyrup) {
        this.type = type;
        this.size = size;
        this.sugarCount = sugarCount;
        this.withSyrup = withSyrup;
        this.finalPrice = calculatePrice();
    }

    public CoffeeOrder(String type, String size) { this(type, size, 0, false); }
    public CoffeeOrder(String type) { this(type, "Medium"); }

    private double calculatePrice() {
        double base = switch (type.toLowerCase()) {
            case "еспресо" -> 40;
            case "капучино" -> 55;
            default -> 60;
        };
        double coeff = switch (size.toLowerCase()) {
            case "small" -> 1.0;
            case "large" -> 1.6;
            default -> 1.3;
        };
        return (base * coeff) + (withSyrup ? 15 : 0);
    }

    public void printReceipt() {
        System.out.printf("Кава: %s (%s), Цукор: %d, Сироп: %s -> До сплати: %.2f грн\n", type, size, sugarCount, withSyrup, finalPrice);
    }
}

// Завдання 15
class Box {
    private double width;
    private double height;
    private double depth;
    private String material;

    public Box(double width, double height, double depth, String material) {
        this.width = width; this.height = height; this.depth = depth; this.material = material;
    }
    public Box(double size, String material) { this(size, size, size, material); }
    public Box(Box other) { this(other.width, other.height, other.depth, other.material); }

    public double getVolume() { return width * height * depth; }
    public double getSurfaceArea() { return 2 * (width * height + width * depth + height * depth); }

    public void printBox() {
        System.out.printf("Box [%.1fx%.1fx%.1f] material: %s | Vol: %.2f\n", width, height, depth, material, getVolume());
    }
}

// Завдання 16
class FlightTicket {
    private String ticketNumber;
    private String passengerName;
    private String flightNumber;
    private String departureCity;
    private String arrivalCity;
    private String seatNumber;
    private boolean hasBaggage;

    public FlightTicket(String tNum, String pName, String fNum, String dep, String arr, String seat, boolean bag) {
        this.ticketNumber = tNum; this.passengerName = pName; this.flightNumber = fNum;
        this.departureCity = dep; this.arrivalCity = arr; this.seatNumber = seat; this.hasBaggage = bag;
    }
    public FlightTicket(String tNum, String pName, String fNum, String dep, String arr) {
        this(tNum, pName, fNum, dep, arr, "UNASSIGNED", false);
    }
    public void assignSeat(String seat) { this.seatNumber = seat; }
    public void printBoardingPass() {
        System.out.printf("Квиток: %s | Пасажир: %s | Рейс: %s (%s -> %s) | Місце: %s | Багаж: %s\n",
                ticketNumber, passengerName, flightNumber, departureCity, arrivalCity, seatNumber, hasBaggage);
    }
}

// ============================================================================
// БЛОК 3: Перевантаження методів (Method Overloading) (Завдання 17–24)
// ============================================================================

// Завдання 17
class MathOperations {
    public int add(int a, int b) { return a + b; }
    public double add(double a, double b) { return a + b; }
    public int add(int a, int b, int c) { return a + b + c; }
    public String add(String a, String b) { return a + " " + b; }

    public int multiply(int a, int b) { return a * b; }
    public double multiply(double a, double b) { return a * b; }
    public String multiply(String text, int times) {
        return text.repeat(Math.max(0, times));
    }
}

// Завдання 18
class DocumentPrinter {
    public void print(String message) { System.out.println(message); }
    public void print(int number) { System.out.println("[INT]: " + number); }
    public void print(double number, int decimals) {
        System.out.printf("[DOUBLE]: %." + decimals + "f\n", number);
    }
    public void print(String[] lines) {
        for (int i = 0; i < lines.length; i++) System.out.println((i + 1) + ". " + lines[i]);
    }
    public void print(String message, boolean uppercase) {
        System.out.println(uppercase ? message.toUpperCase() : message);
    }
}

// Завдання 19
class AreaCalculator {
    public double calculateArea(double side) { return side * side; }
    public double calculateArea(double width, double height) { return width * height; }
    public double calculateArea(double radius, boolean isCircle) { return isCircle ? Math.PI * radius * radius : 0; }
    public double calculateArea(double a, double b, double c) {
        double p = (a + b + c) / 2.0;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }
    public double calculateAreas(double base1, double base2, double height) {
        return ((base1 + base2) / 2.0) * height;
    }
}

// Завдання 20
class Logger {
    public void log(String message) { System.out.println("[INFO] " + message); }
    public void log(String level, String message) { System.out.println("[" + level + "] " + message); }
    public void log(String level, int errCode, String msg) { System.out.printf("[%s] (Code: %d): %s\n", level, errCode, msg); }
    public void log(String tag, String message, boolean timestamp) {
        if (timestamp) {
            System.out.println("[" + tag + "] [" + System.currentTimeMillis() + "]: " + message);
        } else {
            log(tag, message);
        }
    }
}

// Завдання 21
class ArraySearcher {
    public int find(int[] arr, int target) {
        return find(arr, target, 0);
    }
    public int find(int[] arr, int target, int startIndex) {
        if (arr == null) return -1;
        for (int i = Math.max(0, startIndex); i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
    public int find(String[] arr, String target) {
        return find(arr, target, false);
    }
    public int find(String[] arr, String target, boolean ignoreCase) {
        if (arr == null || target == null) return -1;
        for (int i = 0; i < arr.length; i++) {
            if (ignoreCase ? target.equalsIgnoreCase(arr[i]) : target.equals(arr[i])) return i;
        }
        return -1;
    }
}

// Завдання 22
class DateService {
    private int day;
    private int month;
    private int year;

    public DateService(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void add(int days) {
        day += days;
        while (day > 30) {
            day -= 30;
            month++;
            if (month > 12) {
                month = 1;
                year++;
            }
        }
    }

    public void add(int days, int months) {
        add(days);
        this.month += months;
        while (this.month > 12) {
            this.month -= 12;
            this.year++;
        }
    }

    public void add(int days, int months, int years) {
        add(days, months);
        this.year += years;
    }

    public void printDate() { System.out.printf("%02d.%02d.%d\n", day, month, year); }
}

// Завдання 23
class SalaryCalculator {
    public double calculate(double baseRate) { return baseRate; }
    public double calculate(double baseRate, double fixedBonus) { return baseRate + fixedBonus; }
    public double calculate(double baseRate, double bonusPercent, boolean isTaxDeducted) {
        double total = baseRate * (1 + bonusPercent / 100.0);
        return isTaxDeducted ? total * (1 - 0.195) : total;
    }
    public double calculate(double baseRate, int overtimeHours, double overtimeHourlyRate) {
        return baseRate + (overtimeHours * overtimeHourlyRate);
    }
}

// Завдання 24
class ArrayFilter {
    public int[] filter(int[] arr, int threshold) {
        return Arrays.stream(arr).filter(x -> x > threshold).toArray();
    }
    public int[] filter(int[] arr, int min, int max) {
        return Arrays.stream(arr).filter(x -> x >= min && x <= max).toArray();
    }
    public int[] filter(int[] arr, boolean keepEvens) {
        return Arrays.stream(arr).filter(x -> keepEvens ? (x % 2 == 0) : (x % 2 != 0)).toArray();
    }
}

// ============================================================================
// БЛОК 4: Статичні поля, методи та константи (static) (Завдання 25–32)
// ============================================================================

// Завдання 25
class UserAccount {
    private static int nextId = 1001;
    private int id;
    private String username;
    private String email;

    public UserAccount(String username, String email) {
        this.id = nextId++;
        this.username = username;
        this.email = email;
    }

    public static int getNextAvailableId() { return nextId; }
    public static void resetIdSequence(int startId) { nextId = startId; }
    public void printInfo() { System.out.printf("User #%d: %s (%s)\n", id, username, email); }
}

// Завдання 26
class ServerConfig {
    public static final String SERVER_NAME = "AlphaEnterprise";
    public static final int MAX_CONNECTIONS = 5;
    private static int activeConnections = 0;

    public static boolean connect() {
        if (activeConnections < MAX_CONNECTIONS) {
            activeConnections++;
            return true;
        }
        return false;
    }

    public static void disconnect() {
        if (activeConnections > 0) activeConnections--;
    }

    public static void printServerStatus() {
        System.out.printf("[%s] Підключень: %d/%d | Вільних слотів: %d\n",
                SERVER_NAME, activeConnections, MAX_CONNECTIONS, (MAX_CONNECTIONS - activeConnections));
    }
}

// Завдання 27
class MathUtils {
    public static final double PI = 3.1415926535;
    public static final double E = 2.7182818284;

    private MathUtils() {}

    public static long factorial(int n) {
        if (n < 0) return 0;
        long f = 1;
        for (int i = 2; i <= n; i++) f *= i;
        return f;
    }

    public static double power(double base, int exp) {
        return Math.pow(base, exp);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    public static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }
}

// Завдання 28
class CurrencyExchange {
    private static double usdToUahRate = 41.5;
    private static double eurToUahRate = 45.2;

    public static void updateRates(double newUsd, double newEur) {
        if (newUsd > 0) usdToUahRate = newUsd;
        if (newEur > 0) eurToUahRate = newEur;
    }

    public static double uahToUsd(double uah) { return uah / usdToUahRate; }
    public static double usdToUah(double usd) { return usd * usdToUahRate; }
    public static double eurToUah(double eur) { return eur * eurToUahRate; }
}

// Завдання 29
class FleetCar {
    private static int totalCars = 0;
    private static double totalCompanyMileage = 0.0;
    private String vinCode;
    private double currentMileage;

    public FleetCar(String vinCode, double initialMileage) {
        this.vinCode = vinCode;
        this.currentMileage = initialMileage;
        totalCars++;
        totalCompanyMileage += initialMileage;
    }

    public void addTrip(double km) {
        if (km > 0) {
            this.currentMileage += km;
            totalCompanyMileage += km;
        }
    }

    public static void printFleetSummary() {
        System.out.printf("Автопарк: Всього авто = %d | Сумарний пробіг компанії = %.1f км\n", totalCars, totalCompanyMileage);
    }
}

// Завдання 30
class StringUtils {
    private StringUtils() {}

    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        String clean = s.replaceAll("[^a-zA-Z0-9а-яА-Я]", "").toLowerCase();
        return clean.equals(new StringBuilder(clean).reverse().toString());
    }

    public static String reverse(String s) {
        return s == null ? null : new StringBuilder(s).reverse().toString();
    }

    public static int countVowels(String s) {
        if (s == null) return 0;
        int count = 0;
        String vowels = "aeiouyAEIOUYаоуеиієюяAOUEIаоуеиієюя";
        for (char c : s.toCharArray()) {
            if (vowels.indexOf(c) != -1) count++;
        }
        return count;
    }

    public static String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) return "****";
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}

// Завдання 31
class GameSession {
    public static final int MAX_SESSIONS = 3;
    private static int activeSessionsCount = 0;
    private String sessionToken;
    private String hostPlayer;
    private boolean isActive;

    private GameSession(String host) {
        this.hostPlayer = host;
        this.sessionToken = "SESSION-" + System.currentTimeMillis();
        this.isActive = true;
    }

    public static GameSession createSession(String host) {
        if (activeSessionsCount >= MAX_SESSIONS) {
            System.out.println("Помилка: усі сервера перевантажені!");
            return null;
        }
        activeSessionsCount++;
        return new GameSession(host);
    }

    public void terminate() {
        if (isActive) {
            isActive = false;
            activeSessionsCount--;
            System.out.println("Сесію " + sessionToken + " завершено.");
        }
    }

    public static int getActiveSessionsCount() { return activeSessionsCount; }
}

// Завдання 32
class PhysicsEngine {
    public static final double G = 9.80665;
    public static final double SPEED_OF_LIGHT = 299792458.0;

    private PhysicsEngine() {}

    public static double freeFallDistance(double seconds) {
        return (G * seconds * seconds) / 2.0;
    }

    public static double kineticEnergy(double massKg, double velocityMs) {
        return (massKg * velocityMs * velocityMs) / 2.0;
    }

    public static double restEnergy(double massKg) {
        return massKg * SPEED_OF_LIGHT * SPEED_OF_LIGHT;
    }
}

// ============================================================================
// БЛОК 5: Взаємодія між об'єктами (Object Collaboration) (Завдання 33–40)
// ============================================================================

// Завдання 33
class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}

class LineSegment {
    private Point start;
    private Point end;

    public LineSegment(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public LineSegment(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    public double getLength() {
        return Math.sqrt(Math.pow(end.getX() - start.getX(), 2) + Math.pow(end.getY() - start.getY(), 2));
    }

    public Point getMidPoint() {
        return new Point((start.getX() + end.getX()) / 2.0, (start.getY() + end.getY()) / 2.0);
    }

    public void printSegment() {
        System.out.printf("Відрізок: (%.1f; %.1f) -> (%.1f; %.1f) | Довжина: %.2f\n",
                start.getX(), start.getY(), end.getX(), end.getY(), getLength());
    }
}

// Завдання 34
class ClientAccount {
    private String iban;
    private String owner;
    private double balance;

    public ClientAccount(String iban, String owner, double balance) {
        this.iban = iban;
        this.owner = owner;
        this.balance = balance;
    }

    public boolean transferTo(ClientAccount recipient, double amount) {
        if (recipient == null || recipient == this) return false;
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            recipient.balance += amount;
            System.out.printf("Успішний переказ %.2f грн від %s до %s\n", amount, this.owner, recipient.owner);
            return true;
        }
        System.out.println("Недостатньо коштів або некоректна сума!");
        return false;
    }

    public void printAccount() {
        System.out.printf("Рахунок %s (%s): %.2f грн\n", iban, owner, balance);
    }
}

// Завдання 35
class Engine {
    private String serialNumber;
    private int horsePower;
    private boolean isRunning;

    public Engine(String serialNumber, int horsePower) {
        this.serialNumber = serialNumber;
        this.horsePower = horsePower;
        this.isRunning = false;
    }

    public void start() {
        this.isRunning = true;
        System.out.printf("Двигун %s (%d к.с.) заведено!\n", serialNumber, horsePower);
    }

    public void stop() {
        this.isRunning = false;
        System.out.println("Двигун зупинено.");
    }

    public boolean isRunning() { return isRunning; }
    public int getHorsePower() { return horsePower; }
}

class CarWithEngine {
    private String brand;
    private String model;
    private Engine engine;

    public CarWithEngine(String brand, String model, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.engine = engine;
    }

    public void startCar() {
        System.out.println("Водій тисне кнопку запуску в " + brand + " " + model);
        this.engine.start();
    }

    public void turnOff() {
        this.engine.stop();
    }

    public void printSpecs() {
        System.out.printf("Авто: %s %s з двигуном на %d к.с.\n", brand, model, engine.getHorsePower());
    }
}

// Завдання 36
class Processor {
    private String model;
    private double frequencyGHz;
    private double price;

    public Processor(String model, double frequencyGHz, double price) {
        this.model = model;
        this.frequencyGHz = frequencyGHz;
        this.price = price;
    }
    public double getPrice() { return price; }
    public String getModel() { return model; }
}

class Memory {
    private int sizeGB;
    private int frequencyMHz;
    private double price;

    public Memory(int sizeGB, int frequencyMHz, double price) {
        this.sizeGB = sizeGB;
        this.frequencyMHz = frequencyMHz;
        this.price = price;
    }
    public double getPrice() { return price; }
    public int getSizeGB() { return sizeGB; }
}

class Computer {
    private String title;
    private Processor cpu;
    private Memory ram;
    private double casePrice;

    public Computer(String title, Processor cpu, Memory ram, double casePrice) {
        this.title = title;
        this.cpu = cpu;
        this.ram = ram;
        this.casePrice = casePrice;
    }

    public double calculateTotalPrice() {
        return cpu.getPrice() + ram.getPrice() + casePrice;
    }

    public void upgradeMemory(Memory newRam) {
        this.ram = newRam;
        System.out.println("Пам'ять оновлено на " + newRam.getSizeGB() + " GB!");
    }

    public void printSpecs() {
        System.out.printf("ПК '%s' | Процесор: %s | ОЗП: %d GB | Ціна збірки: %.2f грн\n",
                title, cpu.getModel(), ram.getSizeGB(), calculateTotalPrice());
    }
}

// Завдання 37
class Hero {
    private String name;
    private int health;
    private int attackPower;
    private int defense;

    public Hero(String name, int health, int attackPower, int defense) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public void attack(Hero target) {
        int damage = Math.max(1, this.attackPower - target.defense);
        target.health = Math.max(0, target.health - damage);
        System.out.printf("%s завдає %d шкоди %s (залишилось %d HP)\n", this.name, damage, target.name, target.health);
    }

    public boolean isAlive() { return health > 0; }
    public String getName() { return name; }
}

// Завдання 38
class ProductItem {
    private int id;
    private String title;
    private double price;

    public ProductItem(int id, String title, double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
    public double getPrice() { return price; }
    public String getTitle() { return title; }
}

class OrderCustomer {
    private String name;
    private double walletBalance;

    public OrderCustomer(String name, double walletBalance) {
        this.name = name;
        this.walletBalance = walletBalance;
    }

    public boolean pay(double amount) {
        if (walletBalance >= amount) {
            walletBalance -= amount;
            return true;
        }
        return false;
    }
    public double getWalletBalance() { return walletBalance; }
}

class Order {
    private OrderCustomer customer;
    private ProductItem[] items;
    private int itemCount = 0;

    public Order(OrderCustomer customer, int maxItems) {
        this.customer = customer;
        this.items = new ProductItem[maxItems];
    }

    public void addProduct(ProductItem p) {
        if (itemCount < items.length) {
            items[itemCount++] = p;
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += items[i].getPrice();
        }
        return total;
    }

    public boolean checkout() {
        double sum = calculateTotal();
        if (customer.pay(sum)) {
            System.out.printf("Чек оплачено! Сума: %.2f грн. Баланс клієнта: %.2f грн\n", sum, customer.getWalletBalance());
            return true;
        } else {
            System.out.println("Оплата відхилена: недостатньо коштів у гаманці!");
            return false;
        }
    }
}

// Завдання 39
class Vector3D {
    private double x;
    private double y;
    private double z;

    public Vector3D(double x, double y, double z) {
        this.x = x; this.y = y; this.z = z;
    }

    public Vector3D() { this(0, 0, 0); }

    public Vector3D add(Vector3D other) {
        return new Vector3D(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public double dotProduct(Vector3D other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }

    public Vector3D crossProduct(Vector3D other) {
        return new Vector3D(
                this.y * other.z - this.z * other.y,
                this.z * other.x - this.x * other.z,
                this.x * other.y - this.y * other.x
        );
    }

    public boolean isOrthogonalTo(Vector3D other) {
        return Math.abs(dotProduct(other)) < 1e-9;
    }

    public void printVector() {
        System.out.printf("Vector3D(%.1f, %.1f, %.1f)\n", x, y, z);
    }
}

// Завдання 40
class BookItem {
    private String isbn;
    private String title;
    private String author;
    private boolean isAvailable;

    public BookItem(String isbn) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public boolean borrowBook() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public void returnBook() { isAvailable = true; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }

    public void printBook() {
        System.out.printf("Книга: '%s' (%s) | Доступна: %s\n", title, author, isAvailable ? "ТАК" : "НІ");
    }
}

class LibraryCatalog {
    private BookItem[] books;
    private int size = 0;

    public LibraryCatalog(int capacity) {
        this.books = new BookItem[capacity];
    }

    public void addBook(BookItem book) {
        if (size < books.length) {
            books[size++] = book;
        }
    }

    public BookItem findBookByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) {
                return books[i];
            }
        }
        return null;
    }

    public boolean borrowBook(String title) {
        BookItem book = findBookByTitle(title);
        if (book != null && book.isAvailable()) {
            return book.borrowBook();
        }
        return false;
    }

    public void printCatalog() {
        System.out.println("--- КАТАЛОГ БІБЛІОТЕКИ ---");
        for (int i = 0; i < size; i++) {
            books[i].printBook();
        }
    }
}

// ============================================================================
// ГОЛОВНИЙ КЛАС ДЛЯ ЗАПУСКУ
// ============================================================================

public class Practice07 {
    public static void main(String[] args) {
        System.out.println("=================== БЛОК 1: БАЗОВІ КОНСТРУКТОРИ ===================");

        // Завдання 1
        System.out.println("\n--- Завдання 1 ---");
        Book book1 = new Book();
        Book book2 = new Book("Кобзар", "Тарас Шевченко", 1840, 350.0);
        book1.printInfo();
        book2.printInfo();

        // Завдання 2
        System.out.println("\n--- Завдання 2 ---");
        Student student1 = new Student("Максим", 19, 4.8);
        Student student2 = new Student("Олег", 100, -2.0); // тест валідації
        student1.printStudent();
        student2.printStudent();

        // Завдання 3
        System.out.println("\n--- Завдання 3 ---");
        BankAccount acc = new BankAccount("UA1001", "Андрій", 500.0);
        acc.deposit(200);
        acc.withdraw(1000); // відмова
        acc.printAccount();

        // Завдання 4
        System.out.println("\n--- Завдання 4 ---");
        Point2D p1 = new Point2D();
        Point2D p2 = new Point2D(3, 4);
        System.out.println("Відстань до початку координат: " + p2.distanceToOrigin());

        // Завдання 5
        System.out.println("\n--- Завдання 5 ---");
        Product prod = new Product("Ноутбук", 25000, 2, "Електроніка");
        prod.applyDiscount(10);
        prod.printProduct();

        // Завдання 6
        System.out.println("\n--- Завдання 6 ---");
        Time time = new Time(23, 59, 59);
        System.out.println("До тику: " + time.toUniversalString());
        time.tick();
        System.out.println("Після тику: " + time.toUniversalString());

        // Завдання 7
        System.out.println("\n--- Завдання 7 ---");
        Rectangle rect = new Rectangle(4, 5);
        System.out.println("Площа: " + rect.getArea() + ", Це квадрат? " + rect.isSquare());

        // Завдання 8
        System.out.println("\n--- Завдання 8 ---");
        Employee emp = new Employee(1, "Іван Іванов", "Manager", 20000);
        emp.raiseSalary(15);
        emp.printBadge();


        System.out.println("\n=================== БЛОК 2: КЛЮЧОВЕ СЛОВО THIS ТА THIS(...) ===================");

        // Завдання 9
        System.out.println("\n--- Завдання 9 ---");
        User user = new User("admin");
        user.printProfile();

        // Завдання 10
        System.out.println("\n--- Завдання 10 ---");
        Fraction frac = new Fraction(4, 8);
        System.out.print("Скорочений дріб: "); frac.print();

        // Завдання 11
        System.out.println("\n--- Завдання 11 ---");
        Car car = new Car("BMW", "X5", 2023);
        car.drive(50); // Помилка (двигун вимкнено)
        car.startEngine();
        car.drive(50);

        // Завдання 12
        System.out.println("\n--- Завдання 12 ---");
        SmartBulb bulb = new SmartBulb();
        bulb.printStatus();

        // Завдання 13
        System.out.println("\n--- Завдання 13 ---");
        String sql = new QueryBuilder("users").select("id, name, email").where("age >= 18").build();
        System.out.println("SQL: " + sql);

        // Завдання 14
        System.out.println("\n--- Завдання 14 ---");
        CoffeeOrder coffee = new CoffeeOrder("Капучино", "Large", 2, true);
        coffee.printReceipt();

        // Завдання 15
        System.out.println("\n--- Завдання 15 ---");
        Box box1 = new Box(5.0, "Картон");
        Box box2 = new Box(box1);
        box2.printBox();

        // Завдання 16
        System.out.println("\n--- Завдання 16 ---");
        FlightTicket ticket = new FlightTicket("TK-99", "Марія", "PS777", "Київ", "Варшава");
        ticket.assignSeat("12A");
        ticket.printBoardingPass();


        System.out.println("\n=================== БЛОК 3: ПЕРЕВАНТАЖЕННЯ МЕТОДІВ ===================");

        // Завдання 17
        System.out.println("\n--- Завдання 17 ---");
        MathOperations math = new MathOperations();
        System.out.println("2 + 3 = " + math.add(2, 3));
        System.out.println("Рядок mult: " + math.multiply("Java ", 3));

        // Завдання 18
        System.out.println("\n--- Завдання 18 ---");
        DocumentPrinter docPrinter = new DocumentPrinter();
        docPrinter.print(3.14159, 2);

        // Завдання 19
        System.out.println("\n--- Завдання 19 ---");
        AreaCalculator areaCalc = new AreaCalculator();
        System.out.println("Площа круга (r=3): " + areaCalc.calculateArea(3.0, true));

        // Завдання 20
        System.out.println("\n--- Завдання 20 ---");
        Logger logger = new Logger();
        logger.log("ERROR", 500, "Internal Server Error");

        // Завдання 21
        System.out.println("\n--- Завдання 21 ---");
        ArraySearcher searcher = new ArraySearcher();
        System.out.println("Індекс числа 42: " + searcher.find(new int[]{10, 20, 42, 50}, 42));

        // Завдання 22
        System.out.println("\n--- Завдання 22 ---");
        DateService dateService = new DateService(15, 3, 2024);
        dateService.add(20, 3);
        dateService.printDate();

        // Завдання 23
        System.out.println("\n--- Завдання 23 ---");
        SalaryCalculator salCalc = new SalaryCalculator();
        System.out.println("Зарплата з податками: " + salCalc.calculate(30000, 20, true));

        // Завдання 24
        System.out.println("\n--- Завдання 24 ---");
        ArrayFilter filter = new ArrayFilter();
        System.out.println("Парні числа: " + Arrays.toString(filter.filter(new int[]{12, 5, 8, 19, 24}, true)));


        System.out.println("\n=================== БЛОК 4: СТАТИЧНІ ЧЛЕНИ (STATIC) ===================");

        // Завдання 25
        System.out.println("\n--- Завдання 25 ---");
        UserAccount acc1 = new UserAccount("alex", "alex@mail.com");
        UserAccount acc2 = new UserAccount("maria", "maria@mail.com");
        acc1.printInfo();
        acc2.printInfo();

        // Завдання 26
        System.out.println("\n--- Завдання 26 ---");
        ServerConfig.connect();
        ServerConfig.printServerStatus();

        // Завдання 27
        System.out.println("\n--- Завдання 27 ---");
        System.out.println("Факторіал 5: " + MathUtils.factorial(5));

        // Завдання 28
        System.out.println("\n--- Завдання 28 ---");
        System.out.println("100 UAH в USD: " + CurrencyExchange.uahToUsd(100));

        // Завдання 29
        System.out.println("\n--- Завдання 29 ---");
        FleetCar f1 = new FleetCar("VIN123", 1000);
        f1.addTrip(250);
        FleetCar.printFleetSummary();

        // Завдання 30
        System.out.println("\n--- Завдання 30 ---");
        System.out.println("Чи паліндром 'Мадам'? " + StringUtils.isPalindrome("Мадам"));

        // Завдання 31
        System.out.println("\n--- Завдання 31 ---");
        GameSession s1 = GameSession.createSession("Player1");
        GameSession s2 = GameSession.createSession("Player2");
        System.out.println("Активних сесій: " + GameSession.getActiveSessionsCount());

        // Завдання 32
        System.out.println("\n--- Завдання 32 ---");
        System.out.println("Відстань падіння за 5с: " + PhysicsEngine.freeFallDistance(5));


        System.out.println("\n=================== БЛОК 5: ВЗАЄМОДІЯ ОБ'ЄКТІВ ===================");

        // Завдання 33
        System.out.println("\n--- Завдання 33 ---");
        LineSegment seg = new LineSegment(0, 0, 6, 8);
        seg.printSegment();

        // Завдання 34
        System.out.println("\n--- Завдання 34 ---");
        ClientAccount ivan = new ClientAccount("UA1", "Іван", 10000);
        ClientAccount maria = new ClientAccount("UA2", "Марія", 2000);
        ivan.transferTo(maria, 3500);
        ivan.printAccount();
        maria.printAccount();

        // Завдання 35
        System.out.println("\n--- Завдання 35 ---");
        Engine engine = new Engine("V8-2024", 450);
        CarWithEngine sportCar = new CarWithEngine("Ford", "Mustang", engine);
        sportCar.startCar();

        // Завдання 36
        System.out.println("\n--- Завдання 36 ---");
        Processor cpu = new Processor("Intel i7", 3.8, 12000);
        Memory ram = new Memory(16, 3200, 3000);
        Computer pc = new Computer("Gaming PC", cpu, ram, 2500);
        pc.printSpecs();

        // Завдання 37
        System.out.println("\n--- Завдання 37 ---");
        Hero knight = new Hero("Лицар", 100, 30, 10);
        Hero orc = new Hero("Орк", 120, 25, 5);
        knight.attack(orc);

        // Завдання 38
        System.out.println("\n--- Завдання 38 ---");
        OrderCustomer cust = new OrderCustomer("Денис", 1500);
        Order order = new Order(cust, 3);
        order.addProduct(new ProductItem(1, "Клавіатура", 1200));
        order.checkout();

        // Завдання 39
        System.out.println("\n--- Завдання 39 ---");
        Vector3D v1 = new Vector3D(1, 0, 0);
        Vector3D v2 = new Vector3D(0, 1, 0);
        System.out.println("Вектори ортогональні? " + v1.isOrthogonalTo(v2));

        // Завдання 40
        System.out.println("\n--- Завдання 40 ---");
        LibraryCatalog catalog = new LibraryCatalog(5);
        catalog.addBook(new BookItem("1984"));
        catalog.borrowBook("1984");
        catalog.printCatalog();
    }
}