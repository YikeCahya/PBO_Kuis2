/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kuis2ant;

/**
 *
 * @author ADVAn
 */

public class Hero extends GameEntity { // Pastikan ada 'public'
    // Konstruktor memanggil konstruktor dari kelas induk (GameEntity)
    public Hero(String name, int hp, int ap, int level) {
        super(name, hp, ap, level);
    }

    @Override
    public String attack(GameEntity target) {
        // Hitungan damage Hero: AP + (Level * 2)
        int damage = this.getAttackPower() + (this.getLevel() * 2);
        
        // Asumsi Hero menggunakan skill pertama jika ada, untuk simulasi sederhana.
        if (!this.getSkills().isEmpty()) {
            Skill usedSkill = this.getSkills().get(0);
            damage = (int) (damage * usedSkill.getMultiplier());
            
            target.setCurrentHp(target.getCurrentHp() - damage);
            
            String result = String.format("%s menyerang %s menggunakan skill %s (x%.1f)! Total Damage: %d", 
                                          this.getName(), target.getName(), usedSkill.getName(), usedSkill.getMultiplier(), damage);
            
            if (target.getCurrentHp() <= 0) {
                result += String.format("\n%s dikalahkan!", target.getName());
            }
            return result;
        }
        
        // Jika tidak ada skill, gunakan serangan dasar
        target.setCurrentHp(target.getCurrentHp() - damage);
        
        String result = String.format("%s menyerang %s dengan serangan dasar! Total Damage: %d", 
                                      this.getName(), target.getName(), damage);
        
        if (target.getCurrentHp() <= 0) {
            result += String.format("\n%s dikalahkan!", target.getName());
        }
        return result;
    }
}