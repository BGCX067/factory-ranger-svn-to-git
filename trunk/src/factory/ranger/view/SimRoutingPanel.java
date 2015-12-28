/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.view;

import factory.ranger.model.Map;
import factory.ranger.utility.Input;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author axioo
 */
public class SimRoutingPanel extends JPanel{
    
    public JScrollPane getContent() throws IOException {
        System.out.println("a");
        Dimension d = new Dimension(570,360);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBounds(new Rectangle(d));
        panel.setBackground(new Color(229, 224, 181));
        //panel.setOpaque(false);
        GridBagConstraints gbc= new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START; //bottom of space
        System.out.println("aa");
        for(int j=0; j<Map.getSingleton().getHeight();j++){
            for (int i=0;i<Map.getSingleton().getWidth();i++){
                panel.add(getPanel(i, j), gbc);
                gbc.gridx++;
            }
            gbc.gridy++;
            gbc.gridx = 0;
        }
        return new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    private JPanel getPanel(int x, int y) throws IOException {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel tile = new JLabel();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START; //bottom of space
        //gbc.insets.set(15, 10, 0, 0);
        tile.setBounds(new Rectangle(100, 100));
        if(x==Map.getSingleton().getTruckPos().x && y == Map.getSingleton().getTruckPos().y 
                && Map.getSingleton().getTile(x, y)!= 80){
            tile.setIcon(new ImageIcon(getClass().getResource("image/blok-truk.png")));
        } else {
            if(Map.getSingleton().getTile(x, y)==0){
                tile.setIcon(new ImageIcon(getClass().getResource("image/blok-jalan.png")));
            } else if (Map.getSingleton().getTile(x, y)== -1){
                tile.setIcon(new ImageIcon(getClass().getResource("image/blok-lumpur.png")));
            } else if (Map.getSingleton().getTile(x, y)== 80){
                tile.setIcon(new ImageIcon(getClass().getResource("image/blok-pa achmad.png")));
            } else {
                tile.setIcon(new ImageIcon(getClass().getResource("image/blok-pabrik.png")));
            }
        }
       panel.add(tile, gbc); 
        
        return panel;
    }
    
    
    
    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();
        Input input = new Input("coba.txt");
        ProductPanel ppanel = new  ProductPanel(); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(ppanel.getContent());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
        
    }
    
}
