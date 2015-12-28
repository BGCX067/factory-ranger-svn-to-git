/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.controller;

import factory.ranger.model.ListVendor;
import java.awt.Point;
import factory.ranger.model.Map;
import factory.ranger.utility.Input;
import java.util.ArrayList;

/**
 *
 * @author axioo
 */
public class Truck {
    //attribute
    private Map map;
    public Point pos;
    public ArrayList<Character> dest;
    private int mode;
    private ArrayList<Point> route;
    private int iter;

    //constructor
    public Truck(Point pos, ArrayList<Character> dest){
        iter = 1;    
        this.pos = pos;
        this.map = Map.getSingleton();
        this.dest = dest;
        this.route = new ArrayList<Point>();
    }

    //getter and setter
    public void addRoute(Point point){
        route.add(point);
    }
    
    public int getIter(){
        return iter;
    }
    
    public ArrayList<Point> getRoute(){
        return route;
    }
    
    //other methods
    public boolean isSolution(Point point){
        for(int i=0; i<map.getHeight(); i++){
            for(int j=0; j<map.getWidth(); j++){
                for(int k=0; k<dest.size();k++){
                    if(point.x == ListVendor.getSingleton().getLocation(dest.get(k)).x &&
                            point.y == ListVendor.getSingleton().getLocation(dest.get(k)).y){
                        return true;
                    }
                }
                
            }
        }
        return false;
    }
    
    public boolean isEqState(Point point, Point _point){
        if(point.x == _point.x && point.y == _point.y)
            return true;
        else
            return false;        
    }

    public boolean isStateExist(Point point, int mode){
            for(int i=0;i<route.size();i++){//iter=mapList.iterator();iter.hasNext();)
                if(isEqState(point,route.get(i))) {
                        return true;
                    }
            }
            return false;
    }

    public boolean moveUp(Point point, int mode){
            Point temp = new Point(point.x, point.y);
            iter++;
            if(temp.y>0 && 
                    (map.getTile(temp.x, temp.y-1) == 0 || (dest.contains((char)map.getTile(temp.x, temp.y-1))))) {
                
                temp.y--;
                if(!isStateExist(temp,mode)){
                        point.x = temp.x;
                        point.y = temp.y;
                        return true;
                } else {
                        return false;
                }
            } else {
                    return false;
            }
    }

    public boolean moveRight(Point point, int mode){
        Point temp = new Point(point.x, point.y);
        iter++;
        if(temp.x<map.getWidth()-1){
            if (map.getTile(temp.x+1, temp.y) == 0 || dest.contains((char)map.getTile(temp.x+1, temp.y))) {
                temp.x++;
                if(!isStateExist(temp,mode)){
                    point.x = temp.x;
                    point.y = temp.y;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean moveDown(Point point, int mode){
        Point temp = new Point(point.x, point.y);
        iter++;
        if(temp.y<map.getHeight()-1 && 
                (map.getTile(temp.x, temp.y+1) == 0 || dest.contains((char)map.getTile(temp.x, temp.y+1)))) {
            temp.y++;
            if(!isStateExist(temp,mode)){
                point.x = temp.x;
                point.y = temp.y;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean moveLeft(Point point, int mode){
        Point temp = new Point(point.x, point.y);
        iter++;
        //System.out.println("left "+iter+" ; point : "+point.x+", "+point.y);
        if(temp.x>0 && 
                (map.getTile(temp.x-1, temp.y) == 0 || dest.contains((char)map.getTile(temp.x-1, temp.y)))) {
            temp.x--;
            if(!isStateExist(temp,mode)){
                    point.x = temp.x;
                    point.y = temp.y;
                    return true;
            } else {
                    return false;
            }
        } else {
                return false;
        }
    }
    
    public static void main(String args[]){
        Input input = new Input("coba.txt");
        //BFS bfs = new BFS(ListVendor.getSingleton().getNameVendors());
        System.out.println(Map.getSingleton().getTruckPos().x+", "+Map.getSingleton().getTruckPos().y);
        bfs.getTruck().moveRight(Map.getSingleton().getTruckPos(), 1);
        System.out.println(Map.getSingleton().getTruckPos().x+", "+Map.getSingleton().getTruckPos().y);
        if(bfs.getTruck().isSolution(Map.getSingleton().getTruckPos())){
            System.out.println("yeAH");
        }
        if (bfs.getTruck().moveDown(Map.getSingleton().getTruckPos(), 1)){
            System.out.println("bisa ke atas");
        }
        if (bfs.getTruck().moveDown(Map.getSingleton().getTruckPos(), 1)){
            System.out.println("bisa ke kanan");
            Map.getSingleton().printMap();
        }
        if(bfs.getTruck().isSolution(Map.getSingleton().getTruckPos())){
            System.out.println("yeAH");
        }
    }
}
