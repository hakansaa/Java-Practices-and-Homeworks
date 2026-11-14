package semester1.practice11;

import java.util.ArrayList;
import java.util.List;

public class ArrayListDemo {
    public static void demonstrateArrayListAdvantages() {
        System.out.println("\n--- Блок 2.1: Звичайний масив vs ArrayList ---");
        ArrayList<String> dynamicList = new ArrayList<>(List.of("Київ", "Львів", "Одеса"));
        dynamicList.add("Харків");
        dynamicList.add(1, "Житомир");
        dynamicList.remove(0);

        System.out.println("Динамічний ArrayList: " + dynamicList);
    }

    public static void traceArrayListCapacity(int elementsCount) {
        System.out.println("\n--- Блок 2.2: Моделювання ємності ArrayList (розширення в 1.5 раза) ---");
        int capacity = 10;
        int size = 0;

        for (int i = 1; i <= elementsCount; i++) {
            if (size >= capacity) {
                int oldCapacity = capacity;
                capacity = oldCapacity + (oldCapacity >> 1);
                System.out.printf("  [Розширення] Додавання %d-го елемента: ємність масиву Object[] зросла з %d до %d\n",
                        i, oldCapacity, capacity);
            }
            size++;
        }
    }

    public static void testRemoveNuances() {
        System.out.println("\n--- Блок 3.1: remove(int index) vs remove(Object o) ---");
        ArrayList<Integer> numbers = new ArrayList<>(List.of(10, 20, 30, 1, 40, 2));
        System.out.println("Початковий список: " + numbers);

        numbers.remove(1); // Видалить елемент за ІНДЕКСОМ 1
        System.out.println("Після remove(1) (за індексом): " + numbers);

        numbers.remove(Integer.valueOf(1)); // Видалить об'єкт ЗІ ЗНАЧЕННЯМ 1
        System.out.println("Після remove(Integer.valueOf(1)): " + numbers);
    }
}
