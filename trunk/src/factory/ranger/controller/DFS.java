package factory.ranger.controller;

import factory.ranger.model.ListVendor;
import factory.ranger.model.Map;
import factory.ranger.utility.Input;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class DFS {
    private Truck truck;
    private int level;
    private int iter;
    private ArrayList<Point> finalRoute;
    private ArrayList<Point> veryfinalRoute;
    private HashMap<Point, Point> parent;
    private boolean isDFSFinished;
    
    public DFS(){
        iter = 0;
        level = 0;
        finalRoute = new ArrayList<Point>();
        veryfinalRoute = new ArrayList<Point>();
        parent = new HashMap<Point, Point>();
        truck = new Truck(Map.getSingleton().getTruckPos(), ListVendor.getSingleton().getNameVendors());
        isDFSFinished = false;
    }
    
    public Truck getTruck(){
        return truck;
    }
    
    private void levelplus(){
        level++;
    }
    
    void DFS(Point curPoint){
        Point temp = new Point();
        
        //up
        temp.x = curPoint.x;
        temp.y = curPoint.y;
        if (truck.moveUp(temp,2)){
            parent.put(new Point(temp.x,temp.y), new Point(curPoint.x,curPoint.y));
            truck.addRoute(new Point(temp.x,temp.y));
            if (truck.isSolution(temp)){
                isDFSFinished = true;
            } else {
                DFS(temp);
            }
        }
        if(isDFSFinished) return;
        
        //right
        temp.x = curPoint.x;
        temp.y = curPoint.y;
        if (truck.moveRight(temp,2)){
            parent.put(new Point(temp.x,temp.y), new Point(curPoint.x,curPoint.y));
            truck.addRoute(new Point(temp.x,temp.y));
            if (truck.isSolution(temp)){
                isDFSFinished = true;
            } else {
                DFS(temp);
            }
        }
        if(isDFSFinished) return;
        
        //down
        temp.x = curPoint.x;
        temp.y = curPoint.y;
        if (truck.moveDown(temp,2)){
            parent.put(new Point(temp.x,temp.y), new Point(curPoint.x,curPoint.y));
            truck.addRoute(new Point(temp.x,temp.y));
            if (truck.isSolution(temp)){
                isDFSFinished = true;
            } else {
                DFS(temp);
            }
        }
        if(isDFSFinished) return;
        
        //left
        temp.x = curPoint.x;
        temp.y = curPoint.y;
        if (truck.moveLeft(temp,2)){
            parent.put(new Point(temp.x,temp.y), new Point(curPoint.x,curPoint.y));
            truck.addRoute(new Point(temp.x,temp.y));
            if (truck.isSolution(temp)){
                isDFSFinished = true;
            } else {
                DFS(temp);
            }
        }
    }
    
    public void getFinalRoute(Point curPoint){
        DFS(curPoint);
        
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
        
        System.out.println("");
        for(int i=finalRoute.size()-1; i>=0;i--){
            System.out.println(finalRoute.get(i).x+", "+finalRoute.get(i).y);
        }
    }
    
    public void getAllMachine(Point curPoint, ArrayList<Character> curDest){
        //iterasi sampai dest nya abis
        //add posisi truck ke araylist
        //bfs(araylist posisi trck)
        //remove vendor yang uda dikunjungi dari dest
        
        //ArrayList<Character> temp = truck.dest;
        if(!curDest.isEmpty()){
            truck.addRoute(Map.getSingleton().getTruckPos());
            getFinalRoute(curPoint);
            
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
            getAllMachine(curPoint,curDest);
        }else{
            return;
        }
        
    }
    
    public static void main(String[] args) {
        
        Input input = new Input("coba.txt");
        DFS dfs = new DFS();
        
//        dfs.truck.addRoute(Map.getSingleton().getTruckPos());
//        dfs.getFinalRoute(Map.getSingleton().getTruckPos());
        
        dfs.getAllMachine(Map.getSingleton().getTruckPos(),dfs.truck.dest);
        
        for(int i=0; i<=dfs.veryfinalRoute.size()-1;i++){
            System.out.println(i+" <--> "+dfs.veryfinalRoute.get(i).x+", "+dfs.veryfinalRoute.get(i).y);
        }
    }
}
