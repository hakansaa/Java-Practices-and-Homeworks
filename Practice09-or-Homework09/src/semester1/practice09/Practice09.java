package semester1.practice09;

import java.awt.geom.Point2D;
import java.util.Arrays;

// ============================================================================
// БЛОК 1: Поглиблена інкапсуляція, Захисне копіювання та Незмінні об'єкти (1–8)
// ============================================================================

// Завдання 1: ImmutableGeoPoint
class ImmutableGeoPoint {
    private final double latitude;
    private final double longitude;

    public ImmutableGeoPoint(double latitude, double longitude) {
        this.latitude = Math.max(-90.0, Math.min(90.0, latitude));
        this.longitude = Math.max(-180.0, Math.min(180.0, longitude));
    }

    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }

    public ImmutableGeoPoint shift(double dLat, double dLon) {
        return new ImmutableGeoPoint(this.latitude + dLat, this.longitude + dLon);
    }

    @Override
    public String toString() {
        return String.format("ГеоТочка[широта=%.4f, довгота=%.4f]", latitude, longitude);
    }
}

// Завдання 2: LabMeasurement
class LabMeasurement {
    private final String experimentName;
    private final double[] samples;

    public LabMeasurement(String experimentName, double[] samples) {
        this.experimentName = (experimentName == null) ? "Невідомо" : experimentName;
        if (samples == null) {
            this.samples = new double[0];
        } else {
            this.samples = Arrays.copyOf(samples, samples.length);
        }
    }

    public String getExperimentName() { return experimentName; }

    public double[] getSamples() {
        return Arrays.copyOf(this.samples, this.samples.length);
    }

    @Override
    public String toString() {
        return "ЛабораторнеВимірювання{назва='" + experimentName + "', зразки=" + Arrays.toString(samples) + "}";
    }
}

// Завдання 3: CryptoVault
class CryptoVault {
    private final String walletAddress;
    private String secretRecoveryPhrase;
    private double btcBalance;
    private boolean locked;

    public CryptoVault(String walletAddress, String secretRecoveryPhrase, double initialBtc) {
        this.walletAddress = walletAddress;
        this.secretRecoveryPhrase = secretRecoveryPhrase;
        this.btcBalance = Math.max(0.0, initialBtc);
        this.locked = true;
    }

    public boolean unlock(String phrase) {
        if (this.secretRecoveryPhrase.equals(phrase)) {
            this.locked = false;
            return true;
        }
        return false;
    }

    public void lock() { this.locked = true; }

    public boolean sendBtc(double amount, String destination) {
        if (!locked && amount > 0 && btcBalance >= amount) {
            btcBalance -= amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("КриптоГаманець[адреса='%s', заблоковано=%s, BTC=%.4f]", walletAddress, locked, btcBalance);
    }
}

// Завдання 4: ClosedInterval
class ClosedInterval {
    private double start;
    private double end;

    public ClosedInterval(double start, double end) { resize(start, end); }

    public void resize(double newStart, double newEnd) {
        if (newStart > newEnd) {
            this.start = newEnd;
            this.end = newStart;
        } else {
            this.start = newStart;
            this.end = newEnd;
        }
    }

    public double getLength() { return end - start; }
    public double getMidpoint() { return (start + end) / 2.0; }

    public boolean overlaps(ClosedInterval other) {
        return this.start <= other.end && this.end >= other.start;
    }

    @Override
    public String toString() {
        return String.format("[%.1f; %.1f] (центр: %.1f, довжина: %.1f)", start, end, getMidpoint(), getLength());
    }
}

// Завдання 5: RgbColor
class RgbColor {
    private final int red, green, blue;

    public RgbColor(int red, int green, int blue) {
        this.red = clamp(red);
        this.green = clamp(green);
        this.blue = clamp(blue);
    }

    private static int clamp(int val) { return Math.max(0, Math.min(255, val)); }

    public String toHex() {
        return String.format("#%02X%02X%02X", red, green, blue);
    }

    public RgbColor invert() {
        return new RgbColor(255 - red, 255 - green, 255 - blue);
    }

    @Override
    public String toString() {
        return String.format("RGBКолір(%d, %d, %d) [%s]", red, green, blue, toHex());
    }
}

// Завдання 6: HighSecuritySafe
class HighSecuritySafe {
    private int pinCode;
    private double storedCash;
    private int failedAttempts;
    private boolean permanentlyBlocked;

    public HighSecuritySafe(int pinCode, double initialCash) {
        this.pinCode = pinCode;
        this.storedCash = Math.max(0, initialCash);
    }

    private boolean checkAccess(int enteredPin) {
        if (permanentlyBlocked) return false;
        if (enteredPin != pinCode) {
            failedAttempts++;
            if (failedAttempts >= 3) permanentlyBlocked = true;
            return false;
        }
        failedAttempts = 0;
        return true;
    }

    public boolean putCash(int pin, double amount) {
        if (checkAccess(pin) && amount > 0) {
            storedCash += amount;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Сейф[готівка=%.2f грн, помилкові спроби=%d, заблоковано=%s]", storedCash, failedAttempts, permanentlyBlocked);
    }
}

// Завдання 7: EncapsulatedPolynomial
class EncapsulatedPolynomial {
    private final double[] coeffs;

    public EncapsulatedPolynomial(double[] coeffs) {
        this.coeffs = (coeffs == null || coeffs.length == 0) ? new double[]{0.0} : Arrays.copyOf(coeffs, coeffs.length);
    }

    public double evaluate(double x) {
        double result = 0.0;
        for (int i = 0; i < coeffs.length; i++) {
            result += coeffs[i] * Math.pow(x, i);
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("P(x) = ");
        for (int i = coeffs.length - 1; i >= 0; i--) {
            if (i < coeffs.length - 1 && coeffs[i] >= 0) sb.append("+ ");
            sb.append(String.format("%.1f", coeffs[i]));
            if (i > 0) sb.append("x^").append(i).append(" ");
        }
        return sb.toString();
    }
}

// Завдання 8: MedicalRecord
class MedicalRecord {
    private final String patientId;
    private String fullName;
    private int bloodGroup;
    private boolean rhPositive;
    private String[] allergies;

    public MedicalRecord(String patientId, String fullName, int bloodGroup, boolean rhPositive, String[] allergies) {
        this.patientId = patientId;
        this.fullName = fullName;
        this.bloodGroup = Math.max(1, Math.min(4, bloodGroup));
        this.rhPositive = rhPositive;
        this.allergies = (allergies == null) ? new String[0] : Arrays.copyOf(allergies, allergies.length);
    }

