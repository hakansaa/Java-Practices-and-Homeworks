package Task4SmartHomeClimate;

import java.util.Scanner;

public class Task4SmartHomeClimate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ПАНЕЛЬ КЛІМАТ-КОНТРОЛЮ SMART HOME ===");

        System.out.print("Поточна температура (°C): ");
        double currentTemp = scanner.nextDouble();

        System.out.print("Бажана температура (°C): ");
        double desiredTemp = scanner.nextDouble();

        System.out.print("Вологість повітря (%): ");
        int humidity = scanner.nextInt();

        // Очищення буфера від символу нового рядка після nextInt()
        scanner.nextLine();

        System.out.print("Сезон (зима/літо/міжсезоння): ");
        String season = scanner.nextLine().trim();

        System.out.print("Режим будинку (1-Standby, 2-Eco, 3-Party, 4-Night, 5-Vacation): ");
        int mode = scanner.nextInt();

        System.out.print("Чи відчинені вікна? (true/false): ");
        boolean isWindowOpen = scanner.nextBoolean();

        System.out.println("\n------------- СТАТУС СИСТЕМИ -------------");

        // 2. Short-circuit перевірка безпеки відчинених вікон
        if (isWindowOpen && (mode != 5)) {
            System.out.println("ПОПЕРЕДЖЕННЯ: Відчинені вікна! Обігрів та кондиціонування примусово заблоковано!");
            System.out.println("==========================================");
            scanner.close();
            return;
        }

        // 3. Розрахунок скоригованої цільової температури через Modern Switch Expression з yield
        double effectiveTargetTemp = switch (mode) {
            case 1 -> desiredTemp;
            case 2 -> {
                if (season.equalsIgnoreCase("зима")) {
                    yield desiredTemp - 2.0;
                } else if (season.equalsIgnoreCase("літо")) {
                    yield desiredTemp + 2.0;
                } else {
                    yield desiredTemp;
                }
            }
            case 3 -> desiredTemp - 1.5;
            case 4 -> 19.0;
            case 5 -> {
                if (season.equalsIgnoreCase("зима")) {
                    yield 12.0;
                } else {
                    yield 28.0;
                }
            }
            default -> desiredTemp;
        };

        // Текстова назва режиму для звіту
        String modeName = switch (mode) {
            case 1 -> "Standby (Звичайний)";
            case 2 -> "Eco (Енергозбереження)";
            case 3 -> "Party (Гості)";
            case 4 -> "Night (Ніч)";
            case 5 -> "Vacation (Відпустка)";
            default -> "Невідомий";
        };

        // 4. Ухвалення рішення щодо терморегуляції (if-else-if ladder)
        String climateStatus;
        if ((currentTemp - effectiveTargetTemp) > 0.5) {
            climateStatus = "Кондиціонер УВІМКНЕНО (Охолодження)";
        } else if ((effectiveTargetTemp - currentTemp) > 0.5) {
            climateStatus = "Опалення УВІМКНЕНО (Обігрів)";
        } else {
            climateStatus = "Температура в зоні комфорту (Кліматичні прилади в очікуванні)";
        }

        // 5. Керування вологістю за допомогою тернарного оператора
        String humidityStatus = (humidity < 40) ? "Зволожувач УВІМКНЕНО" :
                (humidity > 65) ? "Осушувач УВІМКНЕНО" : "Вологість оптимальна";

        // 6. Формування підсумкового звіту
        System.out.printf("Режим роботи:              %s%n", modeName);
        System.out.printf("Цільова температура:       %.2f°C%n", effectiveTargetTemp);
        System.out.printf("Поточна температура:       %.2f°C%n", currentTemp);
        System.out.printf("Стан терморегуляції:       %s%n", climateStatus);
        System.out.printf("Стан мікроклімату:         %s (вологість %d%%)%n", humidityStatus, humidity);
        System.out.printf("Енергоспоживання:          %s%n", (mode == 2 || mode == 5) ? "Оптимізоване" : "Стандартне");
        System.out.println("==========================================");

        scanner.close();
    }
}