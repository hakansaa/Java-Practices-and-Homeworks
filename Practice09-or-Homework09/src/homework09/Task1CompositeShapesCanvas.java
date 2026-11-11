package homework09;

public class Task1CompositeShapesCanvas {
    public static void main(String[] args) {
        Point2D original = new Point2D(0, 0);
        Circle2D circle = new Circle2D("Коло-А", original, 5);
        original.translate(100, 100);
        Point2D returned = circle.getAnchorPoint();
        returned.translate(50, 50);
        GeometryCanvas canvas = new GeometryCanvas("Інженерне креслення №1", 5);
        canvas.addShape(circle);
        canvas.addShape(new Rectangle2D("Плита-Б", new Point2D(10, 5), 4, 6));
        canvas.addShape(new RightTriangle2D("Клин-В", new Point2D(-3, 2), 3, 4));
        System.out.println(canvas);
        System.out.printf("Сумарна площа: %.2f%n", canvas.getTotalArea());
        System.out.printf("Сумарний периметр: %.2f%n", canvas.getTotalPerimeter());
        AbstractShape2D largest = canvas.findLargestShape();
        System.out.printf("Найбільша фігура: %s (S = %.2f)%n", largest.getName(), largest.getArea());
        canvas.moveAllShapes(5, -2);
        System.out.println("Новий центр кола: " + circle.getAnchorPoint());
        System.out.println("Захисне копіювання працює: зовнішні зміни не вплинули на центр кола.");
    }
}

class Point2D {
    private double x, y;
    public Point2D(double x, double y) { this.x = x; this.y = y; }
    public Point2D(Point2D other) {
        if (other == null) throw new IllegalArgumentException("Точка не може бути null");
        this.x = other.x; this.y = other.y;
    }
    public double getX() { return x; }
    public double getY() { return y; }
    public void translate(double dx, double dy) { x += dx; y += dy; }
    public double distanceTo(Point2D other) {
        if (other == null) throw new IllegalArgumentException("Точка не може бути null");
        return Math.hypot(x - other.x, y - other.y);
    }
    @Override public String toString() { return String.format("(%.2f; %.2f)", x, y); }
}

abstract class AbstractShape2D {
    private final String name;
    private final Point2D anchorPoint;
    public AbstractShape2D(String name, Point2D anchorPoint) {
        this.name = name;
        this.anchorPoint = anchorPoint == null ? new Point2D(0, 0) : new Point2D(anchorPoint);
    }
    public String getName() { return name; }
    public Point2D getAnchorPoint() { return new Point2D(anchorPoint); }
    public void moveBy(double dx, double dy) { anchorPoint.translate(dx, dy); }
    public abstract double getArea();
    public abstract double getPerimeter();
    @Override public String toString() {
        return String.format("%s[name='%s', center=%s | S=%.2f, P=%.2f]",
                getClass().getSimpleName(), name, anchorPoint, getArea(), getPerimeter());
    }
    protected static double positive(double value) {
        if (!Double.isFinite(value) || value <= 0) throw new IllegalArgumentException("Розмір має бути додатним");
        return value;
    }
}

class Circle2D extends AbstractShape2D {
    private final double radius;
    public Circle2D(String name, Point2D center, double radius) {
        super(name, center); this.radius = positive(radius);
    }
    @Override public double getArea() { return Math.PI * radius * radius; }
    @Override public double getPerimeter() { return 2 * Math.PI * radius; }
    @Override public String toString() {
        return String.format("Circle2D[name='%s', center=%s, r=%.2f | S=%.2f, P=%.2f]",
                getName(), getAnchorPoint(), radius, getArea(), getPerimeter());
    }
}

class Rectangle2D extends AbstractShape2D {
    private final double width, height;
    public Rectangle2D(String name, Point2D center, double width, double height) {
        super(name, center); this.width = positive(width); this.height = positive(height);
    }
    @Override public double getArea() { return width * height; }
    @Override public double getPerimeter() { return 2 * (width + height); }
    @Override public String toString() {
        return String.format("Rectangle2D[name='%s', center=%s, %.2fx%.2f | S=%.2f, P=%.2f]",
                getName(), getAnchorPoint(), width, height, getArea(), getPerimeter());
    }
}

class RightTriangle2D extends AbstractShape2D {
    private final double legA, legB;
    public RightTriangle2D(String name, Point2D center, double legA, double legB) {
        super(name, center); this.legA = positive(legA); this.legB = positive(legB);
    }
    public double getHypotenuse() { return Math.hypot(legA, legB); }
    @Override public double getArea() { return legA * legB / 2; }
    @Override public double getPerimeter() { return legA + legB + getHypotenuse(); }
    @Override public String toString() {
        return String.format("RightTriangle2D[name='%s', center=%s, a=%.2f, b=%.2f, c=%.2f | S=%.2f, P=%.2f]",
                getName(), getAnchorPoint(), legA, legB, getHypotenuse(), getArea(), getPerimeter());
    }
}

class GeometryCanvas {
    private final String title;
    private final AbstractShape2D[] shapes;
    private int count;
    public GeometryCanvas(String title, int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Місткість має бути додатною");
        this.title = title; this.shapes = new AbstractShape2D[capacity];
    }
    public boolean addShape(AbstractShape2D shape) {
        if (shape == null || count >= shapes.length) return false;
        shapes[count++] = shape;
        return true;
    }
    public double getTotalArea() {
        double total = 0;
        for (int i = 0; i < count; i++) total += shapes[i].getArea();
        return total;
    }
    public double getTotalPerimeter() {
        double total = 0;
        for (int i = 0; i < count; i++) total += shapes[i].getPerimeter();
        return total;
    }
    public AbstractShape2D findLargestShape() {
        if (count == 0) return null;
        AbstractShape2D largest = shapes[0];
        for (int i = 1; i < count; i++)
            if (shapes[i].getArea() > largest.getArea()) largest = shapes[i];
        return largest;
    }
    public void moveAllShapes(double dx, double dy) {
        for (int i = 0; i < count; i++) shapes[i].moveBy(dx, dy);
    }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Полотно: '%s' (Фігур: %d/%d)%n", title, count, shapes.length));
        for (int i = 0; i < count; i++) sb.append("  ").append(i + 1).append(". ").append(shapes[i]).append("\n");
        return sb.toString();
    }
}