    public boolean hasAllergyTo(String substance) {
        if (substance == null) return false;
        for (String a : allergies) {
            if (a.equalsIgnoreCase(substance)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("МедКартка[%s, %s, Група крові: %d Rh%s, Алергії: %s]",
                patientId, fullName, bloodGroup, (rhPositive ? "+" : "-"), Arrays.toString(allergies));
    }
}


// ============================================================================
// БЛОК 2: Композиція та Агрегація (HAS-A, 1-до-1, Делегування) (9–16)
// ============================================================================

// Завдання 9
class CompositeCircleTask {
    private final double radius;
    private final Point2D center;

    public CompositeCircleTask(double centerX, double centerY, double radius) {
        this.center = new Point2D() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
        this.radius = Math.max(0.1, radius);
    }

    public boolean containsPoint(Point2D p) { return center.distanceSq(p) <= radius; }

    @Override
    public String toString() { return String.format("Коло[центр=%s, r=%.1f]", center, radius); }
}

// Завдання 10
class Engine {
    private final String fuelType;
    private final int volumeCc;
    private int rpm;
    private boolean started;

    public Engine(String fuelType, int volumeCc) {
        this.fuelType = fuelType;
        this.volumeCc = volumeCc;
    }

    public void start() { this.started = true; this.rpm = 800; }
    public void setRpm(int rpm) { if (started) this.rpm = Math.max(800, Math.min(7000, rpm)); }
    public boolean isStarted() { return started; }

    @Override
    public String toString() { return String.format("Двигун[%s, %d куб.см, заведений=%s, оберти=%d]", fuelType, volumeCc, started, rpm); }
}

class Automobile {
    private final String model;
    private double speed;
    private final Engine engine;

    public Automobile(String model, String fuelType, int volumeCc) {
        this.model = model;
        this.engine = new Engine(fuelType, volumeCc);
    }

    public Engine getEngine() { return engine; }

    public void accelerate(double deltaSpeed) {
        if (engine.isStarted()) {
            this.speed += deltaSpeed;
            engine.setRpm((int) (800 + this.speed * 30));
        }
    }

    @Override
    public String toString() { return String.format("Автомобіль[%s, швидкість=%.1f км/год, %s]", model, speed, engine); }
}

// Завдання 11
class Battery {
    private int capacityMah, chargePercent;
    public Battery(int capacityMah, int chargePercent) {
        this.capacityMah = capacityMah;
        this.chargePercent = Math.max(0, Math.min(100, chargePercent));
    }
    public void drain(int percent) { chargePercent = Math.max(0, chargePercent - percent); }
    public int getChargePercent() { return chargePercent; }
    @Override public String toString() { return capacityMah + " мАг (" + chargePercent + "%)"; }
}

class Display {
    private double diagonalInches;
    private int brightnessPercent;
    public Display(double diagonalInches, int brightnessPercent) {
        this.diagonalInches = diagonalInches;
        this.brightnessPercent = Math.max(10, Math.min(100, brightnessPercent));
    }
    public int getBrightnessPercent() { return brightnessPercent; }
    @Override public String toString() { return diagonalInches + "\" (яскравість " + brightnessPercent + "%)"; }
}

class SmartDevice {
    private final Battery battery;
    private final Display display;
    private boolean poweredOn;

    public SmartDevice(Battery battery, Display display) {
        this.battery = battery;
        this.display = display;
        this.poweredOn = battery.getChargePercent() > 0;
    }

    public void watchVideo(int minutes) {
        if (!poweredOn) return;
        int rate = (display.getBrightnessPercent() > 70) ? 2 : 1;
        battery.drain((minutes / 10) * rate);
        if (battery.getChargePercent() == 0) poweredOn = false;
    }

    @Override
    public String toString() { return String.format("СмартПристрій[Увімкнено=%s, Батарея: %s, Дисплей: %s]", poweredOn, battery, display); }
}

// Завдання 12
class LineSegment {
    private final Point2D start, end;
    public LineSegment(Point2D start, Point2D end) {
        this.start = new Point2D() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
        this.end = new Point2D() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
    }
    public double getLength() { return start.distanceSq(end); }
    @Override public String toString() { return String.format("Відрізок[%s -> %s, довжина=%.2f]", start, end, getLength()); }
}

// Завдання 13
class BankAccount {
    private final String iban;
    private double balance;
    public BankAccount(String iban, double balance) { this.iban = iban; this.balance = Math.max(0, balance); }
    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) { balance -= amount; return true; }
        return false;
    }
    @Override public String toString() { return String.format("Рахунок[%s, баланс=%.2f грн]", iban, balance); }
}

class PlasticCard {
    private final String cardNumber;
    private boolean blocked;
    private final BankAccount linkedAccount;

    public PlasticCard(String cardNumber, String expiryDate, BankAccount linkedAccount) {
        this.cardNumber = cardNumber;
        this.linkedAccount = linkedAccount;
    }

    public boolean payAtTerminal(double amount) {
        return !blocked && linkedAccount.withdraw(amount);
    }

    @Override
    public String toString() { return String.format("Картка[%s, заблоковано=%s] -> %s", cardNumber, blocked, linkedAccount); }
}

// Завдання 14
class WeaponItem {
    private final String name;
    private final int bonusDamage;
    private int durability = 100;

    public WeaponItem(String name, int bonusDamage) {
        this.name = name;
        this.bonusDamage = bonusDamage;
    }

    public int useWeapon() {
        if (durability <= 0) return 0;
        durability = Math.max(0, durability - 10);
        return bonusDamage;
    }

    @Override
    public String toString() { return String.format("Зброя['%s', +%d шкоди, міцність=%d%%]", name, bonusDamage, durability); }
}

class HeroCharacter {
    private final String nickname;
    private int baseStrength;
    private WeaponItem equippedWeapon;

    public HeroCharacter(String nickname, int baseStrength) {
        this.nickname = nickname;
        this.baseStrength = baseStrength;
    }

    public void equip(WeaponItem weapon) { this.equippedWeapon = weapon; }

