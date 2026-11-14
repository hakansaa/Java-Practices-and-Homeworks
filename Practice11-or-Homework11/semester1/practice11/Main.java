package semester1.practice11;

public class Main {
    public static void main(String[] args) {
        System.out.println("=========================================================");
        System.out.println("     ПРАКТИЧНА РОБОТА №11: РОЗБИТА ПО МОДУЛЬНИХ КЛАСАХ");
        System.out.println("=========================================================");

        // Виклики з WrapperDemo
        WrapperDemo.demonstrateWrapperTwins();
        WrapperDemo.showCompilerUnderTheHood();
        String[] rawData = {"120", "-45", "300", "abc", "1024", ""};
        WrapperDemo.parseNumbersFromStrings(rawData);
        WrapperDemo.testIntegerPool();

        // Виклики з ArrayListDemo
        ArrayListDemo.demonstrateArrayListAdvantages();
        ArrayListDemo.traceArrayListCapacity(35);

        // Перевірка власної моделі SimpleArrayList
        SimpleArrayList simpleList = new SimpleArrayList();
        for (int i = 1; i <= 12; i++) simpleList.add(i * 10);
        System.out.printf("SimpleArrayList: size = %d, capacity = %d\n", simpleList.size(), simpleList.getCapacity());

        ArrayListDemo.testRemoveNuances();

        // Виклики з HierarchyAndIteratorDemo
        HierarchyAndIteratorDemo.demonstrateTypeInference();
        HierarchyAndIteratorDemo.safeRemoveWithIterator();

        System.out.println("\n=========================================================");
        System.out.println(" Усі класи Практичної роботи №11 успішно відпрацьовано!");
        System.out.println("=========================================================");
    }
}