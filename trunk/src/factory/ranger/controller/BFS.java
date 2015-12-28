/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.controller;

import factory.ranger.model.ListVendor;
import factory.ranger.model.Map;
import factory.ranger.utility.Input;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author axioo
 */
public class BFS {
    
    private Truck truck;
    private int level;
    private int iter;
    private ArrayList<Point> finalRoute;
    private ArrayList<Point> veryfinalRoute;
    private HashMap<Point, Point> parent;
    
    public BFS(){
        iter = 0;
        level = 0;
        finalRoute = new ArrayList<Point>();
        veryfinalRoute = new ArrayList<Point>();
        parent = new HashMap<Point, Point>();
        truck = new Truck(Map.getSingleton().getTruckPos(), ListVendor.getSingleton().getNameVendors());
    }
    
    public Truck getTruck(){
        return truck;
    }
    
    private void levelplus(){
        level++;
    }
    
    void BFS(ArrayList<Point> curLevel){
        
        ArrayList<Point> nextLevel = new ArrayList<Point>();
	for(int i=0; i<curLevel.size();i++){
            Point temp = new Point();
            
            //up
            temp.x = curLevel.get(i).x;
            temp.y = curLevel.get(i).y;
            if(truck.moveUp(temp,1)){
                parent.put(new Point(temp.x,temp.y), new Point(curLevel.get(i).x,curLevel.get(i).y));
                truck.addRoute(new Point(temp.x,temp.y));	
                if(truck.isSolution(temp)) {
                    return;
                }
                nextLevel.add(temp);
            }
            
            //right
            temp.x = curLevel.get(i).x;
            temp.y = curLevel.get(i).y;
            if(truck.moveRight(temp,1)){
                parent.put(new Point(temp.x,temp.y), new Point(curLevel.get(i).x,curLevel.get(i).y));
                truck.addRoute(new Point(temp.x,temp.y));	
                if(truck.isSolution(temp))
                    return;
                nextLevel.add(temp);
            }
            
            //down
            temp.x = curLevel.get(i).x;
            temp.y = curLevel.get(i).y;
            if(truck.moveDown(temp,1)){
                parent.put(new Point(temp.x,temp.y), new Point(curLevel.get(i).x,curLevel.get(i).y));
                truck.addRoute(new Point(temp.x,temp.y));	
                if(truck.isSolution(temp))
                    return;
                nextLevel.add(temp);
            }
            
            //left
            temp.x = curLevel.get(i).x;
            temp.y = curLevel.get(i).y;
            if(truck.moveLeft(temp,1)){
                parent.put(new Point(temp.x,temp.y), new Point(curLevel.get(i).x,curLevel.get(i).y));
                truck.addRoute(new Point(temp.x,temp.y));	
                if(truck.isSolution(temp))
                    return;
                nextLevel.add(temp);
            }
        }
        if(nextLevel.size()>0){
            BFS(nextLevel);
        }
    }
    
    public void getFinalRoute(ArrayList<Point> curLevel){
        BFS(curLevel);
        
//        System.out.println(truck.getRoute().size());
//        for(int i=0; i<truck.getRoute().size();i++){
//            System.out.println(truck.getRoute().get(i).x+", "+truck.getRoute().get(i).y);
//            System.out.println("- key = "+truck.getRoute().get(i)+", "+"value = "+parent.get(truck.getRoute().get(i)));
//        }
        
        int nroute = truck.getRoute().size();
        finalRoute.add(truck.getRoute().get(nroute-1));
        Point key = truck.getRoute().get(nroute-1);
        for(int i=nroute-1; i>=0; i--){
            if(parent.containsKey(key)){
                finalRoute.add(parent.get(key));
                key = parent.get(key);
            }
        }
        
//        System.out.println("");
//        for(int i=finalRoute.size()-1; i>=0;i--){
//            System.out.println(i+" > "+finalRoute.get(i).x+", "+finalRoute.get(i).y);
//        }
        
        
    }
    
    public void getAllMachine(ArrayList<Point> curLevel, ArrayList<Character> curDest){
        //iterasi sampai dest nya abis
        //add posisi truck ke araylist
        //bfs(araylist posisi trck)
        //remove vendor yang uda dikunjungi dari dest
        
        //ArrayList<Character> temp = truck.dest;
        if(!curDest.isEmpty()){
            truck.addRoute(Map.getSingleton().getTruckPos());
            getFinalRoute(curLevel);
            
//            System.out.println(Map.getSingleton().getTile(finalRoute.get(0).x, finalRoute.get(0).y));
            char a = (char)Map.getSingleton().getTile(finalRoute.get(0).x, finalRoute.get(0).y);
//            System.out.println("char "+a);
//            System.out.println(curDest.indexOf(a));
            curDest.remove(curDest.indexOf(a));
        for(int i=finalRoute.size()-1; i>=0;i--){
            veryfinalRoute.add(finalRoute.get(i));
        }
        for(int i=0; i<=finalRoute.size()-1;i++){
            veryfinalRoute.add(finalRoute.get(i));
        }
        
        
        
        finalRoute.clear();
            getAllMachine(curLevel,curDest);
        }else{
            return;
        }
        
    }
    
    public static void main(String[] args) {
        BFS bfs = new BFS();
        ArrayList<Point> a = new ArrayList<Point>();
        a.add(Map.getSingleton().getTruckPos());
        bfs.getAllMachine(a, ListVendor.getSingleton().getNameVendors());
        
        for(int i=0; i<=bfs.veryfinalRoute.size()-1;i++){
            System.out.println(i+" <--> "+bfs.veryfinalRoute.get(i).x+", "+bfs.veryfinalRoute.get(i).y);
        }
    }
}
