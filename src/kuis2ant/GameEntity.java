package kuis2ant;

import java.util.ArrayList;
import java.util.List;

/**
 * Kelas dasar untuk Hero, Enemy, dan Boss.
 * Menyimpan data dasar seperti nama, HP, dan Attack Power (AP).
 */
public abstract class GameEntity {
    private String name;
    private int maxHp;
    private int currentHp;
    private int attackPower;
    private int level;
    private List<Skill> skills;

    public GameEntity(String name, int hp, int ap, int level) {
        this.name = name;
        this.maxHp = hp;
        this.currentHp = hp;
        this.attackPower = ap;
        this.level = level;
        this.skills = new ArrayList<>();
    }

    // Metode abstrak untuk perilaku unik (misalnya, simulasi attack)
    public abstract String attack(GameEntity target);

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getMaxHp() { return maxHp; }
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }
    public int getCurrentHp() { return currentHp; }
    public void setCurrentHp(int currentHp) { this.currentHp = currentHp; }
    public int getAttackPower() { return attackPower; }
    public void setAttackPower(int attackPower) { this.attackPower = attackPower; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public List<Skill> getSkills() { return skills; }

    public void addSkill(Skill skill) {
        this.skills.add(skill);
    }
    
    public void removeSkill(String skillName) {
        this.skills.removeIf(s -> s.getName().equalsIgnoreCase(skillName));
    }
    
    // Representasi string untuk tampilan di GUI
    public String getStatus() {
        return String.format("%s (Lv %d, HP: %d/%d, AP: %d)", 
                             name, level, currentHp, maxHp, attackPower);
    }
}