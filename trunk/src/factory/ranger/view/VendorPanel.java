/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.view;

import factory.ranger.model.ListVendor;
import factory.ranger.model.Machine;
import factory.ranger.model.Vendor;
import factory.ranger.utility.Input;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 *
 * @author axioo
 */
public class VendorPanel extends JPanel{
    
    public VendorPanel(){
        
    }
    
    public String getString(Vendor vendor, int i){
        String temp = "";
        temp += "[vendor "+vendor.getName() +"]\n";
        temp += "     position\t\t: ("+vendor.getPosition().x+", "+vendor.getPosition().y+")\n";
        temp += "     available machines\t: ";
        Machine[] m = ListVendor.getSingleton().getListVendor().get(i).getListOfMachines();
        for(int j=1; j<m.length; j++){
            if(j<m.length-1)
                temp += vendor.getListOfMachines()[j].getNumber()+", ";
            else
                temp += vendor.getListOfMachines()[j].getNumber();
        }
        temp += "\n";
        return temp;
    }
    
    public JScrollPane getContent() throws IOException {
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
        for (int i=0;i<ListVendor.getSingleton().getListVendor().size();i++){
            panel.add(getPanel(i, ListVendor.getSingleton().getListVendor().get(i)), gbc);
            gbc.gridy++;
        }
        
        return new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
                , ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
    
    private JPanel getPanel(int mesinke, Vendor vendor) throws IOException {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        int x = 0;
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START; //bottom of space
        gbc.insets.set(15, 10, 0, 0);
        JTextArea no_machine = new JTextArea();
        no_machine.setEditable(false);
        no_machine.setOpaque(false);
        no_machine.setFont(new Font("Trebuchet MS", 1, 18));
        no_machine.setText(getString(vendor, mesinke));
        no_machine.setLineWrap(true);
        no_machine.setBounds(new Rectangle(570, 350));
        panel.add(no_machine, gbc); 
        
        return panel;
    }
    
    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame();
        Input input = new Input("coba.txt");
        VendorPanel vpanel = new  VendorPanel(); 
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(vpanel.getContent());
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
        
    }
    
}