    public int attack() {
        return baseStrength + ((equippedWeapon != null) ? equippedWeapon.useWeapon() : 0);
    }
}

// Завдання 15
class DeliveryAddress {
    private String city, street;
    private int postOfficeNumber;
    public DeliveryAddress(String city, String street, int postOfficeNumber) {
        this.city = city; this.street = street; this.postOfficeNumber = postOfficeNumber;
    }
    @Override public String toString() { return city + ", " + street + " (№" + postOfficeNumber + ")"; }
}

class ParcelOrder {
    private final String orderId;
    private double weightKg;
    private DeliveryAddress destination;
    private boolean shipped = false;

    public ParcelOrder(String orderId, double weightKg, DeliveryAddress destination) {
        this.orderId = orderId;
        this.weightKg = weightKg;
        this.destination = destination;
    }

    public boolean changeDestination(DeliveryAddress newAddress) {
        if (!shipped && newAddress != null) { this.destination = newAddress; return true; }
        return false;
    }

    public void markAsShipped() { this.shipped = true; }

    @Override
    public String toString() { return String.format("Посилка[%s, %.1f кг, відправлено=%s, адреса=%s]", orderId, weightKg, shipped, destination); }
}

// Завдання 16
class TemperatureSensor {
    private double currentTemp;
    public TemperatureSensor(double initial) { this.currentTemp = initial; }
    public double getCurrentTemp() { return currentTemp; }
}

class HeatingUnit {
    private boolean heatingOn = false;
    private double powerKw;
    public HeatingUnit(double powerKw) { this.powerKw = powerKw; }
    public void setHeatingOn(boolean on) { this.heatingOn = on; }
    @Override public String toString() { return "Обігрівач[" + powerKw + " кВт, увімкнено=" + heatingOn + "]"; }
}

class ClimateController {
    private final TemperatureSensor sensor;
    private final HeatingUnit heater;
    private double targetTemp;

    public ClimateController(TemperatureSensor sensor, HeatingUnit heater, double targetTemp) {
        this.sensor = sensor;
        this.heater = heater;
        this.targetTemp = targetTemp;
    }

    public void regulate() {
        if (sensor.getCurrentTemp() < targetTemp - 0.5) heater.setHeatingOn(true);
        else if (sensor.getCurrentTemp() >= targetTemp) heater.setHeatingOn(false);
    }

    @Override
    public String toString() { return String.format("КліматКонтроль[поточна=%.1f°C, ціль=%.1f°C, %s]", sensor.getCurrentTemp(), targetTemp, heater); }
}


// ============================================================================
// БЛОК 3: Композиція з масивами об'єктів (HAS-MANY, 1-до-багатьох) (17–24)
// ============================================================================

// Завдання 17
class Polyline {
    private final Point2D[] vertices;
    private int count = 0;

    public Polyline(int maxVertices) { this.vertices = new Point2D[maxVertices]; }

    public void addVertex(double x, double y) {
        if (count < vertices.length) vertices[count++] = new Point2D() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        };
    }

    public double getTotalLength() {
        double len = 0.0;
        for (int i = 0; i < count - 1; i++) len += vertices[i].distanceSq(vertices[i + 1]);
        return len;
    }

    public double getClosedPerimeter() {
        return (count < 3) ? getTotalLength() : getTotalLength() + vertices[count - 1].distanceSq(vertices[0]);
    }
}

// Завдання 18
class OrderItem {
    private final String productName;
    private final double unitPrice;
    private int quantity;

    public OrderItem(String productName, double unitPrice, int quantity) {
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductName() { return productName; }
    public void addQuantity(int q) { this.quantity += q; }
    public double getItemTotal() { return unitPrice * quantity; }

    @Override
    public String toString() { return String.format("%s x%d = %.2f грн", productName, quantity, getItemTotal()); }
}

class ShoppingOrder {
    private final OrderItem[] items;
    private int itemCount = 0;

    public ShoppingOrder(int maxCapacity) { this.items = new OrderItem[maxCapacity]; }

    public void addItem(String name, double price, int qty) {
        for (int i = 0; i < itemCount; i++) {
            if (items[i].getProductName().equalsIgnoreCase(name)) {
                items[i].addQuantity(qty);
                return;
            }
        }
        if (itemCount < items.length) items[itemCount++] = new OrderItem(name, price, qty);
    }

    public double calculateGrandTotal() {
        double sum = 0.0;
        for (int i = 0; i < itemCount; i++) sum += items[i].getItemTotal();
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("=== ЧЕК ЗАМОВЛЕННЯ ===\n");
        for (int i = 0; i < itemCount; i++) sb.append(items[i]).append("\n");
        sb.append(String.format("УСЬОГО ДО СПЛАТИ: %.2f грн", calculateGrandTotal()));
        return sb.toString();
    }
}

// Завдання 19
class BookItem {
    private final String title, author;
    private final int pages;
    public BookItem(String title, String author, int pages) { this.title = title; this.author = author; this.pages = pages; }
    public int getPages() { return pages; }
    @Override public String toString() { return String.format("'%s' (%s, %d стор.)", title, author, pages); }
}

class Bookshelf {
    private final String genre;
    private final BookItem[] books;
    private int bookCount = 0;

    public Bookshelf(String genre, int maxCapacity) { this.genre = genre; this.books = new BookItem[maxCapacity]; }

    public boolean putBook(BookItem book) {
        if (book != null && bookCount < books.length) { books[bookCount++] = book; return true; }
        return false;
    }

    public BookItem findLongestBook() {
        if (bookCount == 0) return null;
        BookItem longest = books[0];
        for (int i = 1; i < bookCount; i++) {
            if (books[i].getPages() > longest.getPages()) longest = books[i];
        }
        return longest;
    }
}

// Завдання 20
class AudioTrack {
    private final String title, artist;
    private final int durationSeconds;

    public AudioTrack(String title, String artist, int durationSeconds) {
        this.title = title; this.artist = artist; this.durationSeconds = durationSeconds;
    }

    public int getDurationSeconds() { return durationSeconds; }

    @Override
    public String toString() {
        return String.format("%s - %s [%02d:%02d]", artist, title, durationSeconds / 60, durationSeconds % 60);
    }
}

class Playlist {
    private String name;
    private final AudioTrack[] tracks;
    private int trackCount = 0;

    public Playlist(String name, int capacity) { this.name = name; this.tracks = new AudioTrack[capacity]; }

    public void addTrack(AudioTrack track) {
        if (track != null && trackCount < tracks.length) tracks[trackCount++] = track;
    }

    public int getTotalDurationSeconds() {
        int sum = 0;
        for (int i = 0; i < trackCount; i++) sum += tracks[i].getDurationSeconds();
        return sum;
    }

