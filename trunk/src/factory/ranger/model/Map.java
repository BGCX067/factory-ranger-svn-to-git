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
public class Map {
    
    private static Map map = new Map();
    
    public static Map getSingleton(){
        return map;
    }
    
    //attribute
    int[][] tile;
    int nVendor;
    int width;
    int height;
    Point truck;
    
    public Map(){
        nVendor = 0;
    }
    
    public Map(int width, int height, Point p, int nVendor) {
        this.width = width;
        this.height = height;
        tile = new int[width][height];
        this.nVendor = nVendor;
        truck = p;
    }
    
    public void setMap(Map map){
        this.width = map.width;
        this.height = map.height;
        this.tile = map.tile;
        this.nVendor = map.nVendor;
        this.truck = map.truck;
    }
    
    public Point getTruckPos(){
        return truck;
    }
    
    public int getTile(int x, int y) {
        return tile[x][y];
    }
    
    public int getNVendor() {
        return nVendor;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setTruckPos(Point truck){
        this.truck = truck;
    }
    
    public void setTile(int x, int y, int value) {
        tile[x][y] = value;
    }
    
    public void setNVendor(int n) {
        nVendor = n;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public void printMap() {
        String _map = "";
        int x = truck.x;
        int y = truck.y;  
        for(int j=0; j<height; j++){
            for(int i=0; i<width; i++){
                if(i==x && j==y && tile[i][j]!=80){
                    _map += "?";
                } else {
                    switch(tile[i][j]){
                        case -1 : _map += "#";
                            break;
                        case 0 : _map += ".";
                            break;
                        default : _map += Character.toString((char)tile[i][j]);
                            break;
                    }
                }
            }
            _map+="\n";
        }
        System.out.println(_map);
    }
}
