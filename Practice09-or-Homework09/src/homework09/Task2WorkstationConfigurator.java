///
package homework09;

import java.util.Arrays;

public class Task2WorkstationConfigurator {
    public static void main(String[] args) {
        Motherboard board = new Motherboard("ASUS ROG B650", "AM5", 4);
        PowerSupplyUnit psu = new PowerSupplyUnit("Seasonic 750W Gold", 750, 0.90);
        WorkstationPC pc = new WorkstationPC("DeepLearning-Rig", board, psu);
        pc.installComponent(new ProcessorUnit("Intel Core i9", 20000, "LGA1700", 24, 5.8, 253));
        pc.installComponent(new ProcessorUnit("AMD Ryzen 9 7950X", 24000, "AM5", 16, 5.7, 170));
        pc.installComponent(new GraphicsAccelerator("NVIDIA RTX 4080 Super", 48000, 16, 552, 320));
        int[] samples = {7000, 7400, 7450};
        NvmeSsdDrive ssd = new NvmeSsdDrive("Samsung 990 Pro 2TB", 6900, 2048, 7450, samples);
        samples[0] = 1;
        int[] copied = ssd.getSpeedSamples();
        copied[1] = 1;
        System.out.println("Захищені тести SSD: " + Arrays.toString(ssd.getSpeedSamples()));
        pc.installComponent(ssd);
        pc.installComponent(new GraphicsAccelerator("Надпотужний GPU", 50000, 48, 1000, 500));
        System.out.println(pc);
    }
}

class Motherboard {
    private final String model, socketType;
    private final int maxExpansionSlots;
    public Motherboard(String model, String socketType, int maxExpansionSlots) {
        if (maxExpansionSlots < 1 || maxExpansionSlots > 8) throw new IllegalArgumentException("Слоти: 1..8");
        this.model = model; this.socketType = socketType; this.maxExpansionSlots = maxExpansionSlots;
    }
    public String getModel() { return model; }
    public String getSocketType() { return socketType; }
    public int getMaxExpansionSlots() { return maxExpansionSlots; }
    public boolean isSocketCompatible(String cpuSocket) {
        return cpuSocket != null && socketType.equalsIgnoreCase(cpuSocket);
    }
}

class PowerSupplyUnit {
    private final String model;
    private final int maxOutputWatts;
    private final double efficiencyCoefficient;
    public PowerSupplyUnit(String model, int maxOutputWatts, double efficiencyCoefficient) {
        if (maxOutputWatts <= 0 || !Double.isFinite(efficiencyCoefficient)
                || efficiencyCoefficient < 0.80 || efficiencyCoefficient > 0.96)
            throw new IllegalArgumentException("Некоректні параметри БЖ");
        this.model = model; this.maxOutputWatts = maxOutputWatts;
        this.efficiencyCoefficient = efficiencyCoefficient;
    }
    public String getModel() { return model; }
    public double getEfficiencyCoefficient() { return efficiencyCoefficient; }
    public int getSafeContinuousWatts() { return (int) (maxOutputWatts * 0.85); }
    public double calculateWallPowerDraw(int internalLoadWatts) {
        if (internalLoadWatts < 0) throw new IllegalArgumentException("Навантаження не може бути від'ємним");
        return internalLoadWatts / efficiencyCoefficient;
    }
}

abstract class AbstractHardwareComponent {
    private final String modelName;
    private final double priceUah;
    public AbstractHardwareComponent(String modelName, double priceUah) {
        if (!Double.isFinite(priceUah) || priceUah < 0) throw new IllegalArgumentException("Некоректна ціна");
        this.modelName = modelName; this.priceUah = priceUah;
    }
    public String getModelName() { return modelName; }
    public double getPriceUah() { return priceUah; }
    public abstract int getPowerWatts();
    public abstract double getBenchmarkScore();
    public abstract String getCategory();
    @Override public String toString() {
        return String.format("[%s] %s (%d Вт | Score: %.1f | %.2f грн)",
                getCategory(), modelName, getPowerWatts(), getBenchmarkScore(), priceUah);
    }
}

class ProcessorUnit extends AbstractHardwareComponent {
    private final String socket;
    private final int cores, tdpWatts;
    private final double boostClockGhz;
    public ProcessorUnit(String name, double price, String socket, int cores, double boostClockGhz, int tdpWatts) {
        super(name, price);
        if (cores <= 0 || !Double.isFinite(boostClockGhz) || boostClockGhz <= 0 || tdpWatts <= 0)
            throw new IllegalArgumentException("Некоректні параметри CPU");
        this.socket = socket; this.cores = cores; this.boostClockGhz = boostClockGhz; this.tdpWatts = tdpWatts;
    }
    public String getSocket() { return socket; }
    @Override public int getPowerWatts() { return tdpWatts; }
    @Override public double getBenchmarkScore() { return cores * boostClockGhz * 120.0; }
    @Override public String getCategory() { return "CPU"; }
}

