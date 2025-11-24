package kuis2ant;

/**
 * Implementasi konkret untuk Hero.
 * Hero menyerang dengan damage dasar + bonus dari level.
 */
class Hero extends GameEntity {
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

/**
 * Implementasi konkret untuk Enemy (bisa juga Boss).
 * Enemy menyerang dengan damage dasar (hanya AP).
 */
class Enemy extends GameEntity {
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
