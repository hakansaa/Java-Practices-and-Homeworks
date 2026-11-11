import java.util.Scanner;

public class Task5RobloxPetSimulator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Каталог вихованців та їх характеристики
        String[] petNames = {"Dog", "Cat", "Dragon", "Unicorn", "Huge Titanic Cat"};
        int[] petPowers = {15, 25, 120, 350, 2000};
        // Шанси випадання у відсотках (загальна сума = 100%)
        // Dog: 1-45 (45%), Cat: 46-75 (30%), Dragon: 76-90 (15%), Unicorn: 91-98 (8%), Titanic: 99-100 (2%)
        int[] dropChances = {45, 30, 15, 8, 2};

        // Інвентар гравця (максимум 8 слотів)
        String[] inventoryNames = new String[8];
        int[] inventoryPowers = new int[8];
        int petCount = 0; // Лічильник заповнених слотів

        int coins = 300; // Початковий баланс
        final int EGG_PRICE = 100;

        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n======================================================");
            System.out.println("           🐾 ROBLOX: PET SIMULATOR 🐾");
            System.out.println("======================================================");
            System.out.printf("Баланс: %d монет | Вихованців у рюкзаку: %d/%d\n\n", coins, petCount, inventoryNames.length);
            System.out.println("1 - Відкрити чарівне яйце (100 монет)");
            System.out.println("2 - Відкрити рюкзак-інвентар");
            System.out.println("3 - Оцінити міць команди");
            System.out.println("4 - Фармити монети (+50..150 монет)");
            System.out.println("0 - Вийти з гри");
            System.out.print("Ваш вибір: ");

            if (!scanner.hasNextInt()) {
                System.out.println("❌ Помилка! Введіть число від 0 до 4.");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println();
                    if (coins < EGG_PRICE) {
                        System.out.println("❌ Нестача монет! Вартість яйця — " + EGG_PRICE + " монет. Пофарміть монети (пункт 4).");
                    } else if (petCount >= inventoryNames.length) {
                        System.out.println("⚠️️ Рюкзак переповнений! Продайте або видаліть зайвих петів (максимум " + inventoryNames.length + " слотів).");
                    } else {
                        coins -= EGG_PRICE;
                        System.out.println("🥚 Трісь! Яйце розколюється...");

                        // Визначення випавшого пета (рандом від 1 до 100)
                        int roll = (int) (Math.random() * 100) + 1;
                        int selectedIndex = 0;
                        int cumulativeChance = 0;

                        for (int i = 0; i < dropChances.length; i++) {
                            cumulativeChance += dropChances[i];
                            if (roll <= cumulativeChance) {
                                selectedIndex = i;
                                break;
                            }
                        }

                        String droppedPet = petNames[selectedIndex];
                        int droppedPower = petPowers[selectedIndex];

                        // Анімоване/красиве повідомлення про випадіння
                        if (selectedIndex == 4) { // Huge Titanic Cat
                            System.out.println("⭐⭐⭐ НЕЙМОВІРНО! СУПЕР-ДЖЕКПОТ! ⭐⭐⭐");
                            System.out.println("💎 " + droppedPet + "! (Сила: " + droppedPower + ")");
                        } else if (selectedIndex >= 2) { // Rare/Epic
                            System.out.println("✨ ВАМ ВИПАВ: " + droppedPet + "! (Сила: " + droppedPower + ")");
                        } else {
                            System.out.println("🐾 Вам випав: " + droppedPet + " (Сила: " + droppedPower + ")");
                        }

                        // Додавання у рюкзак
                        inventoryNames[petCount] = droppedPet;
                        inventoryPowers[petCount] = droppedPower;
                        petCount++;

                        System.out.println("Вихованця додано в інвентар! Залишок монет: " + coins);
                    }
                    break;

                case 2:
                    System.out.println("\n--- 🎒 ВАШ РЮКЗАК (ІНВЕНТАР) ---");
                    if (petCount == 0) {
                        System.out.println("Рюкзак порожній. Відкрийте яйце, щоб отримати першого вихованця!");
                    } else {
                        for (int i = 0; i < petCount; i++) {
                            System.out.printf("[Слот %d] %-18s | Сила: %d\n", (i + 1), inventoryNames[i], inventoryPowers[i]);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- ⚔️ СТАТИСТИКА ВАШОЇ КОМАНДИ ---");
                    if (petCount == 0) {
                        System.out.println("У вас немає вихованців для розрахунку сили!");
                    } else {
                        int totalPower = 0;
                        int maxPower = inventoryPowers[0];
                        String bestPet = inventoryNames[0];

                        for (int i = 0; i < petCount; i++) {
                            totalPower += inventoryPowers[i];
                            if (inventoryPowers[i] > maxPower) {
                                maxPower = inventoryPowers[i];
                                bestPet = inventoryNames[i];
                            }
                        }

                        System.out.println("Кількість вихованців:   " + petCount + " шт.");
                        System.out.println("Загальна сила команди:  " + totalPower + " очок шкоди");
                        System.out.println("🔥 Найсильніший улюбленець: " + bestPet + " (Сила: " + maxPower + ")");
                    }
                    break;

                case 4:
                    int farmedCoins = (int) (Math.random() * 101) + 50; // Від 50 до 150
                    coins += farmedCoins;
                    System.out.println("\n⛏️ Ви розбили купу монет на локації та заробили +" + farmedCoins + " монет!");
                    System.out.println("Поточний баланс: " + coins + " монет.");
                    break;

                case 0:
                    System.out.println("\nДякуємо за гру в Roblox Pet Simulator! До зустрічі!");
                    isRunning = false;
                    break;

                default:
                    System.out.println("❌ Некоректний вибір. Оберіть пункт від 0 до 4.");
                    break;
            }
        }

        scanner.close();
    }
}