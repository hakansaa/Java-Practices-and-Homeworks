package semester1.practice11;

import java.util.ArrayList;
import java.util.List;

public class WrapperDemo {
    public static void demonstrateWrapperTwins() {
        System.out.println("--- Блок 1.1: 8 класів-близнюків (Wrapper Classes) ---");
        ArrayList<Integer> numbersOk = new ArrayList<>(); // Колекції працюють лише з об'єктами

        Byte bObj = Byte.valueOf((byte) 10);
        Short sObj = Short.valueOf((short) 500);
        Integer iObj = Integer.valueOf(42);
        Long lObj = Long.valueOf(100L);
        Float fObj = Float.valueOf(3.14f);
        Double dObj = Double.valueOf(2.718);
        Character cObj = Character.valueOf('A');
        Boolean boolObj = Boolean.valueOf(true);

        System.out.println("Byte обгортка: " + bObj + " (тип: " + bObj.getClass().getSimpleName() + ")");
        System.out.println("Integer обгортка: " + iObj + " (тип: " + iObj.getClass().getSimpleName() + ")");
        System.out.println("Character обгортка: " + cObj + " (тип: " + cObj.getClass().getSimpleName() + ")");
    }

    public static void showCompilerUnderTheHood() {
        System.out.println("\n--- Блок 1.2: Autoboxing та Unboxing під капотом ---");
        Integer boxed = 150; // Autoboxing
        int unboxed = boxed; // Unboxing

        Integer boxedCompiler = Integer.valueOf(150);
        int unboxedCompiler = boxedCompiler.intValue();

        System.out.println("Пакування / Розпакування успішне: boxed=" + boxed + ", unboxed=" + unboxed);
    }

    public static List<Integer> parseNumbersFromStrings(String[] inputs) {
        System.out.println("\n--- Блок 1.3: parseInt() vs valueOf() ---");
        List<Integer> resultList = new ArrayList<>();
        int primitiveSum = 0;

        for (String s : inputs) {
            try {
                if (s == null || s.isBlank()) continue;
                int primVal = Integer.parseInt(s);
                primitiveSum += primVal;
                resultList.add(Integer.valueOf(primVal));
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Попередження: неможливо розпарсити рядок -> \"" + s + "\"");
            }
        }
        System.out.println("Сума примітивів int: " + primitiveSum);
        return resultList;
    }

    public static void testIntegerPool() {
        System.out.println("\n--- Блок 1.5: Кешування IntegerCache [-128; 127] ---");
        Integer a = 100;
        Integer b = 100;
        System.out.println("100 == 100 (у межах кешу [-128; 127]): " + (a == b)); // true

        Integer x = 500;
        Integer y = 500;
        System.out.println("500 == 500 (поза межами кешу): " + (x == y)); // false
        System.out.println("500.equals(500) через equals(): " + x.equals(y)); // true
    }
}