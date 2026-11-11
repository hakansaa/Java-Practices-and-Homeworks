import java.util.Arrays;
import java.util.Scanner;
 public class Practice05 {
    public static void main (String[] args){

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Блок 1: Оголошення, ініціалізація та доступ до 1D-масивів ===");
        task01();
        task02();
        task03();
        task04(scanner);
        task05();
        task06();
        task07();

        System.out.println("\n=== Блок 2: Базові обчислення та агрегація в 1D-масивах ===");
        task08();
        task09();
        task10();
        task11();
        task12(scanner);
        task13(scanner);
        task14();
        task15();

        System.out.println("\n=== Блок 3: Перетворення, for-each та клас Arrays ===");
        task16();
        task17();
        task18();
        task19();
        task20();
        task21();
        task22();

        System.out.println("\n=== Блок 4: Двовимірні масиви (Матриці): структура та виведення ===");
        task23();
        task24();
        task25();
        task26();
        task27();
        task28();
        task29();

        System.out.println("\n=== Блок 5: Обчислення та алгоритми в двовимірних масивах ===");
        task30();
        task31();
        task32();
        task33();
        task34();
        task35();

        System.out.println("\n=== Блок 6: Зубчасті та багатовимірні масиви ===");
        task36();
        task37();
        task38();
        task39();
        task40();

        scanner.close();
    }

    // ==========================================
    // Блок 1: Оголошення, ініціалізація та доступ (1–7)
    // ==========================================

    public static void task01() {
        System.out.println("\n--- Завдання 1 ---");
        int[] numbers = new int[5];

        System.out.print("Початковий масив за замовчуванням: ");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();

        numbers[0] = 10;
        numbers[2] = 30;
        numbers[numbers.length - 1] = 50;

        System.out.println("Модифікований масив: " + Arrays.toString(numbers));
    }

    public static void task02() {
        System.out.println("\n--- Завдання 2 ---");
        String[] days = {"Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця", "Субота", "Неділя"};

        System.out.println("Кількість днів: " + days.length);
        System.out.println("Перший день: " + days[0]);
        System.out.println("Останній день: " + days[days.length - 1]);

        days[5] = "Вихідний (Сб)";
        days[6] = "Вихідний (Нд)";

        System.out.println("Оновлений список днів: " + Arrays.toString(days));
    }

    public static void task03() {
        System.out.println("\n--- Завдання 3 ---");
        double[] prices = {120.50, 45.00, 890.99, 15.30, 420.00};
        System.out.println("Початкові ціни: " + Arrays.toString(prices));

        prices[2] = prices[2] * 0.8;
        System.out.println("Ціни після знижки 20% на 3-й товар: " + Arrays.toString(prices));
    }

    public static void task04(Scanner scanner) {
        System.out.println("\n--- Завдання 4 ---");
        System.out.print("Введіть число N (> 0): ");
        int n = scanner.nextInt();

        if (n > 0) {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = i + 1;
            }
            System.out.println("Згенерований масив: " + Arrays.toString(arr));
        } else {
            System.out.println("Число N має бути більше 0.");
        }
    }

    public static void task05() {
        System.out.println("\n--- Завдання 5 ---");
        int[] arr = {4, 8, 15, 16, 23, 42};
        System.out.print("Зворотний порядок: ");
        for (int i = arr.length - 1; i >= 0; i--) {
            System.out.print(arr[i] + (i > 0 ? ", " : ""));
        }
        System.out.println();
    }

    public static void task06() {
        System.out.println("\n--- Завдання 6 ---");
        int[] powersOfTwo = new int[10];
        powersOfTwo[0] = 1;
        for (int i = 1; i < powersOfTwo.length; i++) {
            powersOfTwo[i] = powersOfTwo[i - 1] * 2;
        }
        System.out.println("Перші 10 степенів двійки: " + Arrays.toString(powersOfTwo));
    }

    public static void task07() {
        System.out.println("\n--- Завдання 7 ---");
        int[] original = {3, 7, 1, 9, 4};
        int[] copy = new int[original.length];

        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }

        copy[0] = 999;
        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Copy:     " + Arrays.toString(copy));
    }

    // ==========================================
    // Блок 2: Обчислення та агрегація (8–15)
    // ==========================================

    public static void task08() {
        System.out.println("\n--- Завдання 8 ---");
        int[] grades = {95, 84, 70, 99, 65, 88, 92};
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        double avg = (double) sum / grades.length;
        System.out.printf("Сума: %d, Середній бал: %.2f\n", sum, avg);
    }

    public static void task09() {
        System.out.println("\n--- Завдання 9 ---");
        int[] temps = {18, 21, 15, 24, 13, 27, 20};
        int min = temps[0], max = temps[0];
        int minIdx = 0, maxIdx = 0;

        for (int i = 1; i < temps.length; i++) {
            if (temps[i] < min) {
                min = temps[i];
                minIdx = i;
            }
            if (temps[i] > max) {
                max = temps[i];
                maxIdx = i;
            }
        }
        System.out.println("Мін. температура: " + min + " (день " + (minIdx + 1) + ")");
        System.out.println("Макс. температура: " + max + " (день " + (maxIdx + 1) + ")");
    }

    public static void task10() {
        System.out.println("\n--- Завдання 10 ---");
        int[] data = {12, -5, 0, 43, -19, 0, -2, 8, 0, 15};
        int positive = 0, negative = 0, zeros = 0;

        for (int val : data) {
            if (val > 0) positive++;
            else if (val < 0) negative++;
            else zeros++;
        }

        System.out.println("Додатних: " + positive + ", Від'ємних: " + negative + ", Нулів: " + zeros);
    }

    public static void task11() {
        System.out.println("\n--- Завдання 11 ---");
        int[] nums = {4, 7, 12, 19, 22, 33, 40, 51, 60, 73};
        int evenCount = 0;
        int oddSum = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                evenCount++;
            } else {
                oddSum += num;
            }
        }

        System.out.println("Кількість парних: " + evenCount);
        System.out.println("Сума непарних: " + oddSum);
    }

    public static void task12(Scanner scanner) {
        System.out.println("\n--- Завдання 12 ---");
        int[] students = {1042, 2031, 3055, 4018, 5099};
        System.out.print("Введіть шуканий номер залікової книжки: ");
        int target = scanner.nextInt();
        int foundIndex = -1;

        for (int i = 0; i < students.length; i++) {
            if (students[i] == target) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex != -1) {
            System.out.println("Студента знайдено на позиції " + foundIndex);
        } else {
            System.out.println("Студента з таким номером не знайдено");
        }
    }

    public static void task13(Scanner scanner) {
        System.out.println("\n--- Завдання 13 ---");
        int[] rolls = {3, 6, 2, 6, 1, 4, 6, 5, 2, 6};
        System.out.print("Введіть число від 1 до 6: ");
        int target = scanner.nextInt();
        int count = 0;

        for (int roll : rolls) {
            if (roll == target) {
                count++;
            }
        }

        System.out.println("Число " + target + " випало " + count + " раз(ів).");
    }

    public static void task14() {
        System.out.println("\n--- Завдання 14 ---");
        int[] salaries = {18000, 25000, 32000, 15000, 41000, 29000};
        int sum = 0;
        for (int s : salaries) sum += s;
        double avg = (double) sum / salaries.length;

        System.out.printf("Середня зарплата: %.2f\n", avg);
        System.out.print("Зарплати, більші за середнє: ");
        for (int s : salaries) {
            if (s > avg) {
                System.out.print(s + " ");
            }
        }
        System.out.println();
    }

    public static void task15() {
        System.out.println("\n--- Завдання 15 ---");
        double[] prices = {42.5, 41.8, 44.1, 40.9, 43.6, 45.0};
        double min = prices[0], max = prices[0];

        for (double p : prices) {
            if (p < min) min = p;
            if (p > max) max = p;
        }

        System.out.printf("Розмах цін: %.2f\n", (max - min));
    }

    // ==========================================
    // Блок 3: Перетворення, for-each, Arrays (16–22)
    // ==========================================

    public static void task16() {
        System.out.println("\n--- Завдання 16 ---");
        String[] cities = {"Київ", "Львів", "Харків", "Одеса", "Дніпро"};
        for (String city : cities) {
            System.out.println("Ласкаво просимо до міста " + city + "!");
        }
    }

    public static void task17() {
        System.out.println("\n--- Завдання 17 ---");
        double[] salaries = {20000.0, 25000.0, 18000.0, 30000.0};
        for (int i = 0; i < salaries.length; i++) {
            salaries[i] *= 1.10;
        }
        System.out.println("Зарплати після індексації: " + Arrays.toString(salaries));
    }

    public static void task18() {
        System.out.println("\n--- Завдання 18 ---");
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println("До реверсу: " + Arrays.toString(arr));

        for (int i = 0; i < arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }

        System.out.println("Після реверсу: " + Arrays.toString(arr));
    }

    public static void task19() {
        System.out.println("\n--- Завдання 19 ---");
        int[] base = {2, 4, 6, 8, 10};
        int[] squares = new int[base.length];

        for (int i = 0; i < base.length; i++) {
            squares[i] = base[i] * base[i];
        }

        System.out.println("Base:    " + Arrays.toString(base));
        System.out.println("Squares: " + Arrays.toString(squares));
    }

    public static void task20() {
        System.out.println("\n--- Завдання 20 ---");
        int[] arr = {2, 5, 8, 12, 19, 25};
        boolean isSorted = true;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                isSorted = false;
                break;
            }
        }

        System.out.println("Масив " + Arrays.toString(arr) + " відсортований? " + isSorted);
    }

    public static void task21() {
        System.out.println("\n--- Завдання 21 ---");
        int[] a = {1, 2, 3};
        int[] b = {4, 5, 6, 7};
        int[] c = new int[a.length + b.length];

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i];
        }
        for (int i = 0; i < b.length; i++) {
            c[a.length + i] = b[i];
        }

        System.out.println("Об'єднаний масив: " + Arrays.toString(c));
    }

    public static void task22() {
        System.out.println("\n--- Завдання 22 ---");
        int[] test = {45, 12, 89, 3, 27};
        System.out.println("1. Початковий: " + Arrays.toString(test));

        Arrays.sort(test);
        System.out.println("2. Відсортований: " + Arrays.toString(test));

        Arrays.fill(test, -1);
        System.out.println("3. Заповнений -1: " + Arrays.toString(test));
    }

    // ==========================================
    // Блок 4: Двовимірні масиви (23–29)
    // ==========================================

    public static void task23() {
        System.out.println("\n--- Завдання 23 ---");
        int[][] table = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                System.out.print(table[row][col] + "\t");
            }
            System.out.println();
        }
    }

    public static void task24() {
        System.out.println("\n--- Завдання 24 ---");
        int[][] matrix = {
                {10, 20, 30, 40},
                {50, 60, 70, 80}
        };

        System.out.println("Кількість рядків: " + matrix.length);
        System.out.println("Кількість стовпчиків: " + matrix[0].length);
        System.out.println("Лівий верхній: " + matrix[0][0]);
        System.out.println("Правий верхній: " + matrix[0][matrix[0].length - 1]);
        System.out.println("Лівий нижній: " + matrix[matrix.length - 1][0]);
        System.out.println("Правий нижній: " + matrix[matrix.length - 1][matrix[0].length - 1]);
    }

    public static void task25() {
        System.out.println("\n--- Завдання 25 ---");
        int[][] grid = new int[3][4];
        int counter = 1;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = counter++;
                System.out.print(grid[r][c] + "\t");
            }
            System.out.println();
        }
    }

    public static void task26() {
        System.out.println("\n--- Завдання 26 ---");
        int[][] randomGrid = new int[4][3];

        for (int r = 0; r < randomGrid.length; r++) {
            for (int c = 0; c < randomGrid[r].length; c++) {
                randomGrid[r][c] = (int) (Math.random() * 90) + 10;
            }
        }

        System.out.println("Через Arrays.deepToString(): " + Arrays.deepToString(randomGrid));
        System.out.println("У вигляді таблиці:");
        for (int[] row : randomGrid) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    public static void task27() {
        System.out.println("\n--- Завдання 27 ---");
        int[][] multTable = new int[9][9];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                multTable[r][c] = (r + 1) * (c + 1);
                System.out.print(multTable[r][c] + "\t");
            }
            System.out.println();
        }
    }

    public static void task28() {
        System.out.println("\n--- Завдання 28 ---");
        int[][] identity = new int[5][5];

        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (r == c) identity[r][c] = 1;
                System.out.print(identity[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void task29() {
        System.out.println("\n--- Завдання 29 ---");
        int[][] matrix = new int[4][4];

        for (int[] row : matrix) Arrays.fill(row, 1);

        // 1. Занулюємо 2-й рядок (індекс 1)
        Arrays.fill(matrix[1], 0);

        // 2. Заповнюємо 3-й стовпчик (індекс 2) числом 7
        for (int r = 0; r < matrix.length; r++) {
            matrix[r][2] = 7;
        }

        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + "\t");
            }
            System.out.println();
        }
    }

    // ==========================================
    // Блок 5: Обчислення та алгоритми в 2D (30–35)
    // ==========================================

    public static void task30() {
        System.out.println("\n--- Завдання 30 ---");
        int[][] matrix = {
                {5, 10, 15},
                {20, 25, 30},
                {35, 40, 45}
        };

        int sum = 0;
        int totalElements = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                sum += matrix[r][c];
                totalElements++;
            }
        }

        double avg = (double) sum / totalElements;
        System.out.println("Сума: " + sum + ", Кількість: " + totalElements + ", Середнє: " + avg);
    }

    public static void task31() {
        System.out.println("\n--- Завдання 31 ---");
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };

        int[] rowSums = new int[matrix.length];

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                rowSums[r] += matrix[r][c];
            }
            System.out.println("Рядок " + r + ": сума = " + rowSums[r]);
        }
    }

    public static void task32() {
        System.out.println("\n--- Завдання 32 ---");
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        for (int c = 0; c < matrix[0].length; c++) {
            int colSum = 0;
            for (int r = 0; r < matrix.length; r++) {
                colSum += matrix[r][c];
            }
            System.out.println("Стовпчик " + c + ": сума = " + colSum);
        }
    }

    public static void task33() {
        System.out.println("\n--- Завдання 33 ---");
        int[][] matrix = {
                {12, -5, 43, 8},
                {-19, 99, 0, 15},
                {34, 2, 77, -1}
        };

        int max = matrix[0][0];
        int maxRow = 0, maxCol = 0;

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                if (matrix[r][c] > max) {
                    max = matrix[r][c];
                    maxRow = r;
                    maxCol = c;
                }
            }
        }

        System.out.printf("Максимум: %d у комірці [%d, %d]\n", max, maxRow, maxCol);
    }

    public static void task34() {
        System.out.println("\n--- Завдання 34 ---");
        int[][] matrix = {
                {1,  2,  3,  4},
                {5,  6,  7,  8},
                {9,  10, 11, 12},
                {13, 14, 15, 16}
        };

        int mainDiag = 0, secDiag = 0;

        for (int i = 0; i < matrix.length; i++) {
            mainDiag += matrix[i][i];
            secDiag += matrix[i][matrix.length - 1 - i];
        }

        System.out.println("Головна діагональ: " + mainDiag);
        System.out.println("Побічна діагональ: " + secDiag);
    }

    public static void task35() {
        System.out.println("\n--- Завдання 35 ---");
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] transposed = new int[3][3];

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                transposed[c][r] = matrix[r][c];
            }
        }

        System.out.println("Початкова:  " + Arrays.deepToString(matrix));
        System.out.println("Транспонована: " + Arrays.deepToString(transposed));
    }

    // ==========================================
    // Блок 6: Зубчасті та багатовимірні масиви (36–40)
    // ==========================================

    public static void task36() {
        System.out.println("\n--- Завдання 36 ---");
        int[][] ladder = new int[4][];

        for (int r = 0; r < ladder.length; r++) {
            ladder[r] = new int[r + 1];
            for (int c = 0; c < ladder[r].length; c++) {
                ladder[r][c] = r;
                System.out.print(ladder[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void task37() {
        System.out.println("\n--- Завдання 37 ---");
        String[][] weekSchedule = {
                {"Вища математика", "Основи програмування", "Фізика"},
                {"Англійська мова", "Історія України"},
                {"Основи програмування", "Основи програмування", "Бази даних", "Фізвиховання"},
                {"Дискретна математика"},
                {"Філософія", "Основи алгоритмізації", "Практикум"}
        };

        String[] days = {"Понеділок", "Вівторок", "Середа", "Четвер", "П'ятниця"};

        for (int i = 0; i < weekSchedule.length; i++) {
            System.out.println(days[i] + " (" + weekSchedule[i].length + " пар(и)): " + Arrays.toString(weekSchedule[i]));
        }
    }

    public static void task38() {
        System.out.println("\n--- Завдання 38 ---");
        int[][] pascal = new int[5][];

        for (int i = 0; i < 5; i++) {
            pascal[i] = new int[i + 1];
            pascal[i][0] = 1;
            pascal[i][i] = 1;

            for (int j = 1; j < i; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }

            for (int val : pascal[i]) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static void task39() {
        System.out.println("\n--- Завдання 39 ---");
        int[][][] cube = new int[3][3][3];

        for (int x = 0; x < 3; x++) {
            System.out.println("--- Шар " + x + " ---");
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    cube[x][y][z] = x + y + z;
                    System.out.print(cube[x][y][z] + "\t");
                }
                System.out.println();
            }
        }
    }

    public static void task40() {
        System.out.println("\n--- Завдання 40 ---");
        // 3 міста x 7 днів x 2 заміри (0 - день, 1 - ніч)
        double[][][] weather = {
                // Київ
                { {18.5, 10.0}, {19.0, 11.2}, {17.5, 9.5}, {20.0, 12.0}, {21.5, 13.0}, {22.0, 14.0}, {19.5, 10.5} },
                // Львів
                { {16.0, 8.5}, {17.0, 9.0}, {15.5, 7.0}, {18.0, 10.0}, {19.0, 11.0}, {20.0, 12.0}, {17.5, 8.0} },
                // Одеса
                { {22.0, 15.0}, {23.0, 16.0}, {21.5, 14.5}, {24.0, 17.0}, {25.0, 18.0}, {24.5, 17.5}, {23.0, 15.5} }
        };

        String[] cities = {"Київ", "Львів", "Одеса"};

        // 1. Середня денна температура по кожному з міст
        for (int c = 0; c < weather.length; c++) {
            double daySum = 0;
            for (int d = 0; d < weather[c].length; d++) {
                daySum += weather[c][d][0]; // [0] — день
            }
            System.out.printf("Середня денна температура (%s): %.2f°C\n", cities[c], (daySum / 7));
        }

        // 2. Найхолодніша ніч серед усіх міст
        double minNight = weather[0][0][1];
        String coldestCity = cities[0];
        int coldestDay = 1;

        for (int c = 0; c < weather.length; c++) {
            for (int d = 0; d < weather[c].length; d++) {
                if (weather[c][d][1] < minNight) {
                    minNight = weather[c][d][1];
                    coldestCity = cities[c];
                    coldestDay = d + 1;
                }
            }
        }

        System.out.printf("Найхолодніша ніч: %.1f°C (м. %s, день %d)\n", minNight, coldestCity, coldestDay);
    }

    
}