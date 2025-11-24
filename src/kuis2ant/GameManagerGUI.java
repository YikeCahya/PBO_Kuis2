/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package kuis2ant;

import java.util.ArrayList; 
import java.util.List; 
import javax.swing.DefaultListModel; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 



/**
 *
 * @author ADVAn
 */
public class GameManagerGUI extends javax.swing.JFrame {
    
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
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(GameManagerGUI.class.getName());

    public GameManagerGUI() {
        initComponents();
        
        if (heroes.isEmpty()) {
            heroes.add(new Hero("Ksatria Agung", 500, 50, 5));
            enemies.add(new Enemy("Goblin Kecil", 80, 15, 1));
            skills.add(new Skill("Tebasan Kilat", 1.8));
            skills.add(new Skill("Tameng Besi", 1.0));
        }
        
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
        if (jList1 != null) {
             jList1.setModel(model);
        }
    }
    
    private void updateEnemyList() {
        DefaultListModel<String> model = new DefaultListModel<>();
        for (GameEntity enemy : enemies) {
            model.addElement(enemy.getStatus()); 
        }
        if (jList2 != null) {
            jList2.setModel(model);
        }
    }
    
    private void updateSkillComboBox() {
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
            if (jTextArea1 != null) {
                jTextArea1.setText("Pilih Hero dan Enemy untuk memulai pertarungan.");
            }
            return;
        }

        selectedHero.setCurrentHp(selectedHero.getMaxHp());
        selectedEnemy.setCurrentHp(selectedEnemy.getMaxHp());
        
        if (jTextArea1 != null) {
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
    }
    
    
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new GameManagerGUI().setVisible(true));
        
    }
       
        //</editor-fold>

        /* Create and display the form */
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

