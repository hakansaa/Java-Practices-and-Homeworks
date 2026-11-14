package homework;

import java.util.Locale;

/** Модель артефакту. Допоміжний клас зберігається у тому самому файлі. */
public class Artifact {
    private String name;
    private int costKarbovanets;
    private double radiationLevel;

    public Artifact(String name, int costKarbovanets, double radiationLevel) {
        this.name = name;
        this.costKarbovanets = costKarbovanets;
        this.radiationLevel = radiationLevel;
    }

    public String getName() { return name; }
    public int getCostKarbovanets() { return costKarbovanets; }
    public double getRadiationLevel() { return radiationLevel; }

    public boolean isRadioactiveDud(double maxSafeRadiation) {
        return radiationLevel > maxSafeRadiation || costKarbovanets <= 0;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "[Артефакт: '%s' | Ціна: %d крб | Радіація: %.1f мкЗв/год]",
                name, costKarbovanets, radiationLevel);
    }
}
