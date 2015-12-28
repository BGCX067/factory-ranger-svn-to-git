/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.ranger.model;

/**
 *
 * @author axioo
 */
public class Machine {
    
    private static Machine machine = new Machine();
    
    public static Machine getSingleton(){
        return machine;
    }
    
    //attribute
    private int number;
    private int T;  //beban kerja per hari
    private int W;  //beban kerja per periode
    private int S;  //istirahat
    private int P;  //produktivitas
    private int I;  //bahan baku
    private int C;  //cost overload
    private int R;  //biaya sewa
    private int Dependensi;
    private Product product;

    //constructor
    public Machine() {}
    
    public Machine(int number, int T, int W, int S, int P,
                    int I, int C, int R, int Dependensi, Product product) {
        this.number = number;
        this.T = T;
        this.W = W;
        this.S = S;
        this.P = P;
        this.I = I;
        this.C = C;
        this.R = R;
        this.Dependensi = Dependensi;
        this.product = product;
    }
    
    //getter and setter
    public void setMachine(Machine machine){
        this.number = machine.number;
        this.T = machine.T;
        this.W = machine.W;
        this.S = machine.S;
        this.P = machine.P;
        this.I = machine.I;
        this.C = machine.C;
        this.R = machine.R;
        this.Dependensi = machine.Dependensi;
        this.product = machine.product;
    }
    
    public Product getProduct() {
        return product;
    }
    
    public int getNumber() {
        return number;
    }
    
    public int getC() {
        return C;
    }

    public void setC(int C) {
        this.C = C;
    }

    public int getDependensi() {
        return Dependensi;
    }

    public void setDependensi(int Dependensi) {
        this.Dependensi = Dependensi;
    }

    public int getI() {
        return I;
    }

    public void setI(int I) {
        this.I = I;
    }

    public int getP() {
        return P;
    }

    public void setP(int P) {
        this.P = P;
    }

    public int getR() {
        return R;
    }

    public void setR(int R) {
        this.R = R;
    }

    public int getS() {
        return S;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getT() {
        return T;
    }

    public void setT(int T) {
        this.T = T;
    }

    public int getW() {
        return W;
    }

    public void setW(int W) {
        this.W = W;
    }
    
    public boolean isDependen(Machine m){
        if (this.Dependensi == m.number)
            return true;
        else
            return false;
    }
}