class GraphicsAccelerator extends AbstractHardwareComponent {
    private final int vramGb, tensorCores, boardPowerWatts;
    public GraphicsAccelerator(String name, double price, int vramGb, int tensorCores, int boardPowerWatts) {
        super(name, price);
        if (vramGb <= 0 || tensorCores < 0 || boardPowerWatts <= 0)
            throw new IllegalArgumentException("Некоректні параметри GPU");
        this.vramGb = vramGb; this.tensorCores = tensorCores; this.boardPowerWatts = boardPowerWatts;
    }
    @Override public int getPowerWatts() { return boardPowerWatts; }
    @Override public double getBenchmarkScore() { return vramGb * 250.0 + tensorCores * 1.5; }
    @Override public String getCategory() { return "GPU"; }
}

class NvmeSsdDrive extends AbstractHardwareComponent {
    private final int capacityGb, readSpeedMbSec;
    private final int[] speedSamples;
    public NvmeSsdDrive(String name, double price, int capacityGb, int readSpeedMbSec, int[] speedSamples) {
        super(name, price);
        if (capacityGb <= 0 || readSpeedMbSec <= 0) throw new IllegalArgumentException("Некоректні параметри SSD");
        this.capacityGb = capacityGb; this.readSpeedMbSec = readSpeedMbSec;
        this.speedSamples = speedSamples == null ? new int[0] : speedSamples.clone();
    }
    public int[] getSpeedSamples() { return speedSamples.clone(); }
    @Override public int getPowerWatts() { return 12; }
    @Override public double getBenchmarkScore() { return readSpeedMbSec * 0.25; }
    @Override public String getCategory() { return "NVMe SSD"; }
}

class WorkstationPC {
    private final String buildName;
    private final Motherboard motherboard;
    private final PowerSupplyUnit psu;
    private final AbstractHardwareComponent[] components;
    private int componentCount;
    public WorkstationPC(String buildName, Motherboard motherboard, PowerSupplyUnit psu) {
        if (motherboard == null || psu == null) throw new IllegalArgumentException("Плата та БЖ обов'язкові");
        this.buildName = buildName; this.motherboard = motherboard; this.psu = psu;
        this.components = new AbstractHardwareComponent[motherboard.getMaxExpansionSlots()];
    }
    public boolean installComponent(AbstractHardwareComponent comp) {
        if (comp == null || componentCount >= components.length) {
            System.out.println("Відмова: немає вільного слота або компонент відсутній");
            return false;
        }
        if (comp instanceof ProcessorUnit) {
            ProcessorUnit cpu = (ProcessorUnit) comp;
            if (!motherboard.isSocketCompatible(cpu.getSocket())) {
                System.out.printf("Відмова: %s (%s) несумісний із %s (%s)%n",
                        comp.getModelName(), cpu.getSocket(), motherboard.getModel(), motherboard.getSocketType());
                return false;
            }
            for (int i = 0; i < componentCount; i++) {
                if (components[i] instanceof ProcessorUnit) {
                    System.out.println("Відмова: процесор уже встановлено");
                    return false;
                }
            }
        }
        if (getTotalPowerWatts() + comp.getPowerWatts() > psu.getSafeContinuousWatts()) {
            System.out.println("Відмова: перевищено безпечну потужність блока живлення!");
            return false;
        }
        components[componentCount++] = comp;
        System.out.println("Встановлено: " + comp);
        return true;
    }
    public int getTotalPowerWatts() {
        int sum = 0;
        for (int i = 0; i < componentCount; i++) sum += components[i].getPowerWatts();
        return sum;
    }
    public double getTotalSystemScore() {
        double sum = 0;
        for (int i = 0; i < componentCount; i++) sum += components[i].getBenchmarkScore();
        return sum;
    }
    public double getTotalBuildPrice() {
        double sum = 0;
        for (int i = 0; i < componentCount; i++) sum += components[i].getPriceUah();
        return sum;
    }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("%n[ПАСПОРТ ЗБІРКИ '%s']%n", buildName));
        sb.append(String.format("Материнська плата: %s (Socket: %s, Слотів: %d/%d)%n",
                motherboard.getModel(), motherboard.getSocketType(), componentCount, components.length));
        sb.append(String.format("БЖ: %s (Безпечний ліміт: %d Вт, ККД: %.0f%%)%n",
                psu.getModel(), psu.getSafeContinuousWatts(), psu.getEfficiencyCoefficient() * 100));
        for (int i = 0; i < componentCount; i++) sb.append("  ").append(components[i]).append("\n");
        sb.append(String.format("Споживання: %d Вт (з розетки: %.2f Вт)%n",
                getTotalPowerWatts(), psu.calculateWallPowerDraw(getTotalPowerWatts())));
        sb.append(String.format("Продуктивність: %.2f балів%nВартість: %.2f грн%n",
                getTotalSystemScore(), getTotalBuildPrice()));
        return sb.toString();
    }
}