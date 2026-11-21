package semester1.practice12;

import java.util.*;

public class TreeSetAndSearchDemo {

    // Завдання 5: Статистика унікальних слів у тексті
    public static void task5WordStatistics() {
        System.out.println("\n=== Завдання 5: Статистика слів у тексті ===");
        String text = "Java is fast, Java is safe! Collections: List, Set, and Iterator.";

        String cleaned = text.replaceAll("[^a-zA-Zа-яА-ЯіІїЇєЄґҐ0-9\\s]", " ").toLowerCase().trim();
        String[] words = cleaned.isEmpty() ? new String[0] : cleaned.split("\\s+");

        TreeSet<String> uniqueWords = new TreeSet<>(Arrays.asList(words));
        int totalWords = words.length;
        int uniqueCount = uniqueWords.size();
        double diversityPercent = totalWords == 0 ? 0.0 : (uniqueCount * 100.0) / totalWords;

        System.out.println("Унікальні слова (TreeSet): " + uniqueWords);
        System.out.println("Усього слів: " + totalWords);
        System.out.println("Унікальних слів: " + uniqueCount);
        if (!uniqueWords.isEmpty()) {
            System.out.println("Перше за алфавітом: " + uniqueWords.first());
            System.out.println("Останнє за алфавітом: " + uniqueWords.last());
        }
        System.out.printf("Лексичне різноманіття: %.2f%%%n", diversityPercent);
    }

    // Завдання 6: Поведінка null та реєстрація промокодів
    public static boolean registerPromoCode(Set<String> activeCodes, String code) {
        if (activeCodes == null || code == null || code.isBlank()) {
            return false;
        }
        return activeCodes.add(code.trim().toUpperCase());
    }

    public static void task6NullAndPromoCodes() {
        System.out.println("\n=== Завдання 6: null у Set та промокоди ===");
        Set<String> hs = new HashSet<>();
        hs.add(null);
        hs.add(null);
        System.out.println("HashSet з null, розмір: " + hs.size() + " -> " + hs);

        Set<String> lhs = new LinkedHashSet<>();
        lhs.add(null);
        lhs.add(null);
        System.out.println("LinkedHashSet з null, розмір: " + lhs.size() + " -> " + lhs);

        TreeSet<String> ts = new TreeSet<>();
        try {
            ts.add(null);
        } catch (NullPointerException e) {
            System.out.println("TreeSet не допускає null -> спіймано NullPointerException");
        }

        Set<String> promos = new HashSet<>();
        System.out.println("Реєстрація 'spring26': " + registerPromoCode(promos, "spring26"));
        System.out.println("Повторна 'SPRING26': " + registerPromoCode(promos, "SPRING26"));
        System.out.println("Порожній код '': " + registerPromoCode(promos, ""));
        System.out.println("Активні промокоди: " + promos);
    }

    // Завдання 7: Діапазони температур у TreeSet
    public static void task7TemperatureRanges() {
        System.out.println("\n=== Завдання 7: Діапазони температур (TreeSet) ===");
        TreeSet<Double> temps = new TreeSet<>(List.of(
                -4.5, 0.0, 12.3, 18.7, 22.1, 25.0, 31.4, 36.6, 40.2, 15.5
        ));

        System.out.println("Мін / Макс: " + temps.first() + " / " + temps.last());
        System.out.println("Нижче 20.0: " + temps.headSet(20.0));
        System.out.println("Від 25.0 і вище: " + temps.tailSet(25.0));
        System.out.println("Діапазон [15.0; 32.0): " + temps.subSet(15.0, 32.0));
        System.out.println("Найближче >= 20.0 (ceiling): " + temps.ceiling(20.0));
        System.out.println("Найближче <= 20.0 (floor): " + temps.floor(20.0));
    }

    // Завдання 8: Бенчмарк contains() для ArrayList та HashSet
    public static void task8ContainsBenchmark() {
        System.out.println("\n=== Завдання 8: Бенчмарк contains() ===");
        int count = 100_000;
        List<Integer> arrayList = new ArrayList<>(count);
        Set<Integer> hashSet = new HashSet<>(count);

        for (int i = 0; i < count; i++) {
            arrayList.add(i);
            hashSet.add(i);
        }

        List<Integer> queries = new ArrayList<>(10_000);
        for (int i = 0; i < 10_000; i++) {
            // Частина існуючих ближче до кінця та відсутніх елементів
            queries.add(i % 2 == 0 ? 90_000 + (i % 10_000) : count + i);
        }

        long t1Start = System.nanoTime();
        int foundList = 0;
        for (Integer q : queries) {
            if (arrayList.contains(q)) foundList++;
        }
        long listMs = (System.nanoTime() - t1Start) / 1_000_000;

        long t2Start = System.nanoTime();
        int foundSet = 0;
        for (Integer q : queries) {
            if (hashSet.contains(q)) foundSet++;
        }
        long setNs = System.nanoTime() - t2Start;
        double setMs = setNs / 1_000_000.0;

        System.out.printf("ArrayList (знайдено %d): %d мс%n", foundList, listMs);
        System.out.printf("HashSet   (знайдено %d): %.4f мс (%d нс)%n", foundSet, setMs, setNs);
        if (setNs > 0) {
            double speedup = (double) ((System.nanoTime() - t1Start) - (t2Start - t1Start)) / 1_000_000.0;
            System.out.printf("HashSet значно швидший за ArrayList (різниця часу: ~%.2f мс)%n", Math.max(0.0, listMs - setMs));
        }
    }
}