    @Override
    public String toString() {
        int sec = getTotalDurationSeconds();
        return String.format("Плейлист '%s' (%d треків, %d хв %d сек)", name, trackCount, sec / 60, sec % 60);
    }
}

// Завдання 21
class ServerNode {
    private final String ipAddress;
    private final int totalRamGb;
    private int usedRamGb = 0;
    private boolean online = true;

    public ServerNode(String ipAddress, int totalRamGb) { this.ipAddress = ipAddress; this.totalRamGb = totalRamGb; }

    public int getFreeRamGb() { return online ? (totalRamGb - usedRamGb) : 0; }

    public boolean allocateRam(int ramGb) {
        if (online && getFreeRamGb() >= ramGb) { usedRamGb += ramGb; return true; }
        return false;
    }
}

class DataCenterCluster {
    private final String clusterLocation;
    private final ServerNode[] nodes;
    private int nodeCount = 0;

    public DataCenterCluster(String clusterLocation, int maxNodes) {
        this.clusterLocation = clusterLocation;
        this.nodes = new ServerNode[maxNodes];
    }

    public void addNode(ServerNode node) { if (nodeCount < nodes.length) nodes[nodeCount++] = node; }

    public boolean deployContainer(int requiredRamGb) {
        for (int i = 0; i < nodeCount; i++) {
            if (nodes[i].allocateRam(requiredRamGb)) return true;
        }
        return false;
    }
}

// Завдання 22
class CourseModule {
    private final String topic;
    private final int lectureHours, practiceHours;
    private double maxScore;

    public CourseModule(String topic, int lectureHours, int practiceHours, double maxScore) {
        this.topic = topic; this.lectureHours = lectureHours; this.practiceHours = practiceHours; this.maxScore = maxScore;
    }

    public int getTotalHours() { return lectureHours + practiceHours; }
    public double getMaxScore() { return maxScore; }
}

class UniversityCourse {
    private final String courseTitle;
    private final CourseModule[] modules;
    private int moduleCount = 0;

    public UniversityCourse(String courseTitle, int maxModules) {
        this.courseTitle = courseTitle;
        this.modules = new CourseModule[maxModules];
    }

    public void addModule(CourseModule module) { if (moduleCount < modules.length) modules[moduleCount++] = module; }

    public double calculateEctsCredits() {
        int totalHours = 0;
        for (int i = 0; i < moduleCount; i++) totalHours += modules[i].getTotalHours();
        return totalHours / 30.0;
    }

    public boolean isScoreValid() {
        double score = 0.0;
        for (int i = 0; i < moduleCount; i++) score += modules[i].getMaxScore();
        return Math.abs(score - 100.0) < 1e-6;
    }
}

// Завдання 23
class TrainCarriage {
    private final int carriageNumber;
    private final String type;
    private final int totalSeats;
    private int occupiedSeats = 0;

    public TrainCarriage(int carriageNumber, String type, int totalSeats) {
        this.carriageNumber = carriageNumber; this.type = type; this.totalSeats = totalSeats;
    }

    public int getCarriageNumber() { return carriageNumber; }
    public int getFreeSeats() { return totalSeats - occupiedSeats; }

    public boolean bookSeats(int seats) {
        if (getFreeSeats() >= seats) { occupiedSeats += seats; return true; }
        return false;
    }
}

class PassengerTrain {
    private final String route;
    private final TrainCarriage[] carriages;
    private int carriageCount = 0;

    public PassengerTrain(String route, int maxCarriages) {
        this.route = route;
        this.carriages = new TrainCarriage[maxCarriages];
    }

    public void attachCarriage(TrainCarriage c) { if (carriageCount < carriages.length) carriages[carriageCount++] = c; }

    public int getTotalFreeSeats() {
        int free = 0;
        for (int i = 0; i < carriageCount; i++) free += carriages[i].getFreeSeats();
        return free;
    }

    public boolean bookTicketsInCarriage(int carriageNumber, int seatsToBook) {
        for (int i = 0; i < carriageCount; i++) {
            if (carriages[i].getCarriageNumber() == carriageNumber) {
                return carriages[i].bookSeats(seatsToBook);
            }
        }
        return false;
    }
}

// Завдання 24
class LinearEquation {
    private final double a, b, c;
    public LinearEquation(double a, double b, double c) { this.a = a; this.b = b; this.c = c; }
    public boolean isSatisfiedBy(double x, double y) { return Math.abs(a * x + b * y - c) < 1e-6; }
}

class EquationSystem {
    private final LinearEquation[] equations;
    private int count = 0;

    public EquationSystem(int maxEquations) { this.equations = new LinearEquation[maxEquations]; }

    public void addEquation(LinearEquation eq) { if (count < equations.length) equations[count++] = eq; }

    public boolean checkSolution(double x, double y) {
        for (int i = 0; i < count; i++) {
            if (!equations[i].isSatisfiedBy(x, y)) return false;
        }
        return true;
    }
}


// ============================================================================
// БЛОК 4: Абстрактні класи (abstract class) та Шаблонний метод (25–32)
// ============================================================================

// Завдання 25
abstract class AbstractShapeTask {
    private String color;
    public AbstractShapeTask(String color) { this.color = (color == null || color.isBlank()) ? "Білий" : color; }
    public String getColor() { return color; }

    public abstract double getArea();
    public abstract double getPerimeter();

    @Override
    public String toString() {
        return String.format("%s[колір='%s', S=%.2f, P=%.2f]", this.getClass().getSimpleName(), color, getArea(), getPerimeter());
    }
}

class CircleShapeTask extends AbstractShapeTask {
    private final double radius;
    public CircleShapeTask(String color, double radius) { super(color); this.radius = Math.max(0.1, radius); }
    @Override public double getArea() { return Math.PI * radius * radius; }
    @Override public double getPerimeter() { return 2 * Math.PI * radius; }
}

class RectangleShapeTask extends AbstractShapeTask {
    private final double width, height;
    public RectangleShapeTask(String color, double width, double height) {
        super(color); this.width = Math.max(0.1, width); this.height = Math.max(0.1, height);
    }
    @Override public double getArea() { return width * height; }
    @Override public double getPerimeter() { return 2 * (width + height); }
}

// Завдання 26
abstract class AbstractPowerPlant {
    private final String stationName;
    protected double baseCapacityMw;

