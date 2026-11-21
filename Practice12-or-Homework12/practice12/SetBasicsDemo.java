package semester1.practice12;

import java.util.*;

public class SetBasicsDemo {

    // Завдання 1: Перша множина унікальних мов програмування (HashSet Basics)
    public static void task1HashSetBasics() {
        System.out.println("=== Завдання 1: HashSet Basics ===");
        Set<String> langs = new HashSet<>();

        System.out.println("Додаємо 'Java': " + langs.add("Java"));
        System.out.println("Додаємо 'Python': " + langs.add("Python"));
        System.out.println("Додаємо 'C++': " + langs.add("C++"));
        System.out.println("Додаємо 'Java' (дублікат): " + langs.add("Java"));
        System.out.println("Додаємо 'Kotlin': " + langs.add("Kotlin"));
        System.out.println("Додаємо 'Python' (дублікат): " + langs.add("Python"));

        System.out.println("Розмір множини: " + langs.size());
        System.out.println("Чи містить 'Java': " + langs.contains("Java"));
        System.out.println("Чи містить 'Rust': " + langs.contains("Rust"));

        langs.remove("C++");
        System.out.println("Множина після видалення 'C++': " + langs);
    }

    // Завдання 2: Порівняння HashSet vs LinkedHashSet vs TreeSet
    public static void task2CompareSetImplementations() {
        System.out.println("\n=== Завдання 2: HashSet vs LinkedHashSet vs TreeSet ===");
        String[] cities = {"Львів", "Київ", "Одеса", "Дніпро", "Харків", "Київ", "Львів", "Чернівці"};

        Set<String> hashSet = new HashSet<>(Arrays.asList(cities));
        Set<String> linkedHashSet = new LinkedHashSet<>(Arrays.asList(cities));
        Set<String> treeSet = new TreeSet<>(Arrays.asList(cities));

        // HashSet — порядок залежить від хеш-кодів і бакетів, не гарантується
        System.out.println("HashSet (довільний порядок):       " + hashSet);
        // LinkedHashSet — зберігає порядок першого додавання елементів
        System.out.println("LinkedHashSet (порядок додавання): " + linkedHashSet);
        // TreeSet — автоматично сортує за природним порядком (алфавіт)
        System.out.println("TreeSet (відсортований):           " + treeSet);
    }

    // Завдання 3: Дедуплікація ArrayList зі збереженням порядку
    public static List<String> deduplicatePreserveOrder(List<String> input) {
        Set<String> uniqueSet = new LinkedHashSet<>(input);
        return new ArrayList<>(uniqueSet);
    }

    public static void task3DeduplicateIps() {
        System.out.println("\n=== Завдання 3: Дедуплікація списку IP ===");
        List<String> rawLogs = List.of(
                "192.168.1.10", "10.0.0.1", "192.168.1.10", "172.16.0.5",
                "10.0.0.1", "192.168.1.10", "8.8.8.8", "1.1.1.1",
                "172.16.0.5", "8.8.8.8", "10.0.0.2", "192.168.1.11"
        );

        List<String> uniqueLogs = deduplicatePreserveOrder(rawLogs);
        int removedCount = rawLogs.size() - uniqueLogs.size();

        System.out.println("Записів 'до': " + rawLogs.size() + " -> " + rawLogs);
        System.out.println("Записів 'після': " + uniqueLogs.size() + " -> " + uniqueLogs);
        System.out.println("Відкинуто дублікатів: " + removedCount);
    }

    // Завдання 4: Пошук першого повторюваного елемента
    public static String findFirstDuplicate(List<String> ids) {
        Set<String> seen = new HashSet<>();
        for (String id : ids) {
            if (!seen.add(id)) {
                return id;
            }
        }
        return null;
    }

    public static void task4FindFirstDuplicate() {
        System.out.println("\n=== Завдання 4: Перший дублікат у транзакціях ===");
        List<String> withDupes = List.of("TX-1", "TX-2", "TX-3", "TX-2", "TX-4", "TX-3");
        List<String> allUnique = List.of("TX-10", "TX-20", "TX-30");

        System.out.println("Перший дублікат у withDupes: " + findFirstDuplicate(withDupes));
        System.out.println("Перший дублікат у allUnique: " + findFirstDuplicate(allUnique));
    }
}