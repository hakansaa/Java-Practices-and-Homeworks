package semester1.practice12;

import java.util.*;

public class IteratorAndListIteratorDemo {

    // Завдання 17: Універсальний обхід через Iterator<T>
    public static <T> void printViaIterator(Iterable<T> collection, String title) {
        System.out.println(title + ":");
        Iterator<T> it = collection.iterator();
        int idx = 1;
        while (it.hasNext()) {
            System.out.println("  " + (idx++) + ". " + it.next());
        }
    }

    public static void task17IteratorTraversal() {
        System.out.println("\n=== Завдання 17: Обхід List та Set через Iterator ===");
        List<String> list = List.of("Меркурій", "Венера", "Земля", "Марс");
        Set<String> set = new LinkedHashSet<>(List.of("Юпітер", "Сатурн", "Уран", "Нептун"));
        printViaIterator(list, "Список планет");
        printViaIterator(set, "Множина планет");
    }

    // Завдання 18: ConcurrentModificationException та безпечне видалення
    public static void task18ConcurrentModification() {
        System.out.println("\n=== Завдання 18: ConcurrentModificationException vs Iterator.remove() ===");
        List<Integer> numbers = new ArrayList<>(List.of(10, 15, 22, 33, 40, 55, 60));

        try {
            for (Integer n : numbers) {
                if (n % 2 != 0) {
                    numbers.remove(n); // спричинить ConcurrentModificationException
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Спіймано ConcurrentModificationException при зміні списку під час for-each!");
        }

        List<Integer> clean = new ArrayList<>(numbers);
        Iterator<Integer> it = clean.iterator();
        while (it.hasNext()) {
            if (it.next() % 2 != 0) {
                it.remove();
            }
        }
        System.out.println("Парні числа після Iterator.remove(): " + clean);
    }

    // Завдання 19: Очищення HashSet від прострочених сесій
    static class UserSession {
        private final String token;
        private final int idleMinutes;

        UserSession(String token, int idleMinutes) {
            this.token = token;
            this.idleMinutes = idleMinutes;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserSession other)) return false;
            return Objects.equals(token, other.token);
        }

        @Override
        public int hashCode() { return Objects.hashCode(token); }

        @Override
        public String toString() { return token + "(" + idleMinutes + "хв)"; }
    }

