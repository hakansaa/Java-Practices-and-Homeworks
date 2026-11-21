package semester1.practice12;

import java.util.*;

public class CustomIterableAndOopReviewDemo {

    // Завдання 25: ArithmeticProgression implements Iterable<Double>
    static final class ArithmeticProgression implements Iterable<Double> {
        private final double first;
        private final double step;
        private final int count;

        ArithmeticProgression(double first, double step, int count) {
            if (count < 0) throw new IllegalArgumentException("count >= 0");
            this.first = first;
            this.step = step;
            this.count = count;
        }

        @Override
        public Iterator<Double> iterator() {
            return new Iterator<>() {
                private int index = 0;

                @Override
                public boolean hasNext() { return index < count; }

                @Override
                public Double next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    double val = first + index * step;
                    index++;
                    return val;
                }
            };
        }
    }

    public static void task25ArithmeticProgression() {
        System.out.println("\n=== Завдання 25: Арифметична прогресія (Iterable) ===");
        ArithmeticProgression ap = new ArithmeticProgression(2.0, 3.0, 6);
        double iterSum = 0;
        for (double x : ap) {
            iterSum += x;
        }
        double formulaSum = 6 * (2 * 2.0 + (6 - 1) * 3.0) / 2.0;
        System.out.printf("Сума через for-each: %.2f, формула: %.2f%n", iterSum, formulaSum);
    }

    // Завдання 26: Playlist implements Iterable<Song> із захисним копіюванням
    static final class Song {
        private final String title;
        private final String artist;
        private final int durationSec;

        Song(String title, String artist, int durationSec) {
            this.title = title;
            this.artist = artist;
            this.durationSec = durationSec;
        }

        int getDurationSec() { return durationSec; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Song other)) return false;
            return Objects.equals(title, other.title) && Objects.equals(artist, other.artist);
        }

        @Override
        public int hashCode() { return Objects.hash(title, artist); }

        @Override
        public String toString() { return artist + " - " + title + " (" + durationSec + "с)"; }
    }

    static class Playlist implements Iterable<Song> {
        private final Set<Song> tracks = new LinkedHashSet<>();

        void addSong(Song s) { if (s != null) tracks.add(s); }

        Set<Song> getTracksCopy() { return new LinkedHashSet<>(tracks); }

        @Override
        public Iterator<Song> iterator() {
            return Collections.unmodifiableSet(tracks).iterator();
        }
    }

    public static void task26PlaylistIterable() {
        System.out.println("\n=== Завдання 26: Плейлист із захисним копіюванням ===");
        Playlist pl = new Playlist();
        pl.addSong(new Song("Track A", "Artist 1", 180));
        pl.addSong(new Song("Track B", "Artist 2", 210));
        pl.addSong(new Song("Track A", "Artist 1", 180)); // дублікат

        int totalSec = 0;
        for (Song s : pl) {
            totalSec += s.getDurationSec();
        }
        System.out.println("Треків: " + pl.getTracksCopy().size() + ", загальна тривалість: " + totalSec + "с");
    }

    // Завдання 27: Поліморфні фігури + Iterator + instanceof
    interface Scalable {
        void scale(double factor);
    }

    static abstract class AbstractShape {
        private final String name;
        AbstractShape(String name) { this.name = name; }
        String getName() { return name; }
        abstract double area();
    }

    static class Circle extends AbstractShape implements Scalable {
        private double r;
        Circle(String name, double r) { super(name); this.r = r; }
        @Override double area() { return Math.PI * r * r; }
        @Override public void scale(double factor) { r *= factor; }
    }

    static class Rectangle extends AbstractShape implements Scalable {
        private double w, h;
        Rectangle(String name, double w, double h) { super(name); this.w = w; this.h = h; }
        @Override double area() { return w * h; }
        @Override public void scale(double factor) { w *= factor; h *= factor; }
    }

    static final class FixedStampSquare extends AbstractShape {
        private final double side;
        FixedStampSquare(String name, double side) { super(name); this.side = side; }
        @Override double area() { return side * side; }
    }

    public static void task27PolymorphicShapes() {
        System.out.println("\n=== Завдання 27: Фігури, Iterator та instanceof ===");
        List<AbstractShape> shapes = new ArrayList<>(List.of(
                new Circle("C1", 2.0),          // ~12.57 < 15 -> видалиться
                new Circle("C2", 3.0),          // ~28.27
                new Rectangle("R1", 4.0, 5.0),  // 20.0
                new Rectangle("R2", 2.0, 3.0),  // 6.0 < 15 -> видалиться
                new FixedStampSquare("Stamp", 5.0), // 25.0 (не Scalable)
                new Circle("C3", 4.0)           // ~50.27
        ));

        Iterator<AbstractShape> it = shapes.iterator();
        while (it.hasNext()) {
            if (it.next().area() < 15.0) {
                it.remove();
            }
        }

        for (AbstractShape s : shapes) {
            if (s instanceof Scalable sc) {
                sc.scale(1.5);
            }
            System.out.printf("  %s: площа = %.2f%n", s.getName(), s.area());
        }
    }

    // Завдання 28: Банківський портфель + final метод
    static abstract class FinancialAsset {
        private final String ticker;
        protected final double investedAmount;

        FinancialAsset(String ticker, double investedAmount) {
            this.ticker = ticker;
            this.investedAmount = investedAmount;
        }

        abstract double annualYieldPercent();

        public final double calculateAnnualProfit() {
            return investedAmount * (annualYieldPercent() / 100.0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FinancialAsset other)) return false;
            return Objects.equals(ticker, other.ticker);
        }

        @Override
        public int hashCode() { return Objects.hashCode(ticker); }

        @Override
        public String toString() { return ticker + "(" + investedAmount + ", " + annualYieldPercent() + "%)"; }
    }

    static class GovernmentBond extends FinancialAsset {
        GovernmentBond(String ticker, double amount) { super(ticker, amount); }
        @Override double annualYieldPercent() { return 14.5; }
    }

    static class TechStock extends FinancialAsset {
        private final double yieldPct;
        TechStock(String ticker, double amount, double yieldPct) {
            super(ticker, amount);
            this.yieldPct = yieldPct;
        }
        @Override double annualYieldPercent() { return yieldPct; }
    }

    public static void task28FinancialPortfolio() {
        System.out.println("\n=== Завдання 28: Банківський портфель ===");
        Set<FinancialAsset> portfolio = new LinkedHashSet<>();
        portfolio.add(new TechStock("NVDA", 10000, 12.0));
        portfolio.add(new TechStock("NVDA", 5000, 20.0)); // дублікат за тікером
        portfolio.add(new TechStock("LOWY", 8000, 3.0));  // < 5% -> видалимо
        portfolio.add(new GovernmentBond("OVDP-1", 15000));

        Iterator<FinancialAsset> it = portfolio.iterator();
        while (it.hasNext()) {
            if (it.next().annualYieldPercent() < 5.0) {
                it.remove();
            }
        }

        double totalProfit = 0;
        for (FinancialAsset asset : portfolio) {
            totalProfit += asset.calculateAnnualProfit();
        }
        System.out.printf("Активи: %s, річний прибуток: %.2f%n", portfolio, totalProfit);
    }

    // Завдання 29: PrimeNumberGenerator implements Iterable<Integer> + гіпотеза Гольдбаха
    static final class PrimeNumberGenerator implements Iterable<Integer> {
        private final int maxLimit;
        PrimeNumberGenerator(int maxLimit) { this.maxLimit = maxLimit; }

        private static boolean isPrime(int n) {
            if (n < 2) return false;
            int limit = (int) Math.sqrt(n);
            for (int d = 2; d <= limit; d++) {
                if (n % d == 0) return false;
            }
            return true;
        }

        @Override
        public Iterator<Integer> iterator() {
            return new Iterator<>() {
                private int candidate = 2;
                private Integer nextPrime = findNext();

                private Integer findNext() {
                    while (candidate <= maxLimit) {
                        int cur = candidate++;
                        if (isPrime(cur)) return cur;
                    }
                    return null;
                }

                @Override
                public boolean hasNext() { return nextPrime != null; }

                @Override
                public Integer next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    Integer res = nextPrime;
                    nextPrime = findNext();
                    return res;
                }
            };
        }
    }

    public static void task29PrimeGeneratorAndGoldbach() {
        System.out.println("\n=== Завдання 29: Прості числа та гіпотеза Гольдбаха (84) ===");
        Set<Integer> primes = new LinkedHashSet<>();
        for (int p : new PrimeNumberGenerator(100)) {
            primes.add(p);
        }
        System.out.println("Прості до 100 (" + primes.size() + "): " + primes);

        int target = 84;
        for (int p1 : primes) {
            int p2 = target - p1;
            if (p1 <= p2 && primes.contains(p2)) {
                System.out.println("Гольдбах для 84: " + p1 + " + " + p2 + " = " + target);
                break;
            }
        }
    }

    // Завдання 30: FilteredIterator<T>
    interface ElementFilter<T> {
        boolean accept(T element);
    }

    static final class FilteredIterator<T> implements Iterator<T> {
        private final Iterator<T> source;
        private final ElementFilter<T> filter;
        private T nextMatch;
        private boolean hasMatch;

        FilteredIterator(Iterator<T> source, ElementFilter<T> filter) {
            this.source = source;
            this.filter = filter;
            advance();
        }

        private void advance() {
            hasMatch = false;
            while (source.hasNext()) {
                T candidate = source.next();
                if (filter.accept(candidate)) {
                    nextMatch = candidate;
                    hasMatch = true;
                    return;
                }
            }
        }

        @Override
        public boolean hasNext() { return hasMatch; }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T current = nextMatch;
            advance();
            return current;
        }
    }

    public static void task30FilteredIterator() {
        System.out.println("\n=== Завдання 30: Фільтруючий ітератор ===");
        Set<Integer> nums = new TreeSet<>(List.of(3, 7, 14, 19, 21, 25, 28));
        Iterator<Integer> divBy7 = new FilteredIterator<>(nums.iterator(), n -> n % 7 == 0);
        List<Integer> res = new ArrayList<>();
        while (divBy7.hasNext()) res.add(divBy7.next());
        System.out.println("Кратні 7: " + res);
    }

    // Завдання 31: Бібліотечний каталог (HashSet за ISBN -> TreeSet за роком спадання)
    static final class Book {
        private final String isbn;
        private final String title;
        private final int year;

        Book(String isbn, String title, int year) {
            this.isbn = isbn;
            this.title = title;
            this.year = year;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book other)) return false;
            return Objects.equals(isbn, other.isbn);
        }

        @Override
        public int hashCode() { return Objects.hashCode(isbn); }

        @Override
        public String toString() { return title + " [" + year + ", ISBN:" + isbn + "]"; }
    }

    public static void task31BookCatalog() {
        System.out.println("\n=== Завдання 31: Каталог книг (HashSet -> TreeSet) ===");
        Set<Book> uniqueBooks = new HashSet<>(List.of(
                new Book("ISBN-1", "Effective Java", 2018),
                new Book("ISBN-2", "Clean Code", 2008),
                new Book("ISBN-1", "Effective Java (2nd ed)", 2018),
                new Book("ISBN-3", "Java Concurrency", 2006),
                new Book("ISBN-4", "Modern Java in Action", 2019)
        ));

        TreeSet<Book> sorted = new TreeSet<>(
                Comparator.comparingInt((Book b) -> b.year).reversed().thenComparing(b -> b.title)
        );
        sorted.addAll(uniqueBooks);

        Iterator<Book> it = sorted.iterator();
        while (it.hasNext()) {
            if (it.next().year < 2015) {
                it.remove();
            }
        }
        System.out.println("Книги з 2015 року: " + sorted);
    }

    // Завдання 32: SmartHomeHub implements Iterable<AbstractSensor>
    interface Calibratable {
        void calibrate();
    }

    static abstract class AbstractSensor {
        private final String sensorId;
        private final boolean active;

        AbstractSensor(String sensorId, boolean active) {
            this.sensorId = sensorId;
            this.active = active;
        }

        String getSensorId() { return sensorId; }
        boolean isActive() { return active; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AbstractSensor other)) return false;
            return Objects.equals(sensorId, other.sensorId);
        }

        @Override
        public int hashCode() { return Objects.hashCode(sensorId); }
    }

    static class TempSensor extends AbstractSensor implements Calibratable {
        TempSensor(String id, boolean active) { super(id, active); }
        @Override public void calibrate() { System.out.println("    Калібрування термодатчика " + getSensorId()); }
    }

    static class MotionSensor extends AbstractSensor {
        MotionSensor(String id, boolean active) { super(id, active); }
    }

    static class SmokeSensor extends AbstractSensor implements Calibratable {
        SmokeSensor(String id, boolean active) { super(id, active); }
        @Override public void calibrate() { System.out.println("    Калібрування датчика диму " + getSensorId()); }
    }

    static class SmartHomeHub implements Iterable<AbstractSensor> {
        private final Set<AbstractSensor> sensors = new LinkedHashSet<>();
        void addSensor(AbstractSensor s) { sensors.add(s); }

        int removeOfflineSensors() {
            int removed = 0;
            Iterator<AbstractSensor> it = sensors.iterator();
            while (it.hasNext()) {
                if (!it.next().isActive()) {
                    it.remove();
                    removed++;
                }
            }
            return removed;
        }

        void calibrateAllSupported() {
            for (AbstractSensor s : this) {
                if (s instanceof Calibratable c) {
                    c.calibrate();
                }
            }
        }

        @Override
        public Iterator<AbstractSensor> iterator() { return sensors.iterator(); }
    }

    public static void task32SmartHomeHub() {
        System.out.println("\n=== Завдання 32: SmartHomeHub ===");
        SmartHomeHub hub = new SmartHomeHub();
        hub.addSensor(new TempSensor("T-1", true));
        hub.addSensor(new MotionSensor("M-1", false));
        hub.addSensor(new SmokeSensor("S-1", true));

        int offline = hub.removeOfflineSensors();
        System.out.println("Видалено офлайн-датчиків: " + offline);
        hub.calibrateAllSupported();
    }
}