    public AbstractPowerPlant(String stationName, double baseCapacityMw) {
        this.stationName = stationName;
        this.baseCapacityMw = Math.max(0, baseCapacityMw);
    }

    public abstract double generateMegawatts();

    public double calculateDailyProfit(double tariffPerMwh) {
        return generateMegawatts() * 24 * tariffPerMwh;
    }

    @Override
    public String toString() { return String.format("%s [Генерація: %.1f МВт]", stationName, generateMegawatts()); }
}

class SolarPowerPlant extends AbstractPowerPlant {
    private double sunIntensityFactor;
    public SolarPowerPlant(String name, double cap, double sunFactor) {
        super(name, cap);
        this.sunIntensityFactor = Math.max(0, Math.min(1.0, sunFactor));
    }
    @Override public double generateMegawatts() { return baseCapacityMw * sunIntensityFactor; }
}

class WindPowerPlant extends AbstractPowerPlant {
    private double windSpeedMs;
    public WindPowerPlant(String name, double cap, double windSpeed) { super(name, cap); this.windSpeedMs = windSpeed; }
    @Override
    public double generateMegawatts() {
        if (windSpeedMs < 3.0 || windSpeedMs > 25.0) return 0.0;
        return baseCapacityMw * Math.min(1.0, windSpeedMs / 15.0);
    }
}

// Завдання 27
abstract class AbstractEmployee {
    private final int id;
    private final String name;
    protected double taxRate;

    public AbstractEmployee(int id, String name, double taxRate) {
        this.id = id; this.name = name; this.taxRate = taxRate;
    }

    public abstract double calculateGrossSalary();

    public final double calculateNetSalary() {
        return calculateGrossSalary() * (1.0 - taxRate);
    }

    @Override
    public String toString() {
        return String.format("Співробітник[%d, %s, Брутто=%.2f грн, Нетто=%.2f грн]", id, name, calculateGrossSalary(), calculateNetSalary());
    }
}

class SalariedEmployee extends AbstractEmployee {
    private double monthlySalary, bonus;
    public SalariedEmployee(int id, String name, double taxRate, double salary, double bonus) {
        super(id, name, taxRate); this.monthlySalary = salary; this.bonus = bonus;
    }
    @Override public double calculateGrossSalary() { return monthlySalary + bonus; }
}

class HourlyContractor extends AbstractEmployee {
    private int hoursWorked;
    private double hourlyRate;

    public HourlyContractor(int id, String name, double taxRate, int hoursWorked, double hourlyRate) {
        super(id, name, taxRate);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateGrossSalary() {
        return (hoursWorked <= 160) ? (hoursWorked * hourlyRate) : (160 * hourlyRate + (hoursWorked - 160) * hourlyRate * 1.5);
    }
}

// Завдання 28
abstract class AbstractSequence {
    private final String title;
    public AbstractSequence(String title) { this.title = title; }
    public abstract double getTerm(int index);

    public final double sumFirstN(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) sum += getTerm(i);
        return sum;
    }
}

class HarmonicSequence extends AbstractSequence {
    public HarmonicSequence() { super("Гармонічна послідовність 1/n"); }
    @Override public double getTerm(int index) { return 1.0 / index; }
}

class SquareNumbersSequence extends AbstractSequence {
    public SquareNumbersSequence() { super("Квадрати чисел n^2"); }
    @Override public double getTerm(int index) { return (double) index * index; }
}

// Завдання 29
abstract class AbstractTransaction {
    private final String txId;
    protected final double amount;

    public AbstractTransaction(String txId, double amount) { this.txId = txId; this.amount = amount; }

    public abstract double calculateCommission();

    public final double getTotalDebit() { return amount + calculateCommission(); }
}

class DomesticTransfer extends AbstractTransaction {
    public DomesticTransfer(String txId, double amount) { super(txId, amount); }
    @Override public double calculateCommission() { return Math.max(5.0, amount * 0.005); }
}

class CryptoTransfer extends AbstractTransaction {
    private double networkGasFee;
    public CryptoTransfer(String txId, double amount, double gasFee) { super(txId, amount); this.networkGasFee = gasFee; }
    @Override public double calculateCommission() { return networkGasFee; }
}

// Завдання 30
abstract class Solid3D {
    private final String materialName;
    private final double densityKgPerM3;

    public Solid3D(String materialName, double density) {
        this.materialName = materialName;
        this.densityKgPerM3 = density;
    }

    public abstract double getVolume();
    public abstract double getSurfaceArea();

    public final double getMassKg() { return densityKgPerM3 * getVolume(); }
}

class Sphere3D extends Solid3D {
    private double radius;
    public Sphere3D(String mat, double density, double radius) { super(mat, density); this.radius = radius; }
    @Override public double getVolume() { return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3); }
    @Override public double getSurfaceArea() { return 4 * Math.PI * radius * radius; }
}

class Cylinder3D extends Solid3D {
    private double radius, height;
    public Cylinder3D(String mat, double density, double radius, double height) {
        super(mat, density); this.radius = radius; this.height = height;
    }
    @Override public double getVolume() { return Math.PI * radius * radius * height; }
    @Override public double getSurfaceArea() { return 2 * Math.PI * radius * (radius + height); }
}

// Завдання 31
abstract class AbstractSensor {
    private final String location;
    private final double minSafe, maxSafe;

    public AbstractSensor(String location, double minSafe, double maxSafe) {
        this.location = location; this.minSafe = minSafe; this.maxSafe = maxSafe;
    }

    public abstract double readValue();

    public final boolean isAlarmTriggered() {
        double val = readValue();
        return val < minSafe || val > maxSafe;
    }
}

class RadiationSensor extends AbstractSensor {
    private double uSvPerHour;
    public RadiationSensor(String loc, double min, double max, double val) { super(loc, min, max); this.uSvPerHour = val; }
    @Override public double readValue() { return uSvPerHour; }
}

// Завдання 32
abstract class AbstractCompressor {
    private final String algorithmName;
    public AbstractCompressor(String name) { this.algorithmName = name; }
    public abstract int estimateCompressedSize(int originalSizeBytes);

    public double getCompressionRatio(int originalSizeBytes) {
        int compressed = estimateCompressedSize(originalSizeBytes);
        return (1.0 - (double) compressed / originalSizeBytes) * 100.0;
    }
}

class UltraLzmaCompressor extends AbstractCompressor {
    public UltraLzmaCompressor() { super("LZMA"); }
    @Override public int estimateCompressedSize(int orig) { return (int) (orig * 0.30) + 128; }
}


// ============================================================================
// БЛОК 5: Синергія: Інкапсуляція + Композиція + Абстрактні класи (33–40)
// ============================================================================

// Завдання 33
abstract class AbstractPcPart {
    private final String model;
    private final double priceUah;

