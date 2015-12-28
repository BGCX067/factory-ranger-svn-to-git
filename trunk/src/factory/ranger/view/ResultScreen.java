/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * RecapScreen.java
 *
 * Created on Feb 28, 2012, 11:34:36 PM
 */
package factory.ranger.view;

import factory.ranger.model.Factory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

/**
 *
 * @author axioo
 */
public class ResultScreen extends javax.swing.JPanel {

    private static ResultScreen recapscreen = new ResultScreen();
    
    public static ResultScreen getSingleton(){
        return recapscreen;
    }
    
    RentedPanel rpanel;
    WorkPanel wpanel;
    SoldPanel spanel;
    JScrollPane content;
    /** Creates new form RecapScreen */
    public ResultScreen() {
        initComponents();
    }

    public void displayResult(int mode) throws IOException{
        recapPanel.setVisible(false);
        rentedPanel.setVisible(false);
        workPanel.setVisible(false);
        soldPanel.setVisible(false);
        
        switch (mode){
            case 1 :
                recapPanel.setVisible(true);
                ibudget_txt.setText(SelectScreen.getSingleton().path);
                if(SelectScreen.getSingleton().mode == 1){
                    income_txt.setText("Breadth First Search (BFS)");
                } else if(SelectScreen.getSingleton().mode == 2){
                    income_txt.setText("Depth First Search (DFS)");
                } else if(SelectScreen.getSingleton().mode == 3){
                    income_txt.setText("A* Algorithm");
                }
                //FormPanel.state = 2;
                break;
            case 2 :
                rentedPanel.setVisible(true);
                showRented();
                //FormPanel.state = 3;
                break;
            case 3 :
                workPanel.setVisible(true);
                showWork();
                //FormPanel.state = 4;
                break;
            case 4 :
                soldPanel.setVisible(true);
                showSold();
                //FormPanel.state = 5;
                break;
            default :
                
                break;
        }
        this.setVisible(true);
        
    }
    
    public void showWork() throws IOException{
        workPanel.removeAll();
        wpanel = new  WorkPanel(); 
        content = new JScrollPane();
        content.setOpaque(false);
        content.getVerticalScrollBar().setBlockIncrement(100);
        content = wpanel.getContent();
        content.setBounds(207,7,565,355);
        content.setVisible(true);
        workPanel.add(content);
        workPanel.add(machine_lbl1);
        workPanel.add(machine_btn1);
        this.setVisible(true);
    }
    
    public void showSold() throws IOException{
        soldPanel.removeAll();
        spanel = new  SoldPanel(); 
        content = new JScrollPane();
        content.setOpaque(false);
        content.getVerticalScrollBar().setBlockIncrement(100);
        content = spanel.getContent();
        content.setBounds(207,7,565,355);
        content.setVisible(true);
        soldPanel.add(vendor_lbl1);
        soldPanel.add(vendor_btn1);
        soldPanel.add(content);
        this.setVisible(true);
    }
    
    public void showRented() throws IOException{
        rentedPanel.removeAll();
        rpanel = new  RentedPanel();
        content = new JScrollPane();
        content.setOpaque(false);
        content.getVerticalScrollBar().setBlockIncrement(100);
        content = rpanel.getContent();
        content.setBounds(207,7,565,355);
        content.setVisible(true);
        rentedPanel.add(factory_lbl1);
        rentedPanel.add(factory_btn1);
        rentedPanel.add(content);
        this.setVisible(true);
    }
    
