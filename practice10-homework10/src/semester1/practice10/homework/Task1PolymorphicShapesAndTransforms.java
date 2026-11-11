package semester1.practice10.homework;

// --- 1. ІНТЕРФЕЙСИ ТРАНСФОРМАЦІЙ (CAN-DO) ---
interface Scalable2D {
    double MIN_SCALE_FACTOR = 0.1;
    void scale(double factor);
}

interface Rotatable2D {
    void rotate(double deltaDegrees);
    double getRotationAngle();
}

// --- 2. БАЗОВИЙ АБСТРАКТНИЙ КЛАС (IS-A) ---
abstract class AbstractShape2D {
    private final String name;
    private double centerX;
    private double centerY;

    public AbstractShape2D(String name, double centerX, double centerY) {
        this.name = name;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    // Захищений final-метод розрахунку ізопериметричного коефіцієнта компактності
    public final double getIsoperimetricQuotient() {
        double area = getArea();
        double perimeter = getPerimeter();
        if (perimeter <= 0) return 0.0;
        return (4.0 * Math.PI * area) / (perimeter * perimeter);
    }

    public String getName() { return name; }
    public double getCenterX() { return centerX; }
    public double getCenterY() { return centerY; }

    @Override
    public String toString() {
        return String.format("%s['%s', center=(%.1f; %.1f) | S=%.2f, P=%.2f, Q=%.4f]",
                getClass().getSimpleName(), name, centerX, centerY, getArea(), getPerimeter(), getIsoperimetricQuotient());
    }
}

// --- 3. КОНКРЕТНІ КЛАСИ-ФІГУРИ ---
class CircleShape extends AbstractShape2D implements Scalable2D {
    private double radius;

    public CircleShape(String name, double cx, double cy, double radius) {
        super(name, cx, cy);
        this.radius = Math.max(0.001, radius);
    }

    @Override public double getArea() { return Math.PI * radius * radius; }
    @Override public double getPerimeter() { return 2 * Math.PI * radius; }

    @Override
    public void scale(double factor) {
        double f = Math.max(MIN_SCALE_FACTOR, factor);
        this.radius *= f;
        System.out.printf("   [Scalable2D] '%s' масштабовано в %.1f разів (новий r=%.2f)\n", getName(), f, radius);
    }
}

class RectangleShape extends AbstractShape2D implements Scalable2D, Rotatable2D {
    private double width;
    private double height;
    private double rotationAngle = 0.0;

    public RectangleShape(String name, double cx, double cy, double width, double height) {
        super(name, cx, cy);
        this.width = Math.max(0.001, width);
        this.height = Math.max(0.001, height);
    }

    @Override public double getArea() { return width * height; }
    @Override public double getPerimeter() { return 2 * (width + height); }

    @Override
    public void scale(double factor) {
        double f = Math.max(MIN_SCALE_FACTOR, factor);
        this.width *= f;
        this.height *= f;
        System.out.printf("   [Scalable2D] '%s' масштабовано в %.1f разів (%.2fx%.2f)\n", getName(), f, width, height);
    }

    @Override
    public void rotate(double deltaDegrees) {
        this.rotationAngle = (this.rotationAngle + deltaDegrees) % 360.0;
        System.out.printf("   [Rotatable2D] '%s' повернуто на символічний кут %+.1f° (поточний: %.1f°)\n", getName(), deltaDegrees, rotationAngle);
    }

    @Override public double getRotationAngle() { return rotationAngle; }

    @Override
    public String toString() {
        return String.format("%s['%s', center=(%.1f; %.1f), %.2fx%.2f, angle=%.1f° | S=%.2f, P=%.2f, Q=%.4f]",
                getClass().getSimpleName(), getName(), getCenterX(), getCenterY(), width, height, rotationAngle, getArea(), getPerimeter(), getIsoperimetricQuotient());
    }
}

// Фінальний клас — не має нащадків і не підтримує інтерфейси трансформацій
final class ImmutableReferenceSquare extends AbstractShape2D {
    private final double side;

    public ImmutableReferenceSquare(String name, double cx, double cy, double side) {
        super(name, cx, cy);
        this.side = Math.max(0.001, side);
    }

    @Override public double getArea() { return side * side; }
    @Override public double getPerimeter() { return 4 * side; }

    @Override
    public String toString() {
        return String.format("%s['%s', center=(%.1f; %.1f), side=%.2f | S=%.2f, P=%.2f, Q=%.4f]",
                getClass().getSimpleName(), getName(), getCenterX(), getCenterY(), side, getArea(), getPerimeter(), getIsoperimetricQuotient());
    }
}

// --- 4. КЛАС ЗАПУСКУ ЗАВДАННЯ 1 ---
public class Task1PolymorphicShapesAndTransforms {
    public static double calculateTotalArea(AbstractShape2D[] shapes) {
        double total = 0;
        for (AbstractShape2D s : shapes) {
            if (s != null) total += s.getArea();
        }
        return total;
    }

    public static void transformScene(AbstractShape2D[] shapes, double scaleFactor, double rotateDeg) {
        System.out.println("\n-> Виконуємо пакетну трансформацію (scale x2.0, rotate +45.0°) через instanceof...");
        for (AbstractShape2D s : shapes) {
            if (s instanceof Scalable2D sc) {
                sc.scale(scaleFactor);
            }
            if (s instanceof Rotatable2D rt) {
                rt.rotate(rotateDeg);
            }
            if (!(s instanceof Scalable2D) && !(s instanceof Rotatable2D)) {
                System.out.printf("   [Info] '%s' (final class) пропущено — не підтримує трансформації.\n", s.getName());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("================ ПОЛІМОРФНА ГЕОМЕТРІЯ ТА ІНТЕРФЕЙСИ ТРАНСФОРМАЦІЙ ================");

        AbstractShape2D[] scene = {
                new CircleShape("Коло-Лінза", 0.0, 0.0, 5.0),
                new RectangleShape("Рамка-А", 2.0, 3.0, 4.0, 6.0),
                new ImmutableReferenceSquare("Еталон-Квадрат", 10.0, 10.0, 5.0)
        };

        System.out.println("[СТАН СЦЕНИ ДО ТРАНСФОРМАЦІЇ]:");
        for (int i = 0; i < scene.length; i++) {
            System.out.printf("  %d. %s\n", (i + 1), scene[i]);
        }
        System.out.printf("Сумарна площа сцени: %.2f кв.од.\n", calculateTotalArea(scene));

        transformScene(scene, 2.0, 45.0);

        System.out.println("\n[СТАН СЦЕНИ ПІСЛЯ ТРАНСФОРМАЦІЇ]:");
        for (int i = 0; i < scene.length; i++) {
            System.out.printf("  %d. %s\n", (i + 1), scene[i]);
        }
        System.out.printf("Нова сумарна площа сцени: %.2f кв.од.\n", calculateTotalArea(scene));
        System.out.println("==================================================================================");
    }
}