    public static int purgeExpiredSessions(Set<UserSession> sessions, int maxIdleMinutes) {
        int removed = 0;
        Iterator<UserSession> it = sessions.iterator();
        while (it.hasNext()) {
            if (it.next().idleMinutes > maxIdleMinutes) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public static void task19PurgeSessions() {
        System.out.println("\n=== Завдання 19: Очищення прострочених сесій ===");
        Set<UserSession> sessions = new HashSet<>(List.of(
                new UserSession("tok-1", 2),
                new UserSession("tok-2", 45),
                new UserSession("tok-3", 120),
                new UserSession("tok-4", 15),
                new UserSession("tok-5", 65),
                new UserSession("tok-6", 5),
                new UserSession("tok-7", 90)
        ));
        int deleted = purgeExpiredSessions(sessions, 30);
        System.out.println("Видалено прострочених (>30 хв): " + deleted + ", залишилось: " + sessions);
    }

    // Завдання 20: Двонаправлений обхід через ListIterator
    public static void task20ListIteratorDirections() {
        System.out.println("\n=== Завдання 20: ListIterator вперед та назад ===");
        List<String> stations = new ArrayList<>(List.of("Вокзальна", "Університет", "Театральна", "Хрещатик", "Арсенальна"));
        ListIterator<String> lit = stations.listIterator();

        System.out.print("Вперед: ");
        while (lit.hasNext()) {
            String st = lit.next();
            System.out.print("[" + lit.previousIndex() + "]" + st + (lit.hasNext() ? " -> " : ""));
        }
        System.out.println();

        System.out.print("Назад:  ");
        while (lit.hasPrevious()) {
            String st = lit.previous();
            System.out.print("[" + lit.nextIndex() + "]" + st + (lit.hasPrevious() ? " <- " : ""));
        }
        System.out.println();
    }

    // Завдання 21: Модифікація через ListIterator.set() та add()
    public static void task21ListIteratorModify() {
        System.out.println("\n=== Завдання 21: ListIterator set() та add() ===");
        List<String> commands = new ArrayList<>(List.of("START", "LOAD", "ERROR_TEMP", "PROCESS", "STOP"));
        ListIterator<String> lit = commands.listIterator();

        while (lit.hasNext()) {
            String cmd = lit.next();
            if ("ERROR_TEMP".equals(cmd)) {
                lit.set("RETRY");
            } else if ("LOAD".equals(cmd)) {
                lit.add("VALIDATE");
            }
        }
        System.out.println("Оновлені команди: " + commands);
    }

    // Завдання 22: Фільтрація через Iterator та removeIf()
    public static int cleanWithIterator(List<Double> data) {
        int removed = 0;
        Iterator<Double> it = data.iterator();
        while (it.hasNext()) {
            Double v = it.next();
            if (v == null || Double.isNaN(v) || v < 0.0 || v > 100.0) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public static void cleanWithRemoveIf(List<Double> data) {
        data.removeIf(v -> v == null || Double.isNaN(v) || v < 0.0 || v > 100.0);
    }

    public static void task22SensorCleaning() {
        System.out.println("\n=== Завдання 22: Очищення показників датчиків ===");
        List<Double> raw1 = new ArrayList<>(Arrays.asList(
                21.5, -5.0, 24.0, Double.NaN, 150.2, 19.8, 22.4, -0.1, 25.0, 101.0, 20.2, 23.1
        ));
        List<Double> raw2 = new ArrayList<>(raw1);

        int removedCount = cleanWithIterator(raw1);
        cleanWithRemoveIf(raw2);

        double sum = 0;
        for (double d : raw1) sum += d;
        double avg = raw1.isEmpty() ? 0.0 : sum / raw1.size();

        System.out.println("Видалено викидів: " + removedCount + ", збігаються з removeIf: " + raw1.equals(raw2));
        System.out.printf("Валідні дані (%d): %s, середнє = %.2f%n", raw1.size(), raw1, avg);
    }

    // Завдання 23: Видалення дублікатів на місці через Iterator + Set
    public static int removeDuplicatesInPlace(List<String> list) {
        Set<String> seen = new HashSet<>();
        int removed = 0;
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (!seen.add(it.next())) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    public static void task23RemoveDuplicatesInPlace() {
        System.out.println("\n=== Завдання 23: Видалення дублікатів на місці ===");
        List<String> tags = new ArrayList<>(List.of("oop", "java", "set", "java", "iterator", "oop", "list", "set"));
        int removed = removeDuplicatesInPlace(tags);
        System.out.println("Видалено дублікатів: " + removed + ", унікальний список: " + tags);
    }

    // Завдання 24: Злиття двох відсортованих списків через два ітератори
    public static List<Integer> mergeSortedUnique(List<Integer> a, List<Integer> b) {
        List<Integer> merged = new ArrayList<>();
        Iterator<Integer> itA = a.iterator();
        Iterator<Integer> itB = b.iterator();

        Integer curA = itA.hasNext() ? itA.next() : null;
        Integer curB = itB.hasNext() ? itB.next() : null;

        while (curA != null || curB != null) {
            Integer chosen;
            if (curA == null) {
                chosen = curB;
                curB = itB.hasNext() ? itB.next() : null;
            } else if (curB == null) {
                chosen = curA;
                curA = itA.hasNext() ? itA.next() : null;
            } else if (curA.compareTo(curB) < 0) {
                chosen = curA;
                curA = itA.hasNext() ? itA.next() : null;
            } else if (curB.compareTo(curA) < 0) {
                chosen = curB;
                curB = itB.hasNext() ? itB.next() : null;
            } else {
                chosen = curA;
                curA = itA.hasNext() ? itA.next() : null;
                curB = itB.hasNext() ? itB.next() : null;
            }

            if (merged.isEmpty() || !merged.get(merged.size() - 1).equals(chosen)) {
                merged.add(chosen);
            }
        }
        return merged;
    }

    public static void task24MergeTwoIterators() {
        System.out.println("\n=== Завдання 24: Злиття відсортованих списків через Iterator ===");
        List<Integer> a = List.of(1, 4, 7, 12, 20);
        List<Integer> b = List.of(2, 4, 9, 12, 15, 25);
        System.out.println("Результат злиття: " + mergeSortedUnique(a, b));
    }
}