package semester1.practice12;

import java.util.*;

public class SetTheoryAndHashContractDemo {

    // Завдання 9: Калькулятор математичних множин (без зміни вхідних множин)
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(a);
        res.addAll(b);
        return res;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(a);
        res.retainAll(b);
        return res;
    }

    public static <T> Set<T> difference(Set<T> a, Set<T> b) {
        Set<T> res = new HashSet<>(a);
        res.removeAll(b);
        return res;
    }

    public static <T> Set<T> symmetricDifference(Set<T> a, Set<T> b) {
        Set<T> u = union(a, b);
        Set<T> inter = intersection(a, b);
        u.removeAll(inter);
        return u;
    }

    public static void task9SetMathCalculator() {
        System.out.println("\n=== Завдання 9: Математичні операції над множинами ===");
        Set<Integer> a = new LinkedHashSet<>(List.of(1, 2, 3, 4, 5));
        Set<Integer> b = new LinkedHashSet<>(List.of(4, 5, 6, 7, 8));

        System.out.println("A \\cup B: " + union(a, b));
        System.out.println("A \\cap B: " + intersection(a, b));
        System.out.println("A \\ B:    " + difference(a, b));
        System.out.println("A \\triangle B: " + symmetricDifference(a, b));
        System.out.println("Вихідні A і B не змінені: A=" + a + ", B=" + b);
    }

    // Завдання 10: Індекс схожості Жаккара
    public static double jaccardSimilarity(Set<String> a, Set<String> b) {
        if (a.isEmpty() && b.isEmpty()) return 1.0;
        Set<String> inter = intersection(a, b);
        Set<String> u = union(a, b);
        return u.isEmpty() ? 0.0 : (double) inter.size() / u.size();
    }

    public static void task10JaccardSimilarity() {
        System.out.println("\n=== Завдання 10: Коефіцієнт Жаккара ===");
        Set<String> vacancy = Set.of("Java", "SQL", "Git", "Docker", "Spring");
        Map<String, Set<String>> candidates = new LinkedHashMap<>();
        candidates.put("Кандидат 1", Set.of("Java", "SQL", "Git"));
        candidates.put("Кандидат 2", Set.of("Java", "Spring", "Docker", "Git", "SQL"));
        candidates.put("Кандидат 3", Set.of("Python", "Git"));

        for (var entry : candidates.entrySet()) {
            double sim = jaccardSimilarity(entry.getValue(), vacancy);
            Set<String> missing = difference(vacancy, entry.getValue());
            System.out.printf("%s: схожість = %.1f%%, бракує = %s%n", entry.getKey(), sim * 100, missing);
        }
    }

    // Завдання 11: Перевірка прав доступу через підмножини
    public static boolean hasAccess(Set<String> userPerms, Set<String> requiredPerms) {
        return userPerms.containsAll(requiredPerms);
    }

    public static Set<String> getMissingPermissions(Set<String> userPerms, Set<String> requiredPerms) {
        return difference(requiredPerms, userPerms);
    }

    public static void task11PermissionsCheck() {
        System.out.println("\n=== Завдання 11: Перевірка прав доступу ===");
        Set<String> userPerms = Set.of("READ", "WRITE", "EXPORT");
        System.out.println("Повний доступ [READ, EXPORT]: " + hasAccess(userPerms, Set.of("READ", "EXPORT")));
        Set<String> reqAdmin = Set.of("READ", "DELETE_USERS");
        System.out.println("Доступ [READ, DELETE_USERS]: " + hasAccess(userPerms, reqAdmin)
                + ", бракує: " + getMissingPermissions(userPerms, reqAdmin));
        System.out.println("Порожні вимоги []: " + hasAccess(userPerms, Set.of()));
    }

    // Завдання 12: BrokenPoint2D vs ValidPoint2D
    static class BrokenPoint2D {
        private final int x, y;
        BrokenPoint2D(int x, int y) { this.x = x; this.y = y; }
    }

    static final class ValidPoint2D {
        private final int x, y;
        ValidPoint2D(int x, int y) { this.x = x; this.y = y; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ValidPoint2D other)) return false;
            return x == other.x && y == other.y;
        }

        @Override
        public int hashCode() { return Objects.hash(x, y); }

        @Override
        public String toString() { return "(" + x + ";" + y + ")"; }
    }

    public static void task12PointEqualsHashCode() {
        System.out.println("\n=== Завдання 12: Контракт equals() / hashCode() ===");
        Set<BrokenPoint2D> brokenSet = new HashSet<>();
        brokenSet.add(new BrokenPoint2D(0, 0));
        brokenSet.add(new BrokenPoint2D(3, 4));
        brokenSet.add(new BrokenPoint2D(0, 0));
        brokenSet.add(new BrokenPoint2D(3, 4));

        Set<ValidPoint2D> validSet = new HashSet<>();
        validSet.add(new ValidPoint2D(0, 0));
        validSet.add(new ValidPoint2D(3, 4));
        validSet.add(new ValidPoint2D(0, 0));
        validSet.add(new ValidPoint2D(3, 4));

        System.out.println("BrokenPoint2D розмір (очікувано 4 через відсутність equals/hashCode): " + brokenSet.size());
        System.out.println("ValidPoint2D розмір (очікувано 2): " + validSet.size()
                + ", містить (3;4): " + validSet.contains(new ValidPoint2D(3, 4)));
    }

    // Завдання 13: Реєстр студентів за studentId
    static class StudentRecord {
        private final String studentId;
        private String fullName;
        private double gpa;

        StudentRecord(String studentId, String fullName, double gpa) {
            this.studentId = studentId;
            this.fullName = fullName;
            this.gpa = gpa;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof StudentRecord other)) return false;
            return Objects.equals(studentId, other.studentId);
        }

        @Override
        public int hashCode() { return Objects.hashCode(studentId); }

        @Override
        public String toString() { return studentId + " (" + fullName + ", GPA=" + gpa + ")"; }
    }

    public static void task13StudentRegistry() {
        System.out.println("\n=== Завдання 13: Реєстр студентів за studentId ===");
        Set<StudentRecord> registry = new HashSet<>();
        registry.add(new StudentRecord("KB-101", "Іван Коваль", 88.5));
        registry.add(new StudentRecord("KB-102", "Олена Ткач", 94.0));
        registry.add(new StudentRecord("KB-103", "Максим Бондар", 79.2));
        registry.add(new StudentRecord("KB-101", "Іван Коваль (оновлено)", 91.0)); // дублікат за ID
        registry.add(new StudentRecord("KB-104", "Софія Мельник", 85.0));

        System.out.println("Унікальних студентів у реєстрі: " + registry.size() + " -> " + registry);
    }

    // Завдання 14: Пастка змінюваного ключа в HashSet
    static class MutableTag {
        private String label;
        MutableTag(String label) { this.label = label; }
        void setLabel(String label) { this.label = label; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MutableTag other)) return false;
            return Objects.equals(label, other.label);
        }

        @Override
        public int hashCode() { return Objects.hashCode(label); }

        @Override
        public String toString() { return "Tag[" + label + "]"; }
    }

    public static void task14MutableKeyTrap() {
        System.out.println("\n=== Завдання 14: Пастка змінюваного ключа в HashSet ===");
        Set<MutableTag> tags = new HashSet<>();
        MutableTag t = new MutableTag("java");
        tags.add(t);
        System.out.println("До зміни: contains(t) = " + tags.contains(t) + ", size = " + tags.size());

        t.setLabel("kotlin"); // Змінили поле безпосередньо всередині множини
        System.out.println("Після setLabel('kotlin'): contains(t) = " + tags.contains(t)
                + ", remove(t) = " + tags.remove(t) + ", size = " + tags.size());

        // Правильне оновлення: вилучити -> змінити -> додати назад
        tags.clear();
        MutableTag safeTag = new MutableTag("java");
        tags.add(safeTag);
        tags.remove(safeTag);
        safeTag.setLabel("kotlin");
        tags.add(safeTag);
        System.out.println("Безпечне оновлення: contains(safeTag) = " + tags.contains(safeTag) + " -> " + tags);
    }

    // Завдання 15: Сортування серверів у TreeSet
    static class ServerNode implements Comparable<ServerNode> {
        private final String hostName;
        private final int pingMs;
        private final double cpuLoad;

        ServerNode(String hostName, int pingMs, double cpuLoad) {
            this.hostName = hostName;
            this.pingMs = pingMs;
            this.cpuLoad = cpuLoad;
        }

        @Override
        public int compareTo(ServerNode other) {
            int cmp = Integer.compare(this.pingMs, other.pingMs);
            return cmp != 0 ? cmp : this.hostName.compareTo(other.hostName);
        }

        @Override
        public String toString() {
            return String.format("%s(ping=%dмs, cpu=%.1f%%)", hostName, pingMs, cpuLoad);
        }
    }

    public static void task15ServerTreeSet() {
        System.out.println("\n=== Завдання 15: TreeSet для ServerNode ===");
        List<ServerNode> nodes = List.of(
                new ServerNode("eu-central-1", 24, 65.0),
                new ServerNode("us-east-1", 110, 40.5),
                new ServerNode("eu-west-2", 24, 82.0),
                new ServerNode("ua-kyiv-1", 12, 55.2)
        );

        TreeSet<ServerNode> byPing = new TreeSet<>(nodes);
        TreeSet<ServerNode> byCpuDesc = new TreeSet<>(
                Comparator.comparingDouble((ServerNode s) -> s.cpuLoad).reversed()
                        .thenComparing(s -> s.hostName)
        );
        byCpuDesc.addAll(nodes);

        System.out.println("За пінгом (Comparable): " + byPing);
        System.out.println("За CPU спаданням (Comparator): " + byCpuDesc);
    }

    // Завдання 16: Ортогональні пари векторів
    static final class IntVector2D {
        private final int dx, dy;
        IntVector2D(int dx, int dy) { this.dx = dx; this.dy = dy; }
        int dotProduct(IntVector2D other) { return this.dx * other.dx + this.dy * other.dy; }
        boolean isZero() { return dx == 0 && dy == 0; }
        double norm() { return Math.hypot(dx, dy); }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IntVector2D other)) return false;
            return dx == other.dx && dy == other.dy;
        }

        @Override
        public int hashCode() { return Objects.hash(dx, dy); }

        @Override
        public String toString() { return "<" + dx + ", " + dy + ">"; }
    }

    public static void task16OrthogonalVectors() {
        System.out.println("\n=== Завдання 16: Ортогональні пари векторів ===");
        IntVector2D[] raw = {
                new IntVector2D(1, 0), new IntVector2D(0, 1), new IntVector2D(1, 0),
                new IntVector2D(2, 3), new IntVector2D(-3, 2), new IntVector2D(0, 0)
        };
        Set<IntVector2D> unique = new LinkedHashSet<>(Arrays.asList(raw));
        List<IntVector2D> list = new ArrayList<>(unique);

        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                IntVector2D u = list.get(i);
                IntVector2D v = list.get(j);
                if (!u.isZero() && !v.isZero() && u.dotProduct(v) == 0) {
                    System.out.printf("Ортогональна пара: %s та %s (|u|=%.2f, |v|=%.2f)%n", u, v, u.norm(), v.norm());
                }
            }
        }
    }
}