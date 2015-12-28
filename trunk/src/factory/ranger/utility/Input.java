/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.utility;

import factory.ranger.model.Factory;
import factory.ranger.model.ListMachine;
import factory.ranger.model.ListVendor;
import factory.ranger.model.Machine;
import factory.ranger.model.Map;
import factory.ranger.model.Product;
import factory.ranger.model.Vendor;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author axioo
 */
public class Input {
    
    //attribute
    private String[] strInput;
    private int nMesin, nVendor, width, height;
    
    public Input() {}
    
    public Input(String filename) {
        String strLine;
        ArrayList<String> tempInput = new ArrayList<String>();
        //String[] tempInput = null;
        try{
          FileInputStream fstream = new FileInputStream(filename);
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          
          //Read File Line By Line
          int baris = 0;
          while ((strLine = br.readLine()) != null)   {
              tempInput.add(strLine);//[baris] = strLine;
              baris++;
          }
          //Close the input stream
          in.close();
          strInput = new String[baris];
          strInput = tempInput.toArray(strInput);
          nMesin = Integer.parseInt(strInput[0].split("\\s")[2]);
          nVendor = Integer.parseInt(strInput[1+nMesin]);
          height = Integer.parseInt(strInput[2+(2*nMesin)+(2*nVendor)].split("\\s")[0]);
          width = Integer.parseInt(strInput[2+(2*nMesin)+(2*nVendor)].split("\\s")[1]);
          
        }catch (Exception e){//Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
        
        Map.getSingleton().setMap(getMap());
        Factory.getSingleton().setFactory(getFactory());
        ListVendor.getSingleton().setListVendor(getListVendor());
        ListMachine.getSingleton().setListMachine(getListMachine());
    }
    
    public String[] read(String filename) {
        String strLine;
        String[] tempInput = null;
        try{
          FileInputStream fstream = new FileInputStream(filename);
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          
          //Read File Line By Line
          int baris = 0;
          while ((strLine = br.readLine()) != null)   {
              tempInput[baris] = strLine;
              baris++;
          }
          //Close the input stream
          in.close();
          //strInput = tempInput;
        }catch (Exception e){//Catch exception if any
          System.err.println("Error: " + e.getMessage());
        }
        return tempInput;
    }

    private Map getMap(){
        String temp;
        Point factory = new Point();
        
        Map map = new Map(width, height, new Point(0,0), 0);
        
        for(int j=0; j<height; j++) {
            temp = strInput[(2*nMesin)+(2*nVendor)+3+j];
            for(int i=0; i<width; i++) {
                if(temp.charAt(i) == 'P') {
                    factory = new Point(i,j);
                    map.setTruckPos(factory);
                    map.setTile(i, j, (int)'P');
                } else if(temp.charAt(i) == '#') {
                    map.setTile(i, j, -1);
                } else if(temp.charAt(i) == '.') {
                    map.setTile(i, j, 0);
                } else {
                    map.setTile(i, j, (int)temp.charAt(i));
                }
            }
        }
        map.setNVendor(nVendor);
        
        return map;
    }
    
    private Factory getFactory(){
        int $, H, M, N, Y;
        String[] input;
        
        input = strInput[0].split("\\s");
        $ = Integer.parseInt(input[0]);
        H = Integer.parseInt(input[1]);
        M = Integer.parseInt(input[2]);
        N = Integer.parseInt(input[3]);
        Y = Integer.parseInt(input[4]);
        
        return new Factory($, H, M, N, Y); 
    }
    
    private Machine[] getListMachine(){
        int T, W, S, P, I, C, R, Dependensi, number;
        String[] input;
        Product product;
        Machine[] machine = new Machine[nMesin+1];
        int rowProduct = 1 + (nVendor * 2) + nMesin;
        
        for(int i=1; i<=nMesin; i++) {
            number = i;
            input = strInput[i].split("\\s");
            T = Integer.parseInt(input[0]);
            W = Integer.parseInt(input[1]);
            S = Integer.parseInt(input[2]);
            P = Integer.parseInt(input[3]);
            I = Integer.parseInt(input[4]);
            C = Integer.parseInt(input[5]);
            R = Integer.parseInt(input[6]);
            Dependensi = Integer.parseInt(input[7]);
            product = new Product(i, Integer.parseInt(strInput[rowProduct + i]));
            machine[i] = new Machine(number, T, W, S, P, I, C, R, Dependensi, product);
        }
        return machine;
    }
    
    private Vendor[] getListVendor(){
        char name;
        Point position;
        Machine[] machine, tempMachine;
        String[] temp;
        Vendor[] vendor;
        
        
        vendor = new Vendor[nVendor];
        tempMachine = getListMachine();
        position = new Point();
        
        for(int i=0; i<nVendor; i++){
             name = strInput[2+nMesin+(i*2)].charAt(0);
             temp = strInput[3+nMesin+(i*2)].split("\\s");
             machine = new Machine[Integer.parseInt(temp[0])+1];
             for(int j=1; j<=Integer.parseInt(temp[0]); j++) {
                 machine[j] = tempMachine[Integer.parseInt(temp[j])];
             }
             for(int j=0; j<height; j++){
                 for(int k=0; k<width; k++){
                     if(strInput[3+(nMesin*2)+(nVendor*2)+j].charAt(k)==name){
                         position = new Point(k,j);
                     }
                 }
             }
             vendor[i] = new Vendor(name, position, machine);
        }
        return vendor;
    }
    
    public static String getPath(String path){
        String temp = "";
        temp = path.replace("\\", "\\\\");
        
        return temp;
    }
    
    public static void main (String args[]) {
        
        Input input = new Input("C:\\Users\\axioo\\Documents\\NetBeansProjects\\factory-ranger\\coba.txt");
        
        System.out.println("--- MAP ---");
        System.out.println("width = "+Map.getSingleton().getWidth()+", height = "+Map.getSingleton().getHeight());
        Map.getSingleton().printMap();
        System.out.println("--- ---");
        System.out.println("");
        
        System.out.println("--- FACTORY ---");
        System.out.println("- $ = "+Factory.getSingleton().get$());
        System.out.println("- H = "+Factory.getSingleton().getH());
        System.out.println("- M = "+Factory.getSingleton().getM());
        System.out.println("- N = "+Factory.getSingleton().getN());
        System.out.println("- Y = "+Factory.getSingleton().getY());
        System.out.println("--- ---");
        System.out.println("");
        
        System.out.println("--- VENDOR ---");
        System.out.println("nVendor = "+ListVendor.getSingleton().getListVendor().size());
        for(int i=0; i<ListVendor.getSingleton().getListVendor().size();i++){
            System.out.println("Vendor "+ListVendor.getSingleton().getListVendor().get(i).getName());
            Machine[] temp = ListVendor.getSingleton().getListVendor().get(i).getListOfMachines();
            System.out.print("- ");
            for(int j=1;j<temp.length;j++){
                if(j<temp.length-1)
                    System.out.print(temp[j].getNumber()+", ");
                else
                    System.out.println(temp[j].getNumber());
            }
        }
        System.out.println("--- ---");
        
        System.out.println("--- MACHINE ---");
        System.out.println("nMesin = "+ListMachine.getSingleton().getListMachine().size());
        for(int i=1; i<ListMachine.getSingleton().getListMachine().size();i++){
            System.out.println("Machine "+ListMachine.getSingleton().getListMachine().get(i).getNumber());
            System.out.println("- T = "+ListMachine.getSingleton().getListMachine().get(i).getT());
            System.out.println("- W = "+ListMachine.getSingleton().getListMachine().get(i).getW());
            System.out.println("- S = "+ListMachine.getSingleton().getListMachine().get(i).getS());
            System.out.println("- P = "+ListMachine.getSingleton().getListMachine().get(i).getP());
            System.out.println("- I = "+ListMachine.getSingleton().getListMachine().get(i).getI());
            System.out.println("- C = "+ListMachine.getSingleton().getListMachine().get(i).getC());
            System.out.println("- R = "+ListMachine.getSingleton().getListMachine().get(i).getR());
            System.out.println("- Dependensi = "+ListMachine.getSingleton().getListMachine().get(i).getDependensi());
        }
        System.out.println("--- ---");
        System.out.println("");
        
        System.out.println("--- PRODUCT ---");
        ArrayList<Machine> temp = ListMachine.getSingleton().getListMachine();
        for(int i=1; i<temp.size();i++){
            System.out.println("Product "+i);
            System.out.println("- name : "+temp.get(i).getProduct().getName());
            System.out.println("- harga : "+temp.get(i).getProduct().getPrice());
            System.out.println("");
        }
        System.out.println("--- ---");
    }
}