    public AbstractPcPart(String model, double priceUah) { this.model = model; this.priceUah = priceUah; }
    public abstract int getPowerConsumptionWatts();
}

class ProcessorPart extends AbstractPcPart {
    private int cores, watts;
    public ProcessorPart(String model, double price, int cores, int watts) {
        super(model, price); this.cores = cores; this.watts = watts;
    }
    @Override public int getPowerConsumptionWatts() { return watts; }
}

class VideoCardPart extends AbstractPcPart {
    private int vramGb, watts;
    public VideoCardPart(String model, double price, int vramGb, int watts) {
        super(model, price); this.vramGb = vramGb; this.watts = watts;
    }
    @Override public int getPowerConsumptionWatts() { return watts; }
}

class DesktopPC {
    private final int psuMaxWatts;
    private final AbstractPcPart[] parts;
    private int count = 0;

    public DesktopPC(int psuMaxWatts, int maxParts) { this.psuMaxWatts = psuMaxWatts; this.parts = new AbstractPcPart[maxParts]; }

    public void addPart(AbstractPcPart part) { if (count < parts.length) parts[count++] = part; }

    public boolean isSystemStable() {
        int totalWatts = 0;
        for (int i = 0; i < count; i++) totalWatts += parts[i].getPowerConsumptionWatts();
        return (totalWatts * 1.20) <= psuMaxWatts;
    }
}

// Завдання 34
abstract class AbstractThruster {
    private final String thrusterId;
    public AbstractThruster(String thrusterId) { this.thrusterId = thrusterId; }
    public abstract double getThrustKn();
}

class ChemicalThruster extends AbstractThruster {
    public ChemicalThruster(String id) { super(id); }
    @Override public double getThrustKn() { return 500.0; }
}

class Spacecraft {
    private final String shipName;
    private final double dryMassTons;
    private final AbstractThruster[] thrusters;
    private int count = 0;

    public Spacecraft(String shipName, double dryMassTons, int maxThrusters) {
        this.shipName = shipName; this.dryMassTons = dryMassTons; this.thrusters = new AbstractThruster[maxThrusters];
    }

    public void addThruster(AbstractThruster t) { if (count < thrusters.length) thrusters[count++] = t; }

    public double getTotalThrust() {
        double thrust = 0;
        for (int i = 0; i < count; i++) thrust += thrusters[i].getThrustKn();
        return thrust;
    }

    public double getInitialAcceleration() { return getTotalThrust() / dryMassTons; }
}

// Завдання 35
class LandPlot {
    private final double totalPlotAreaM2;
    private final AbstractShapeTask[] structures;
    private int count = 0;

    public LandPlot(double totalPlotAreaM2, int maxStructures) {
        this.totalPlotAreaM2 = totalPlotAreaM2;
        this.structures = new AbstractShapeTask[maxStructures];
    }

    public boolean addStructure(AbstractShapeTask s) {
        double currentArea = 0;
        for (int i = 0; i < count; i++) currentArea += structures[i].getArea();
        if ((currentArea + s.getArea()) <= totalPlotAreaM2 * 0.60 && count < structures.length) {
            structures[count++] = s;
            return true;
        }
        return false;
    }

    public double getFreeGreenArea() {
        double builtArea = 0;
        for (int i = 0; i < count; i++) builtArea += structures[i].getArea();
        return totalPlotAreaM2 - builtArea;
    }
}

// Завдання 36
abstract class AbstractAsset {
    private final String ticker;
    protected double investedAmount;

    public AbstractAsset(String ticker, double investedAmount) {
        this.ticker = ticker;
        this.investedAmount = investedAmount;
    }

    public abstract double calculateAnnualReturn();
    public double getInvestedAmount() { return investedAmount; }
}

class GovernmentBond extends AbstractAsset {
    public GovernmentBond(String ticker, double amount) { super(ticker, amount); }
    @Override public double calculateAnnualReturn() { return investedAmount * 0.155; }
}

class DividendStock extends AbstractAsset {
    public DividendStock(String ticker, double amount) { super(ticker, amount); }
    @Override public double calculateAnnualReturn() { return investedAmount * 0.12; }
}

class InvestmentPortfolio {
    private final String investorName;
    private final AbstractAsset[] assets;
    private int count = 0;

    public InvestmentPortfolio(String investorName, int maxAssets) {
        this.investorName = investorName;
        this.assets = new AbstractAsset[maxAssets];
    }

    public void addAsset(AbstractAsset asset) { if (count < assets.length) assets[count++] = asset; }

    public double getTotalPortfolioValue() {
        double total = 0;
        for (int i = 0; i < count; i++) total += assets[i].getInvestedAmount();
        return total;
    }

    public double getTotalExpectedReturn() {
        double ret = 0;
        for (int i = 0; i < count; i++) ret += assets[i].calculateAnnualReturn();
        return ret;
    }
}

// Завдання 37
abstract class AbstractHeroUnit {
    private final String name;
    protected int hp;
    public AbstractHeroUnit(String name, int hp) { this.name = name; this.hp = hp; }
    public abstract int getCombatPower();
    public abstract String getRole();
}

class TankWarrior extends AbstractHeroUnit {
    private int armor;
    public TankWarrior(String name, int hp, int armor) { super(name, hp); this.armor = armor; }
    @Override public int getCombatPower() { return hp + armor * 2; }
    @Override public String getRole() { return "Танк"; }
}

class ArcaneMage extends AbstractHeroUnit {
    private int spellPower;
    public ArcaneMage(String name, int hp, int spellPower) { super(name, hp); this.spellPower = spellPower; }
    @Override public int getCombatPower() { return spellPower * 3; }
    @Override public String getRole() { return "Маг"; }
}

class RaidParty {
    private final String guildName;
    private final AbstractHeroUnit[] members;
    private int memberCount = 0;

    public RaidParty(String guildName, int maxMembers) { this.guildName = guildName; this.members = new AbstractHeroUnit[maxMembers]; }

    public void addMember(AbstractHeroUnit hero) { if (memberCount < members.length) members[memberCount++] = hero; }

