/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.model;

import java.awt.Point;

/**
 *
 * @author axioo
 */
public class Vendor {
    //attribute
    private static Vendor vendor = new Vendor();
    
    public static Vendor getSingleton() {
        return vendor;
    }
    private Machine[] lom;
    private Point position;
    private char name;
    
    //constructor
    public Vendor() {}
    
    public Vendor(char name, Point position, Machine[] lom) {
        this.name = name;
        this.position = position;
        this.lom = lom;
    }
    
    //getter and setter
    public void setVendor(Vendor vendor){
        this.name = vendor.name;
        this.position = vendor.position;
        this.lom = vendor.lom;
    }
    
    public char getName(){
        return name;
    }
    
    public Machine[] getListOfMachines() {
        return lom;
    }

    public void setListOfMachines(Machine[] lom) {
        this.lom = lom;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
    
}