    public void hideResult(){
        recapPanel.setVisible(true);
        rentedPanel.setVisible(true);
        workPanel.setVisible(true);
        soldPanel.setVisible(true);
        this.setVisible(false);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        recapPanel = new javax.swing.JLayeredPane();
        recap_lbl1 = new javax.swing.JLabel();
        algorithm_lbl = new javax.swing.JLabel();
        input_lbl = new javax.swing.JLabel();
        income_txt = new javax.swing.JTextField();
        ibudget_txt = new javax.swing.JTextField();
        recap_btn2 = new javax.swing.JLabel();
        rentedPanel = new javax.swing.JLayeredPane();
        factory_lbl1 = new javax.swing.JLabel();
        factory_btn1 = new javax.swing.JLabel();
        workPanel = new javax.swing.JLayeredPane();
        machine_lbl1 = new javax.swing.JLabel();
        machine_btn1 = new javax.swing.JLabel();
        soldPanel = new javax.swing.JLayeredPane();
        vendor_lbl1 = new javax.swing.JLabel();
        vendor_btn1 = new javax.swing.JLabel();
        sold_lbl = new javax.swing.JLabel();
        work_lbl = new javax.swing.JLabel();
        rented_lbl = new javax.swing.JLabel();
        recap_lbl = new javax.swing.JLabel();
        sold_btn = new javax.swing.JLabel();
        work_btn = new javax.swing.JLabel();
        rented_btn = new javax.swing.JLabel();
        recap_btn = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mainmenu_btn = new javax.swing.JLabel();
        dialogbox = new javax.swing.JLabel();
        header = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        recap_lbl1.setFont(new java.awt.Font("Trebuchet MS", 1, 18));
        recap_lbl1.setForeground(new java.awt.Color(229, 224, 181));
        recap_lbl1.setText(" RECAP");
        recap_lbl1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        recap_lbl1.setBounds(130, 30, 60, 35);
        recapPanel.add(recap_lbl1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        algorithm_lbl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        algorithm_lbl.setForeground(new java.awt.Color(61, 56, 39));
        algorithm_lbl.setText("final income :");
        algorithm_lbl.setBounds(250, 170, 170, 40);
        recapPanel.add(algorithm_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        input_lbl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        input_lbl.setForeground(new java.awt.Color(61, 56, 39));
        input_lbl.setText("initial budget :");
        input_lbl.setBounds(250, 60, 150, 30);
        recapPanel.add(input_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        income_txt.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        income_txt.setForeground(new java.awt.Color(61, 56, 39));
        income_txt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        income_txt.setBounds(290, 220, 440, 30);
        recapPanel.add(income_txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ibudget_txt.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        ibudget_txt.setForeground(new java.awt.Color(61, 56, 39));
        ibudget_txt.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        ibudget_txt.setBounds(290, 100, 440, 30);
        recapPanel.add(ibudget_txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        recap_btn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu selected-x20y170.png"))); // NOI18N
        recap_btn2.setText("jLabel18");
        recap_btn2.setBounds(20, 20, 180, 55);
        recapPanel.add(recap_btn2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        recapPanel.setBounds(0, 150, 800, 370);
        jLayeredPane1.add(recapPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        factory_lbl1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        factory_lbl1.setForeground(new java.awt.Color(229, 224, 181));
        factory_lbl1.setText(" RENTED MACHINES");
        factory_lbl1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        factory_lbl1.setBounds(30, 95, 160, 35);
        rentedPanel.add(factory_lbl1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        factory_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu selected-x20y170.png"))); // NOI18N
        factory_btn1.setText("jLabel18");
        factory_btn1.setBounds(20, 85, 180, 55);
        rentedPanel.add(factory_btn1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        rentedPanel.setBounds(1, 151, 800, 370);
        jLayeredPane1.add(rentedPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        machine_lbl1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        machine_lbl1.setForeground(new java.awt.Color(229, 224, 181));
        machine_lbl1.setText("            WORK LOGS");
        machine_lbl1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        machine_lbl1.setBounds(30, 160, 170, 35);
        workPanel.add(machine_lbl1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        machine_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu selected-x20y170.png"))); // NOI18N
        machine_btn1.setText("jLabel18");
        machine_btn1.setBounds(20, 150, 180, 55);
        workPanel.add(machine_btn1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        workPanel.setBounds(0, 150, 800, 370);
        jLayeredPane1.add(workPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        vendor_lbl1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        vendor_lbl1.setForeground(new java.awt.Color(229, 224, 181));
        vendor_lbl1.setText("      SOLD PRODUCT");
        vendor_lbl1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        vendor_lbl1.setBounds(30, 225, 170, 35);
        soldPanel.add(vendor_lbl1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        vendor_btn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu selected-x20y170.png"))); // NOI18N
        vendor_btn1.setText("jLabel18");
        vendor_btn1.setBounds(20, 215, 180, 55);
        soldPanel.add(vendor_btn1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        soldPanel.setBounds(0, 150, 800, 370);
        jLayeredPane1.add(soldPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        sold_lbl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        sold_lbl.setForeground(new java.awt.Color(229, 224, 181));
        sold_lbl.setText("      SOLD PRODUCT");
        sold_lbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        sold_lbl.setBounds(30, 375, 170, 35);
        jLayeredPane1.add(sold_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        work_lbl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        work_lbl.setForeground(new java.awt.Color(229, 224, 181));
        work_lbl.setText("            WORK LOGS");
        work_lbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        work_lbl.setBounds(30, 310, 170, 35);
        jLayeredPane1.add(work_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        rented_lbl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        rented_lbl.setForeground(new java.awt.Color(229, 224, 181));
        rented_lbl.setText(" RENTED MACHINES");
        rented_lbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rented_lbl.setBounds(30, 245, 160, 35);
        jLayeredPane1.add(rented_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        recap_lbl.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        recap_lbl.setForeground(new java.awt.Color(229, 224, 181));
        recap_lbl.setText(" RECAP");
        recap_lbl.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        recap_lbl.setBounds(130, 180, 60, 35);
        jLayeredPane1.add(recap_lbl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        sold_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu-x20y170.png"))); // NOI18N
        sold_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sold_btnMouseClicked(evt);
            }
        });
        sold_btn.setBounds(20, 365, 180, 55);
        jLayeredPane1.add(sold_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        work_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu-x20y170.png"))); // NOI18N
        work_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                work_btnMouseClicked(evt);
            }
        });
        work_btn.setBounds(20, 300, 180, 55);
        jLayeredPane1.add(work_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        rented_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu-x20y170.png"))); // NOI18N
        rented_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rented_btnMouseClicked(evt);
            }
        });
        rented_btn.setBounds(20, 235, 180, 55);
        jLayeredPane1.add(rented_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        recap_btn.setForeground(new java.awt.Color(255, 255, 255));
        recap_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-left menu-x20y170.png"))); // NOI18N
        recap_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                recap_btnMouseClicked(evt);
            }
        });
        recap_btn.setBounds(20, 170, 180, 55);
        jLayeredPane1.add(recap_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(229, 224, 181));
        jLabel2.setText("main menu");
        jLabel2.setBounds(640, 540, 110, 30);
        jLayeredPane1.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        mainmenu_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/button-bg-x600y530.png"))); // NOI18N
        mainmenu_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainmenu_btnMouseClicked(evt);
            }
        });
        mainmenu_btn.setBounds(603, 530, 175, 50);
        jLayeredPane1.add(mainmenu_btn, javax.swing.JLayeredPane.DEFAULT_LAYER);

        dialogbox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/menu-right bg-x200y150.png"))); // NOI18N
        dialogbox.setBounds(200, 150, 580, 370);
        jLayeredPane1.add(dialogbox, javax.swing.JLayeredPane.DEFAULT_LAYER);

        header.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/intro-title-x0y70.png"))); // NOI18N
        header.setBounds(0, 10, 800, 110);
        jLayeredPane1.add(header, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/factory/ranger/image/main-bg.png"))); // NOI18N
        jLabel1.setBounds(0, 0, 800, 600);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void recap_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_recap_btnMouseClicked
        try {
            // TODO add your handling code here:
            displayResult(1);
        } catch (IOException ex) {
            Logger.getLogger(ResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_recap_btnMouseClicked

    private void work_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_work_btnMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            displayResult(3);
        } catch (IOException ex) {
            Logger.getLogger(ResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_work_btnMouseClicked

    private void rented_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rented_btnMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            displayResult(2);
        } catch (IOException ex) {
            Logger.getLogger(ResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_rented_btnMouseClicked

    private void sold_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sold_btnMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            displayResult(4);
        } catch (IOException ex) {
            Logger.getLogger(ResultScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sold_btnMouseClicked

    private void mainmenu_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainmenu_btnMouseClicked
        // TODO add your handling code here:
        hideResult();
        MainScreen.getSingleton().setVisible(true);
    }//GEN-LAST:event_mainmenu_btnMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel algorithm_lbl;
    private javax.swing.JLabel dialogbox;
    private javax.swing.JLabel factory_btn1;
    private javax.swing.JLabel factory_lbl1;
    private javax.swing.JLabel header;
    private javax.swing.JTextField ibudget_txt;
    private javax.swing.JTextField income_txt;
    private javax.swing.JLabel input_lbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel machine_btn1;
    private javax.swing.JLabel machine_lbl1;
    private javax.swing.JLabel mainmenu_btn;
    private javax.swing.JLayeredPane recapPanel;
    private javax.swing.JLabel recap_btn;
    private javax.swing.JLabel recap_btn2;
    private javax.swing.JLabel recap_lbl;
    private javax.swing.JLabel recap_lbl1;
    private javax.swing.JLayeredPane rentedPanel;
    private javax.swing.JLabel rented_btn;
    private javax.swing.JLabel rented_lbl;
    private javax.swing.JLayeredPane soldPanel;
    private javax.swing.JLabel sold_btn;
    private javax.swing.JLabel sold_lbl;
    private javax.swing.JLabel vendor_btn1;
    private javax.swing.JLabel vendor_lbl1;
    private javax.swing.JLayeredPane workPanel;
    private javax.swing.JLabel work_btn;
    private javax.swing.JLabel work_lbl;
    // End of variables declaration//GEN-END:variables
}