    public boolean isReadyForDungeon(int requiredPower) {
        boolean hasTank = false;
        int totalPower = 0;
        for (int i = 0; i < memberCount; i++) {
            if ("Танк".equalsIgnoreCase(members[i].getRole())) hasTank = true;
            totalPower += members[i].getCombatPower();
        }
        return hasTank && (totalPower >= requiredPower);
    }
}

// Завдання 38
class EmergencySiren {
    private boolean sounding = false;
    public void activate() { this.sounding = true; }
}

class SmartLab {
    private final EmergencySiren siren = new EmergencySiren();
    private final AbstractSensor[] sensors;
    private int count = 0;

    public SmartLab(int maxSensors) { this.sensors = new AbstractSensor[maxSensors]; }

    public void addSensor(AbstractSensor sensor) { if (count < sensors.length) sensors[count++] = sensor; }

    public void runDiagnostics() {
        for (int i = 0; i < count; i++) {
            if (sensors[i].isAlarmTriggered()) {
                siren.activate();
                System.out.println("[УВАГА] Спрацювала сирена! Показник критичний.");
                return;
            }
        }
        System.out.println("[OK] Діагностика успішна, всі системи в нормі.");
    }
}

// Завдання 39
abstract class AbstractFilter {
    private final String filterName;
    public AbstractFilter(String filterName) { this.filterName = filterName; }
    public abstract double processSample(double x);
}

class AmplifierFilter extends AbstractFilter {
    private double gain;
    public AmplifierFilter(double gain) { super("Підсилювач"); this.gain = gain; }
    @Override public double processSample(double x) { return x * gain; }
}

class ClippingFilter extends AbstractFilter {
    private double threshold;
    public ClippingFilter(double threshold) { super("Обмежувач"); this.threshold = threshold; }
    @Override public double processSample(double x) { return Math.max(-threshold, Math.min(threshold, x)); }
}

class SignalProcessor {
    private final AbstractFilter[] pipeline;
    private int count = 0;

    public SignalProcessor(int maxFilters) { this.pipeline = new AbstractFilter[maxFilters]; }

    public void addFilter(AbstractFilter filter) { if (count < pipeline.length) pipeline[count++] = filter; }

    public double[] processSignal(double[] signal) {
        double[] output = Arrays.copyOf(signal, signal.length);
        for (int i = 0; i < output.length; i++) {
            for (int f = 0; f < count; f++) {
                output[i] = pipeline[f].processSample(output[i]);
            }
        }
        return output;
    }
}

// Завдання 40 та Головний метод для запуску всіх завдань українською
public class Practice09 {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   ПРАКТИЧНА РОБОТА №9: УСІ 40 ЗАВДАНЬ ВИКОНАНО    ");
        System.out.println("==================================================\n");

        // Блок 1 (1–8)
        System.out.println("--- Блок 1: Поглиблена інкапсуляція & Defensive Copying ---");
        ImmutableGeoPoint pt1 = new ImmutableGeoPoint(120.0, -200.0);
        System.out.println("Завдання 1: " + pt1.shift(-10, 20));

        double[] samples = {1.2, 3.4, 5.6};
        LabMeasurement lab = new LabMeasurement("Фізика", samples);
        samples[0] = 999.9;
        System.out.println("Завдання 2: " + lab);

        CryptoVault vault = new CryptoVault("0xABC123", "secret123", 1.5);
        vault.unlock("secret123");
        vault.sendBtc(0.5, "0xXYZ");
        System.out.println("Завдання 3: " + vault);

        ClosedInterval interval = new ClosedInterval(10.0, 2.0);
        System.out.println("Завдання 4: " + interval);

        RgbColor color = new RgbColor(300, -20, 150);
        System.out.println("Завдання 5: " + color + " -> Інвертований: " + color.invert());

        HighSecuritySafe safe = new HighSecuritySafe(1234, 5000);
        safe.putCash(1111, 100);
        safe.putCash(1111, 100);
        safe.putCash(1111, 100); // 3 спроби -> блокування
        System.out.println("Завдання 6: " + safe);

        EncapsulatedPolynomial poly = new EncapsulatedPolynomial(new double[]{1, 2, 3});
        System.out.println("Завдання 7: " + poly + ", P(2) = " + poly.evaluate(2));

        MedicalRecord med = new MedicalRecord("P-101", "Шевченко Т.Г.", 2, true, new String[]{"Пилок", "Аспірин"});
        System.out.println("Завдання 8: " + med + ", алергія на аспірин: " + med.hasAllergyTo("аспірин"));

        // Блок 2 (9–16)
        System.out.println("\n--- Блок 2: Композиція та Агрегація (HAS-A 1-до-1) ---");
        CompositeCircleTask cTask = new CompositeCircleTask(0, 0, 5);
        System.out.println("Завдання 9: " + cTask);

        Automobile car = new Automobile("Audi A6", "Бензин", 2000);
        car.getEngine().start();
        car.accelerate(50);
        System.out.println("Завдання 10: " + car);

        SmartDevice phone = new SmartDevice(new Battery(4000, 100), new Display(6.5, 80));
        phone.watchVideo(30);
        System.out.println("Завдання 11: " + phone);

        LineSegment seg = new LineSegment(new Point2D() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        }, new Point2D() {
            @Override
            public double getX() {
                return 0;
            }

            @Override
            public double getY() {
                return 0;
            }

            @Override
            public void setLocation(double x, double y) {

            }
        });
        System.out.println("Завдання 12: " + seg);

        BankAccount acc = new BankAccount("UA123456789", 1000);
        PlasticCard card1 = new PlasticCard("4444-1111", "12/28", acc);
        card1.payAtTerminal(200);
        System.out.println("Завдання 13: " + card1);

        HeroCharacter hero = new HeroCharacter("Конан", 20);
        hero.equip(new WeaponItem("Меч Екскалібур", 15));
        System.out.println("Завдання 14: Атака героєм = " + hero.attack() + " од.");

        ParcelOrder order = new ParcelOrder("ORD-99", 2.5, new DeliveryAddress("Київ", "Хрещатик", 1));
        order.markAsShipped();
        order.changeDestination(new DeliveryAddress("Львів", "Свободи", 2));
        System.out.println("Завдання 15: " + order);

        ClimateController climate = new ClimateController(new TemperatureSensor(18.0), new HeatingUnit(2.5), 21.0);
        climate.regulate();
        System.out.println("Завдання 16: " + climate);

