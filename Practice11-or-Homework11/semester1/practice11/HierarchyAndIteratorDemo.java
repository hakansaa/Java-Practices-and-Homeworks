package semester1.practice11;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HierarchyAndIteratorDemo {
    public static void demonstrateTypeInference() {
        System.out.println("\n--- Блок 4.1: Виведення типів (Diamond <> та var) ---");
        List<Integer> list1 = new ArrayList<>();
        var list2 = new ArrayList<Integer>(); // Виведення типу через var
        list2.add(100);
        System.out.println("var list2 успішно створено: " + list2);
    }

    public static void safeRemoveWithIterator() {
        System.out.println("\n--- Блок 4.2: Безпечне видалення через Iterator ---");
        ArrayList<Integer> data = new ArrayList<>(List.of(15, -3, 42, -10, 8, -1, 25));
        System.out.println("До видалення від'ємних: " + data);

        // Безпечний обхід і видалення без ConcurrentModificationException
        Iterator<Integer> it = data.iterator();
        while (it.hasNext()) {
            Integer current = it.next();
            if (current < 0) {
                it.remove();
            }
        }
        System.out.println("Після видалення від'ємних через Iterator: " + data);
    }
}
