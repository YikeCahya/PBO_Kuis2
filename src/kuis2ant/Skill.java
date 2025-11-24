package kuis2ant;

/**
 * Kelas untuk merepresentasikan skill dengan multiplier damage.
 */
public class Skill {
    private String name;
    private double multiplier; // Contoh: 1.5 untuk 150% damage

    public Skill(String name, double multiplier) {
        this.name = name;
        this.multiplier = multiplier;
    }

    public String getName() { return name; }
    public double getMultiplier() { return multiplier; }

    @Override
    public String toString() {
        return String.format("%s (x%.1f)", name, multiplier);
    }
}
