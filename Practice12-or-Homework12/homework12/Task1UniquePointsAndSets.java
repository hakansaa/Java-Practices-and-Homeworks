package homework12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

class Point2D {
    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double distanceFromOrigin() {
        return Math.hypot(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point2D point2D)) return false;
        return x == point2D.x && y == point2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "; " + y + ")";
    }
}

class PointSetUtils {
    public static HashSet<Point2D> union(HashSet<Point2D> a, HashSet<Point2D> b) {
        HashSet<Point2D> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    public static HashSet<Point2D> intersection(HashSet<Point2D> a, HashSet<Point2D> b) {
        HashSet<Point2D> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    public static HashSet<Point2D> difference(HashSet<Point2D> a, HashSet<Point2D> b) {
        HashSet<Point2D> result = new HashSet<>(a);
        result.removeAll(b);
        return result;
    }

    public static int removeFarPoints(HashSet<Point2D> points, double maxRadius) {
        int removed = 0;
        Iterator<Point2D> it = points.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            if (p.distanceFromOrigin() > maxRadius) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }
}

public class Task1UniquePointsAndSets {
    public static void main(String[] args) {
        ArrayList<Point2D> rawSensorA = new ArrayList<>(List.of(
                new Point2D(0, 0),
                new Point2D(3, 4),
                new Point2D(1, 2),
                new Point2D(3, 4),
                new Point2D(6, 8),
                new Point2D(5, 0),
                new Point2D(1, 2)
        ));

        ArrayList<Point2D> rawSensorB = new ArrayList<>(List.of(
                new Point2D(3, 4),
                new Point2D(5, 0),
                new Point2D(2, 2),
                new Point2D(8, 6),
                new Point2D(2, 2),
                new Point2D(0, 10)
        ));

        HashSet<Point2D> setA = new HashSet<>(rawSensorA);
        HashSet<Point2D> setB = new HashSet<>(rawSensorB);

        System.out.println("================ 📡 ГЕОДЕЗИЧНИЙ РАДАР: HASHSET ТА ITERATOR 📡 ================");
        System.out.printf("Сенсор A: отримано %d точок -> унікальних (HashSet): %d %s%n",
                rawSensorA.size(), setA.size(), setA);
        System.out.printf("Сенсор B: отримано %d точок -> унікальних (HashSet): %d %s%n",
                rawSensorB.size(), setB.size(), setB);

        HashSet<Point2D> unionSet = PointSetUtils.union(setA, setB);
        HashSet<Point2D> intersectionSet = PointSetUtils.intersection(setA, setB);
        HashSet<Point2D> differenceSet = PointSetUtils.difference(setA, setB);

        System.out.println("\n--- Операції над множинами (HashSet) ---");
        System.out.printf("Об'єднання (A U B) [%d]:  %s%n", unionSet.size(), unionSet);
        System.out.printf("Перетин (A ∩ B) [%d]:     %s%n", intersectionSet.size(), intersectionSet);
        System.out.printf("Різниця (A \\ B) [%d]:     %s%n", differenceSet.size(), differenceSet);

        double maxRadius = 7.0;
        int removedCount = PointSetUtils.removeFarPoints(unionSet, maxRadius);

        System.out.printf("%n--- Фільтрація через Iterator (R_max = %.1f) ---%n", maxRadius);
        System.out.println("Видалено точок поза радіусом: " + removedCount);
        System.out.printf("Залишилось у зоні [%d]:   %s%n", unionSet.size(), unionSet);
        System.out.println("==============================================================================");
    }
}
