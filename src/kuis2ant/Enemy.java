/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kuis2ant;

/**
 *
 * @author ADVAn
 */

public class Enemy extends GameEntity { // Pastikan ada 'public'
    // Konstruktor memanggil konstruktor dari kelas induk (GameEntity)
    public Enemy(String name, int hp, int ap, int level) {
        super(name, hp, ap, level);
    }

    @Override
    public String attack(GameEntity target) {
        // Hitungan damage Enemy: Hanya AP
        int damage = this.getAttackPower();
        target.setCurrentHp(target.getCurrentHp() - damage);
        
        String result = String.format("%s (Musuh) menyerang %s. Damage: %d", 
                                      this.getName(), target.getName(), damage);
        
        if (target.getCurrentHp() <= 0) {
            result += String.format("\n%s dikalahkan!", target.getName());
        }
        return result;
    }
}