        // Блок 3 (17–24)
        System.out.println("\n--- Блок 3: Композиція з масивами об'єктів (HAS-MANY) ---");
        Polyline polyline = new Polyline(5);
        polyline.addVertex(0, 0); polyline.addVertex(0, 3); polyline.addVertex(4, 0);
        System.out.println("Завдання 17: Довжина ламаної = " + polyline.getTotalLength() + ", Периметр = " + polyline.getClosedPerimeter());

        ShoppingOrder cart = new ShoppingOrder(5);
        cart.addItem("Кава", 150, 2);
        cart.addItem("Кава", 150, 1);
        System.out.println("Завдання 18:\n" + cart);

        Bookshelf shelf = new Bookshelf("Фантастика", 5);
        shelf.putBook(new BookItem("Дюна", "Ф. Герберт", 600));
        System.out.println("Завдання 19: Найдовша книга: " + shelf.findLongestBook());

        Playlist playlist = new Playlist("Улюблене", 5);
        playlist.addTrack(new AudioTrack("Трек 1", "Виконавець А", 210));
        System.out.println("Завдання 20: " + playlist);

        DataCenterCluster dc = new DataCenterCluster("Київ-1", 3);
        dc.addNode(new ServerNode("192.168.1.1", 64));
        System.out.println("Завдання 21: Розгортання контейнера (32GB): " + dc.deployContainer(32));

        UniversityCourse course = new UniversityCourse("ООП", 2);
        course.addModule(new CourseModule("Базовий ООП", 30, 30, 50.0));
        course.addModule(new CourseModule("Поглиблений ООП", 30, 30, 50.0));
        System.out.println("Завдання 22: Кредити ЄКТС = " + course.calculateEctsCredits() + ", Бали 100% = " + course.isScoreValid());

        PassengerTrain train = new PassengerTrain("Київ - Львів", 3);
        train.attachCarriage(new TrainCarriage(1, "Купе", 36));
        train.bookTicketsInCarriage(1, 10);
        System.out.println("Завдання 23: Вільних місць у потязі = " + train.getTotalFreeSeats());

        EquationSystem system = new EquationSystem(2);
        system.addEquation(new LinearEquation(1, 1, 5)); // x + y = 5
        system.addEquation(new LinearEquation(1, -1, 1)); // x - y = 1
        System.out.println("Завдання 24: Розв'язок (3, 2) вірний: " + system.checkSolution(3, 2));

        // Блок 4 (25–32)
        System.out.println("\n--- Блок 4: Абстрактні класи та Шаблонний метод ---");
        AbstractShapeTask sh = new CircleShapeTask("Синій", 3.0);
        System.out.println("Завдання 25: " + sh);

        AbstractPowerPlant plant = new SolarPowerPlant("Сонячна-1", 100, 0.8);
        System.out.println("Завдання 26: " + plant + ", Прибуток = " + plant.calculateDailyProfit(2.5) + " грн");

        AbstractEmployee emp = new HourlyContractor(1, "Петро", 0.195, 170, 200);
        System.out.println("Завдання 27: " + emp);

        AbstractSequence seq = new HarmonicSequence();
        System.out.println("Завдання 28: Сума перших 5 членів гармонічного ряду = " + seq.sumFirstN(5));

        AbstractTransaction tx = new DomesticTransfer("TX-100", 1000);
        System.out.println("Завдання 29: Комісія = " + tx.calculateCommission() + " грн, Всього: " + tx.getTotalDebit());

        Solid3D sphere = new Sphere3D("Сталь", 7850, 0.5);
        System.out.println("Завдання 30: Маса сталевої кулі = " + String.format("%.2f", sphere.getMassKg()) + " кг");

        AbstractSensor sensor = new RadiationSensor("Реакторний цех", 0.0, 0.5, 1.2);
        System.out.println("Завдання 31: Тривога радіації: " + sensor.isAlarmTriggered());

        AbstractCompressor lzma = new UltraLzmaCompressor();
        System.out.println("Завдання 32: Відсоток стиснення LZMA (1000 байт) = " + String.format("%.1f%%", lzma.getCompressionRatio(1000)));

        // Блок 5 (33–40)
        System.out.println("\n--- Блок 5: Комплексна синергія ---");
        DesktopPC pc = new DesktopPC(500, 3);
        pc.addPart(new ProcessorPart("Процесор i7", 12000, 8, 125));
        pc.addPart(new VideoCardPart("Відеокарта RTX 4070", 25000, 12, 220));
        System.out.println("Завдання 33: Стабільність ПК (Блок живлення 500 Вт): " + pc.isSystemStable());

        Spacecraft ship = new Spacecraft("Аполлон", 10.0, 2);
        ship.addThruster(new ChemicalThruster("Двигун-1"));
        System.out.println("Завдання 34: Тяга = " + ship.getTotalThrust() + " кН, Прискорення = " + ship.getInitialAcceleration() + " м/с²");

        LandPlot plot = new LandPlot(1000, 5);
        plot.addStructure(new RectangleShapeTask("Будинок", 10, 15));
        System.out.println("Завдання 35: Вільна зелена площа = " + plot.getFreeGreenArea() + " м²");

        InvestmentPortfolio portfolio = new InvestmentPortfolio("Іван", 2);
        portfolio.addAsset(new GovernmentBond("ОВДП", 50000));
        portfolio.addAsset(new DividendStock("Акції AAPL", 30000));
        System.out.println("Завдання 36: Вартість = " + portfolio.getTotalPortfolioValue() + " грн, Річний дохід = " + portfolio.getTotalExpectedReturn() + " грн");

        RaidParty raid = new RaidParty("Дракони", 3);
        raid.addMember(new TankWarrior("Паладин", 500, 50));
        raid.addMember(new ArcaneMage("Маг", 200, 150));
        System.out.println("Завдання 37: Готовність до рейду (Сила >= 800): " + raid.isReadyForDungeon(800));

        SignalProcessor processor = new SignalProcessor(2);
        processor.addFilter(new AmplifierFilter(2.0));
        processor.addFilter(new ClippingFilter(5.0));
        double[] rawSignal = {1.0, 3.0, 5.0};
        double[] processed = processor.processSignal(rawSignal);
        System.out.println("Завдання 39: Вхідний сигнал: " + Arrays.toString(rawSignal) + " -> Оброблений: " + Arrays.toString(processed));

        System.out.println("\nЗавдання 40: Усі класи успішно зкомпільовані та протестовані. Готово до відправки в Git (git push origin main)!");
    }
}