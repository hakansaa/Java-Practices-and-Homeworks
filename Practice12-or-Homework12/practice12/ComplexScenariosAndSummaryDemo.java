package semester1.practice12;

import java.util.*;

public class ComplexScenariosAndSummaryDemo {

    // Завдання 33: Граф соціальної мережі
    public static Set<String> mutualFriends(Set<String> u1, Set<String> u2) {
        return SetTheoryAndHashContractDemo.intersection(u1, u2);
    }

    public static Set<String> suggestedFriends(String user, Set<String> myFriends, Set<String> friendOfFriendSet) {
        Set<String> res = new HashSet<>(friendOfFriendSet);
        res.removeAll(myFriends);
        res.remove(user);
        return res;
    }

    public static int uniqueNetworkSize(List<Set<String>> allGroups) {
        Set<String> all = new HashSet<>();
        for (Set<String> g : allGroups) all.addAll(g);
        return all.size();
    }

    public static void task33SocialGraph() {
        System.out.println("\n=== Завдання 33: Соціальна мережа ===");
        Map<String, Set<String>> friends = new LinkedHashMap<>();
        friends.put("Alex", Set.of("Maria", "Bohdan"));
        friends.put("Maria", Set.of("Alex", "Olena", "Taras"));
        friends.put("Bohdan", Set.of("Alex", "Olena"));
        friends.put("Olena", Set.of("Maria", "Bohdan", "Taras"));

        Set<String> fof = new HashSet<>();
        for (String f : friends.get("Alex")) {
            fof.addAll(friends.getOrDefault(f, Set.of()));
        }

        System.out.println("Спільні друзі Maria і Bohdan: " + mutualFriends(friends.get("Maria"), friends.get("Bohdan")));
        System.out.println("Рекомендовані друзі для Alex: " + suggestedFriends("Alex", friends.get("Alex"), fof));
        System.out.println("Унікальних учасників у мережі: " + uniqueNetworkSize(new ArrayList<>(friends.values())));
    }

