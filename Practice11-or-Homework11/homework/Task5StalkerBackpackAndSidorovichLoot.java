package homework;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/** Домашнє завдання №11, завдання 5 (5 балів). */
public class Task5StalkerBackpackAndSidorovichLoot {
    public static final double MAX_SAFE_RADIATION = 50.0;
    private static final int SPECIAL_CONTAINER_MIN_COST = 15_000;

    public static void main(String[] args) {
        System.out.println("================ S.T.A.L.K.E.R. 2: РЮКЗАК СТАЛКЕРА ТА ЛАВКА СИДОРОВИЧА ================");
        System.out.println("Сталкер: Скіф | Дикий Схід Зони -> Бункер Сидоровича | Ліміт: 50.0 мкЗв/год");

        var backpack = new ArrayList<Artifact>();
        backpack.add(new Artifact("Бенгальський вогонь", 9_500, 14.5));
        backpack.add(new Artifact("Фоняча Пустушка", 0, 92.0));
        backpack.add(new Artifact("Крівник", 18_500, 26.0));
        backpack.add(new Artifact("Аномальний Шлак", 1_200, 135.4));
        backpack.add(new Artifact("Виверт", 11_000, 18.0));
        backpack.add(new Artifact("Іржава Пустушка", 500, 78.5));
        backpack.add(new Artifact("Компас", 45_000, 32.0));
        backpack.add(new Artifact("Граві", 13_500, 21.0));

        System.out.println("\n[1. РЕВІЗІЯ РЮКЗАКА ДО ОЧИЩЕННЯ]");
        printArtifacts(backpack);
        System.out.printf(Locale.US, "Всього знахідок: %d | Сума рівнів радіації: %.1f мкЗв/год%n",
                backpack.size(), totalRadiation(backpack));

        System.out.println("\n[2. НЕБЕЗПЕЧНЕ ВИДАЛЕННЯ ПІД ЧАС FOR-EACH (ДЕМОНСТРАЦІЯ)]");
        demonstrateFailFastOnBackpack(backpack);

        System.out.println("\n[3. БЕЗПЕЧНЕ ОЧИЩЕННЯ ЧЕРЕЗ ITERATOR.REMOVE()]");
        int removed = purgeRadioactiveDudsWithIterator(backpack, MAX_SAFE_RADIATION);
        System.out.printf("Видалено пустушок: %d | Залишилося: %d%n", removed, backpack.size());
        System.out.printf(Locale.US, "Сума рівнів радіації решти: %.1f мкЗв/год%n", totalRadiation(backpack));

        System.out.println("\n[4. СОРТУВАННЯ ТА РОЗПОДІЛ ПО КОНТЕЙНЕРАХ]");
        var leadSpecialContainer = new ArrayList<Artifact>();
        var standardBeltContainer = new ArrayList<Artifact>();
        distributeAndRankForSidorovich(backpack, leadSpecialContainer, standardBeltContainer);
        System.out.println("Свинцевий спецконтейнер (від 15 000 крб):");
        printArtifacts(leadSpecialContainer);
        System.out.println("Стандартний поясний контейнер (менше 15 000 крб):");
        printArtifacts(standardBeltContainer);

        System.out.println("\n[5. ФІНАЛЬНИЙ ЧЕК СИДОРОВИЧА]");
        findTopArtifactAndPrintReceipt(backpack);
    }

    /** На копії списку показує, чому не слід змінювати ArrayList напряму в for-each. */
    public static void demonstrateFailFastOnBackpack(List<Artifact> backpack) {
        List<Artifact> copy = new ArrayList<>(backpack);
        try {
            for (Artifact artifact : copy) {
                if (artifact.isRadioactiveDud(MAX_SAFE_RADIATION)) {
                    copy.remove(artifact); // навмисна помилка для демонстрації fail-fast
                }
            }
            System.out.println("Виняток не виник (fail-fast не гарантований для кожної модифікації).");
        } catch (ConcurrentModificationException e) {
            System.out.println("Перехоплено ConcurrentModificationException: прямий remove() порушив ітерацію.");
        }
        System.out.println("Оригінальний рюкзак не змінено: " + backpack.size() + " знахідок.");
    }

    public static int purgeRadioactiveDudsWithIterator(List<Artifact> backpack, double maxRadiation) {
        Iterator<Artifact> iterator = backpack.iterator();
        int removedDuds = 0;
        while (iterator.hasNext()) {
            Artifact current = iterator.next();
            if (current.isRadioactiveDud(maxRadiation)) {
                System.out.printf(Locale.US, "[Iterator.remove] Вилучено: %s (%.1f мкЗв/год, %d крб)%n",
                        current.getName(), current.getRadiationLevel(), current.getCostKarbovanets());
                iterator.remove();
                removedDuds++;
            }
        }
        return removedDuds;
    }

    /** Сортування вибором: тільки get() та set(), без Collections.sort(). */
    public static void distributeAndRankForSidorovich(List<Artifact> cleanLoot,
                                                      List<Artifact> leadContainer,
                                                      List<Artifact> standardPouch) {
        for (int i = 0; i < cleanLoot.size(); i++) {
            int mostExpensiveIndex = i;
            for (int j = i + 1; j < cleanLoot.size(); j++) {
                if (cleanLoot.get(j).getCostKarbovanets() > cleanLoot.get(mostExpensiveIndex).getCostKarbovanets()) {
                    mostExpensiveIndex = j;
                }
            }
            Artifact temp = cleanLoot.get(i);
            cleanLoot.set(i, cleanLoot.get(mostExpensiveIndex));
            cleanLoot.set(mostExpensiveIndex, temp);
        }
        for (int i = 0; i < cleanLoot.size(); i++) {
            Artifact artifact = cleanLoot.get(i);
            if (artifact.getCostKarbovanets() >= SPECIAL_CONTAINER_MIN_COST) {
                leadContainer.add(artifact);
            } else {
                standardPouch.add(artifact);
            }
        }
    }

    public static void findTopArtifactAndPrintReceipt(List<Artifact> cleanLoot) {
        int maxCost = Integer.MIN_VALUE;
        int minCost = Integer.MAX_VALUE;
        long totalCost = 0;
        Artifact topArtifact = null;
        for (int i = 0; i < cleanLoot.size(); i++) {
            Artifact artifact = cleanLoot.get(i);
            int cost = artifact.getCostKarbovanets();
            System.out.printf(Locale.US, "%d. %-22s %7d крб | %.1f мкЗв/год%n",
                    i + 1, artifact.getName(), cost, artifact.getRadiationLevel());
            totalCost += cost;
            if (cost > maxCost) {
                maxCost = cost;
                topArtifact = artifact;
            }
            if (cost < minCost) minCost = cost;
        }
        if (topArtifact == null) {
            System.out.println("Немає придатних артефактів для продажу.");
            return;
        }
        System.out.println("Найдорожчий артефакт: " + topArtifact.getName() + " (" + maxCost + " крб)");
        System.out.println("Найдешевший артефакт: " + minCost + " крб");
        System.out.println("ПІДСУМКОВИЙ ВИТОРГ: " + totalCost + " крб");
        System.out.println("Сидорович: Гарний улов, Скіфе! Ось твої " + totalCost + " карбованців.");
    }

    private static double totalRadiation(List<Artifact> artifacts) {
        double total = 0;
        for (Artifact artifact : artifacts) total += artifact.getRadiationLevel();
        return total;
    }

    private static void printArtifacts(List<Artifact> artifacts) {
        for (int i = 0; i < artifacts.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + artifacts.get(i));
        }
    }
}