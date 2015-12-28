/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author axioo
 */
public class ListVendor {
    
    private static ListVendor listvendor = new ListVendor();
    
    public static ListVendor getSingleton(){
        return listvendor;
    }
    
    private ArrayList<Vendor> lov;
    private HashMap<Character, Point> hashmap;
    
    public ListVendor(){
        lov = new ArrayList<Vendor>();
        hashmap = new HashMap<Character, Point>();
    }
    
    public ListVendor(Vendor[] vendor){
        lov = new ArrayList<Vendor>();
        hashmap = new HashMap<Character, Point>();
        lov.addAll(Arrays.asList(vendor));
        
        for(int i=0; i<vendor.length; i++){
            hashmap.put(vendor[i].getName(),vendor[i].getPosition());
        }        
    }
    
    public void setListVendor(Vendor[] vendor){
        lov.addAll(Arrays.asList(vendor));
        for(int i=0; i<vendor.length; i++){
            hashmap.put(vendor[i].getName(),vendor[i].getPosition());
        }
    }
    
    public ArrayList<Vendor> getListVendor(){
        return lov;
    }
    
    public ArrayList<Character> getNameVendors(){
        ArrayList<Character> names = new ArrayList<Character>();
        for(int i=0; i<lov.size(); i++){
            names.add(lov.get(i).getName());
        }
        return names;
    }
    
    public Point getLocation(char name){
        return hashmap.get(name);
    }
    
    public boolean isExist(Point P) {
        boolean Result = false;
        
        for (int N=0; N<lov.size(); N++) {
            if ((P.x == lov.get(N).getPosition().x) && (P.y == lov.get(N).getPosition().y)) {
                Result = true;
            }
        }
        
        return Result;
    }
    
    public void EraseVendor(Point P) {
        for (int N=0; N<lov.size(); N++) {
            if ((P.x == lov.get(N).getPosition().x) && (P.y == lov.get(N).getPosition().y)) {
                lov.remove(N);
            }
        }
    }
}
