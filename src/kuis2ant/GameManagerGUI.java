package kuis2ant;

import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameManagerGUI extends JFrame {
 
    private static List<Hero> heroes = new ArrayList<>();
    private static List<GameEntity> enemies = new ArrayList<>(); 
    private static List<Skill> skills = new ArrayList<>();

    private javax.swing.JList<String> jList1;      
    private javax.swing.JList<String> jList2;      
    private javax.swing.JComboBox<Skill> jComboBox1; 
    private javax.swing.JTextArea jTextArea1;     
    
 
    private javax.swing.JTextField txtHeroName;
    private javax.swing.JTextField txtHeroHP;
    private javax.swing.JTextField txtHeroAP;
    private javax.swing.JTextField txtHeroLevel;
    private javax.swing.JButton btnAddHero; 
 
    public GameManagerGUI() {
       
        initComponents(); 
        
        // Data Awal
        if (heroes.isEmpty()) {
            heroes.add(new Hero("Ksatria Agung", 500, 50, 5));
            enemies.add(new Enemy("Goblin Kecil", 80, 15, 1));
            skills.add(new Skill("Tebasan Kilat", 1.8));
            skills.add(new Skill("Tameng Besi", 1.0));
        }
        
        // Panggil update list awal
        updateHeroList();
        updateEnemyList();
        updateSkillComboBox();
        
        this.setTitle("UTS Game Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
    private void updateHeroList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (Hero hero : heroes) {
            model.addElement(hero.getStatus()); 
        }
        // Pastikan jList1 adalah nama JList Hero Anda
        if (jList1 != null) {
             jList1.setModel(model);
        }
    }
    
    // Metode untuk memperbarui JList Enemy
    private void updateEnemyList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (GameEntity enemy : enemies) {
            model.addElement(enemy.getStatus()); 
        }
        // Pastikan jList2 adalah nama JList Enemy Anda
        if (jList2 != null) {
            jList2.setModel(model);
        }
    }

 
    private void updateSkillComboBox() {
        // Pastikan jComboBox1 adalah nama JComboBox Skill Anda
        if (jComboBox1 != null) {
            jComboBox1.removeAllItems();
            for (Skill skill : skills) {
                jComboBox1.addItem(skill); 
            }
        }
    }
    

    public void addHeroAction(String name, int hp, int ap, int level) {
        try {
            Hero newHero = new Hero(name, hp, ap, level);
            heroes.add(newHero);
            
            updateHeroList(); 
            JOptionPane.showMessageDialog(this, "Hero " + name + " berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "HP, AP, dan Level harus berupa angka!", "Error Input", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan Hero: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void addSkillAction(String name, double multiplier) {
        try {
            Skill newSkill = new Skill(name, multiplier);
            skills.add(newSkill);
            
            updateSkillComboBox(); 
            JOptionPane.showMessageDialog(this, "Skill " + name + " berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid. Pastikan Multiplier adalah angka desimal.", "Error Input", JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    public void startBattleSimulation(Hero selectedHero, GameEntity selectedEnemy) {
        
        if (selectedHero == null || selectedEnemy == null) {
            jTextArea1.setText("Pilih Hero dan Enemy untuk memulai pertarungan.");
            return;
        }

        selectedHero.setCurrentHp(selectedHero.getMaxHp());
        selectedEnemy.setCurrentHp(selectedEnemy.getMaxHp());
        
        jTextArea1.setText(">>> PERTARUNGAN DIMULAI <<<\n");
        jTextArea1.append(selectedHero.getStatus() + " vs " + selectedEnemy.getStatus() + "\n\n");
        
        int round = 1;
        while (selectedHero.getCurrentHp() > 0 && selectedEnemy.getCurrentHp() > 0) {
            jTextArea1.append("--- RONDE " + round + " ---\n");
            
           
            String heroAttackLog = selectedHero.attack(selectedEnemy);
            jTextArea1.append("Hero Turn: " + heroAttackLog + "\n");
            
            if (selectedEnemy.getCurrentHp() <= 0) {
                jTextArea1.append("\n" + selectedEnemy.getName() + " dikalahkan! " + selectedHero.getName() + " MENANG!\n");
                break;
            }
            
            
            String enemyAttackLog = selectedEnemy.attack(selectedHero);
            jTextArea1.append("Enemy Turn: " + enemyAttackLog + "\n");
            
            if (selectedHero.getCurrentHp() <= 0) {
                jTextArea1.append("\n" + selectedHero.getName() + " dikalahkan! " + selectedEnemy.getName() + " MENANG!\n");
                break;
            }

            jTextArea1.append("Status: " + selectedHero.getCurrentHp() + " HP vs " + selectedEnemy.getCurrentHp() + " HP\n\n");
            round++;
        }
        
        jTextArea1.append(">>> PERTARUNGAN SELESAI <<<\n");
    }
    
   
    private void initComponents() {
       
    }

    
} 