    // Завдання 34: Матричний індекс унікальних значень та нулів
    public static void task34MatrixIndex() {
        System.out.println("\n=== Завдання 34: Матричний індекс ===");
        int[][] matrix = {
                {5, 0, 8, 2, 10},
                {3, 7, 0, 5, 12},
                {8, 1, 4, 9, 0},
                {2, 0, 10, 6, 3}
        };

        TreeSet<Integer> allUnique = new TreeSet<>();
        Set<Integer> firstRow = new HashSet<>();
        Set<Integer> lastRow = new HashSet<>();
        List<String> zeroCoords = new ArrayList<>();

        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                int val = matrix[r][c];
                allUnique.add(val);
                if (r == 0) firstRow.add(val);
                if (r == matrix.length - 1) lastRow.add(val);
                if (val == 0) zeroCoords.add("row=" + r + ", col=" + c);
            }
        }

        Set<Integer> commonFirstLast = SetTheoryAndHashContractDemo.intersection(firstRow, lastRow);
        ListIterator<String> lit = zeroCoords.listIterator();
        while (lit.hasNext()) {
            lit.set("[" + lit.next() + "]");
        }

        System.out.println("Унікальні елементи (TreeSet): " + allUnique);
        System.out.println("Спільні у 1-му та останньому рядках: " + commonFirstLast);
        System.out.println("Координати нулів: " + zeroCoords);
    }

    // Завдання 35: Антиспам-конвеєр
    static final class EmailMessage {
        private final String id;
        private final String senderDomain;
        private final String subject;
        private final double spamScore;

        EmailMessage(String id, String senderDomain, String subject, double spamScore) {
            this.id = id;
            this.senderDomain = senderDomain;
            this.subject = subject;
            this.spamScore = spamScore;
        }

        @Override
        public String toString() { return id + "@" + senderDomain + " (" + subject + ")"; }
    }

    public static void task35AntiSpamPipeline() {
        System.out.println("\n=== Завдання 35: Антиспам-конвеєр ===");
        Set<String> blacklistedDomains = Set.of("spam-zone.biz", "phish-mail.ru");
        List<EmailMessage> inbox = new ArrayList<>(List.of(
                new EmailMessage("M-1", "university.edu", "Розклад занять", 0.05),
                new EmailMessage("M-2", "spam-zone.biz", "Виграш", 0.95),
                new EmailMessage("M-3", "work.io", "Звіт", 0.10),
                new EmailMessage("M-1", "university.edu", "Дублікат листа", 0.05),
                new EmailMessage("M-4", "promo.net", "Підозріле посилання", 0.85)
        ));

        Set<String> seenIds = new HashSet<>();
        int blockedDomain = 0, blockedSpam = 0, blockedDup = 0;

        Iterator<EmailMessage> it = inbox.iterator();
        while (it.hasNext()) {
            EmailMessage m = it.next();
            if (blacklistedDomains.contains(m.senderDomain)) {
                it.remove();
                blockedDomain++;
            } else if (m.spamScore >= 0.8) {
                it.remove();
                blockedSpam++;
            } else if (!seenIds.add(m.id)) {
                it.remove();
                blockedDup++;
            }
        }

        System.out.printf("Доставлено: %d %s | Відфільтровано: домен=%d, спам=%d, дублікат=%d%n",
                inbox.size(), inbox, blockedDomain, blockedSpam, blockedDup);
    }

    // Завдання 36: Об'єднання інтервалів у TreeSet
    static final class Interval implements Comparable<Interval> {
        private final double start;
        private final double end;

        Interval(double start, double end) {
            if (start > end) throw new IllegalArgumentException("start <= end");
            this.start = start;
            this.end = end;
        }

        double length() { return end - start; }

        @Override
        public int compareTo(Interval other) {
            int cmp = Double.compare(this.start, other.start);
            return cmp != 0 ? cmp : Double.compare(this.end, other.end);
        }

        @Override
        public String toString() { return "[" + start + "; " + end + "]"; }
    }

    public static void task36MergeIntervals() {
        System.out.println("\n=== Завдання 36: Об'єднання інтервалів ===");
        TreeSet<Interval> sorted = new TreeSet<>(List.of(
                new Interval(1.0, 4.0),
                new Interval(2.0, 6.0),
                new Interval(8.0, 10.0),
                new Interval(9.0, 12.0),
                new Interval(1.0, 4.0)
        ));

        List<Interval> merged = new ArrayList<>();
        Iterator<Interval> it = sorted.iterator();
        if (it.hasNext()) {
            Interval current = it.next();
            while (it.hasNext()) {
                Interval next = it.next();
                if (next.start <= current.end) {
                    current = new Interval(current.start, Math.max(current.end, next.end));
                } else {
                    merged.add(current);
                    current = next;
                }
            }
            merged.add(current);
        }

        double totalLen = 0;
        for (Interval iv : merged) totalLen += iv.length();
        System.out.println("Об'єднані інтервали: " + merged + ", сумарна довжина = " + totalLen);
    }

    // Завдання 37: Диспетчер авіарейсів
    interface InternationalFlight {
        String getDestinationCountry();
    }

    static abstract class AbstractFlight {
        private final String flightCode;
        private final int delayMinutes;

        AbstractFlight(String flightCode, int delayMinutes) {
            this.flightCode = flightCode;
            this.delayMinutes = delayMinutes;
        }

        String getFlightCode() { return flightCode; }
        int getDelayMinutes() { return delayMinutes; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof AbstractFlight other)) return false;
            return Objects.equals(flightCode, other.flightCode);
        }

        @Override
        public int hashCode() { return Objects.hashCode(flightCode); }
    }

    static class DomesticFlight extends AbstractFlight {
        DomesticFlight(String code, int delay) { super(code, delay); }
    }

    static class InternationalCharterFlight extends AbstractFlight implements InternationalFlight {
        private final String country;
        InternationalCharterFlight(String code, int delay, String country) {
            super(code, delay);
            this.country = country;
        }
        @Override public String getDestinationCountry() { return country; }
    }

    public static void task37FlightsDispatcher() {
        System.out.println("\n=== Завдання 37: Диспетчер авіарейсів ===");
        Set<AbstractFlight> schedule = new LinkedHashSet<>(List.of(
                new DomesticFlight("PS-101", 15),
                new InternationalCharterFlight("LH-402", 210, "Germany"), // затримка > 180 -> видалимо
                new InternationalCharterFlight("LO-754", 30, "Poland"),
                new InternationalCharterFlight("BA-220", 10, "United Kingdom")
        ));

        Iterator<AbstractFlight> it = schedule.iterator();
        while (it.hasNext()) {
            if (it.next().getDelayMinutes() > 180) {
                it.remove();
            }
        }

        Set<String> countries = new TreeSet<>();
        for (AbstractFlight f : schedule) {
            if (f instanceof InternationalFlight intl) {
                countries.add(intl.getDestinationCountry());
            }
        }
        System.out.println("Активних рейсів: " + schedule.size() + ", країни призначення: " + countries);
    }

    // Завдання 38: Крипто-портфель та арбітраж
    static final class TradeOrder {
        private final String orderId;
        private final String pair;
        private final double price;
        private final double volume;

        TradeOrder(String orderId, String pair, double price, double volume) {
            this.orderId = orderId;
            this.pair = pair;
            this.price = price;
            this.volume = volume;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TradeOrder other)) return false;
            return Objects.equals(orderId, other.orderId);
        }

        @Override
        public int hashCode() { return Objects.hashCode(orderId); }
    }

    public static void task38CryptoArbitrage() {
        System.out.println("\n=== Завдання 38: Крипто-арбітраж та ордери ===");
        Set<String> binancePairs = Set.of("BTC/USDT", "ETH/USDT", "SOL/USDT");
        Set<String> krakenPairs = Set.of("BTC/USDT", "ETH/USDT", "DOT/USDT");

        Set<String> arbPairs = SetTheoryAndHashContractDemo.intersection(binancePairs, krakenPairs);
        Set<String> exclusivePairs = SetTheoryAndHashContractDemo.symmetricDifference(binancePairs, krakenPairs);

        List<TradeOrder> orders = new ArrayList<>(List.of(
                new TradeOrder("O-1", "BTC/USDT", 65000.0, 0.5),
                new TradeOrder("O-2", "SOL/USDT", 150.0, 10.0), // не в arbPairs
                new TradeOrder("O-3", "ETH/USDT", 3200.0, 2.0),
                new TradeOrder("O-4", "BTC/USDT", 65000.0, 0.0)  // volume <= 0
        ));

        Iterator<TradeOrder> it = orders.iterator();
        double liquidity = 0;
        while (it.hasNext()) {
            TradeOrder o = it.next();
            if (!arbPairs.contains(o.pair) || o.volume <= 0) {
                it.remove();
            } else {
                liquidity += o.price * o.volume;
            }
        }

        System.out.println("Арбітражні пари: " + arbPairs + ", ексклюзивні: " + exclusivePairs);
        System.out.printf("Валідних ордерів: %d, сумарна ліквідність: %.2f%n", orders.size(), liquidity);
    }

    // Завдання 39: Ігрова Гільдія RPG
    interface Poisonable {
        boolean isPoisoned();
        void tickPoison();
    }

    interface Regenerating {
        void healTurn();
    }

    static abstract class RpgHero {
        private final String name;
        protected int hp;
        private final Set<String> activeBuffs = new HashSet<>();

        RpgHero(String name, int hp) {
            this.name = name;
            this.hp = hp;
        }

        String getName() { return name; }
        int getHp() { return hp; }
        boolean addBuff(String buff) { return activeBuffs.add(buff); }
        Set<String> getActiveBuffs() { return activeBuffs; }
    }

    static class Warrior extends RpgHero implements Regenerating {
        Warrior(String name, int hp) { super(name, hp); }
        @Override public void healTurn() { hp += 10; }
    }

    static class Mage extends RpgHero implements Poisonable {
        private boolean poisoned;
        Mage(String name, int hp, boolean poisoned) { super(name, hp); this.poisoned = poisoned; }
        @Override public boolean isPoisoned() { return poisoned; }
        @Override public void tickPoison() { hp -= 25; }
    }

    static class Ranger extends RpgHero implements Poisonable, Regenerating {
        private boolean poisoned;
        Ranger(String name, int hp, boolean poisoned) { super(name, hp); this.poisoned = poisoned; }
        @Override public boolean isPoisoned() { return poisoned; }
        @Override public void tickPoison() { hp -= 15; }
        @Override public void healTurn() { hp += 5; }
    }

    public static void task39RpgGuild() {
        System.out.println("\n=== Завдання 39: RPG Гільдія (3 ходи) ===");
        List<RpgHero> party = new ArrayList<>(List.of(
                new Warrior("Торін", 90),
                new Mage("Ліра", 60, true),
                new Ranger("Ельвін", 70, true)
        ));

        for (int turn = 1; turn <= 3; turn++) {
            System.out.println("Хід " + turn + ":");
            Iterator<RpgHero> it = party.iterator();
            while (it.hasNext()) {
                RpgHero hero = it.next();
                hero.addBuff("HASTE");
                hero.addBuff("HASTE"); // дублікат бафа ігнорується Set
                if (hero instanceof Poisonable p && p.isPoisoned()) p.tickPoison();
                if (hero instanceof Regenerating r) r.healTurn();

                if (hero.getHp() <= 0) {
                    System.out.println("  " + hero.getName() + " вибуває (HP <= 0)");
                    it.remove();
                } else {
                    System.out.println("  " + hero.getName() + ": HP=" + hero.getHp() + ", бафи=" + hero.getActiveBuffs());
                }
            }
        }
    }

    // Завдання 40: Фінальний інтеграційний підсумок
    public static void runFinalSummary() {
        System.out.println("\n=== Завдання 40: Фінальний підсумок Практичної роботи №12 ===");
        List<String> rawItems = List.of("A", "B", "A", "C", "B", "D");
        Set<String> uniqueItems = new LinkedHashSet<>(rawItems);
        Set<String> extraItems = Set.of("C", "E");

        Set<String> combined = SetTheoryAndHashContractDemo.union(uniqueItems, extraItems);
        Iterator<String> it = combined.iterator();
        while (it.hasNext()) {
            if ("B".equals(it.next())) it.remove();
        }
        System.out.println("Фінальна множина після union та Iterator.remove('B'): " + combined);
        System.out.println("Перевірте збірку: mvn clean test-compile та git push перед наступною парою!");
    }
}