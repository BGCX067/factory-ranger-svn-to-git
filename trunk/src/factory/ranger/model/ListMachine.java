/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author axioo
 */
public class ListMachine {
    
    private static ListMachine listmachine = new ListMachine();
    
    public static ListMachine getSingleton(){
        return listmachine;
    }
    
    private ArrayList<Machine> lom;
    private HashMap<Integer, Integer> hashmap;
    
    public ListMachine(){
        lom = new ArrayList<Machine>();
        hashmap = new HashMap<Integer, Integer>();
    }
    
    public ListMachine(Machine[] machine){
        lom = new ArrayList<Machine>();
        hashmap = new HashMap<Integer, Integer>();
        
        lom.addAll(Arrays.asList(machine));
        for(int i=1; i<machine.length; i++){
            hashmap.put(machine[i].getNumber(),machine[i].getDependensi());
        }
    }
    
    public ArrayList<Machine> getListMachine(){
        return lom;
    }
    
    public int getDependensiMachine(int number){
        return hashmap.get(number);
    }
    
    public void setListMachine(Machine[] machine){
        lom.addAll(Arrays.asList(machine));
        
        for(int i=1; i<lom.size(); i++){
            hashmap.put(lom.get(i).getNumber(),lom.get(i).getDependensi());
        }
    }
}
