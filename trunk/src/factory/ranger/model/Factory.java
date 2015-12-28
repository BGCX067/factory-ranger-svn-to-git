/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.model;

/**
 *
 * @author axioo
 */
public class Factory {
    
    private static Factory factory = new Factory();
    
    public static Factory getSingleton(){
        return factory;
    }
    //attribute
    private int $;  //rupiah
    private int H;  //jam operasi
    private int M;  //jumlah mesin
    private int N;  //sewa maks
    private int Y;  //kapasitas angkut
    
    //constructor
    public Factory() {}
    
    public Factory(int $, int H, int M, int N, int Y) {
        this.$ = $;
        this.H = H;
        this.M = M;
        this.N = N;
        this.Y = Y;
    }
    //getter and setter
    public void setFactory(Factory factory){
        this.$ = factory.$;
        this.H = factory.H;
        this.M = factory.M;
        this.N = factory.N;
        this.Y = factory.Y;
    }
    
    public int get$() {
        return $;
    }

    public void set$(int $) {
        this.$ = $;
    }

    public int getH() {
        return H;
    }

    public void setH(int H) {
        this.H = H;
    }

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getN() {
        return N;
    }

    public void setN(int N) {
        this.N = N;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
}
