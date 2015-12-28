// Nama         : Bagus Rahman Aryabima
// NIM          : 13509044
// Nama File    : AStar.java
// Tanggal      : 21 Februari 2012
// Deskripsi    : Algoritma AStar pada pencarian jalur

package factory.ranger.model;
import factory.ranger.model.Map;
import factory.ranger.model.ListVendor;
import factory.ranger.utility.Input;
import java.awt.Point;

public class Heuristic {
    public Map Region;
    public ListVendor Love;
    public double[][] Table;
    public final int VENDOR = 6;
    public final int NODE = 31;
    
    public Heuristic() {
        Region = Map.getSingleton();
        Love = ListVendor.getSingleton();
        Table = new double[Region.getNVendor() + 1][(Region.getHeight() * Region.getWidth()) + 1];
    }
    
    public static double DistanceHeuristic(Point Start, Point Finish) {
        int X, Y;
        double SPD;
        
        X = Finish.x - Start.x;
        Y = Finish.y - Start.y;
        SPD = Math.sqrt((X*X) + (Y*Y));
        
        return SPD;
    }
        
    public int PointToNode(Point P) {
        int Answer;
        Answer = (Region.getSingleton().height * P.x) + P.y + 1;
        return Answer;
    }
    
    public Point NodeToPoint(int N) {
        Point P = new Point();
        P.x = (N-1)/Region.getSingleton().height;
        P.y = (N-1)%Region.getSingleton().height;
        return P;
    }
    
    public void FillTable() {
        int node = 1;
        int vendor;
        for (int i=0; i<Region.getSingleton().width; i++) {
            for (int j=0; j<Region.getSingleton().height; j++) {
                for (vendor=1; vendor<=Region.getSingleton().getNVendor(); vendor++) {
                    if (Region.getSingleton().getTile(i,j) == -1) {
                        Table[vendor][node] = -1;
                    } else {
                        Table[vendor][node] = DistanceHeuristic(new Point(i,j), Love.getSingleton().getListVendor().get(vendor-1).getPosition());
                    }
                }
                node++;
            }
        }
    }
    
    public static void main(String[] args) {
        int N, S;
        double R;
        Point P;
        Input input = new Input("coba.txt");
        Heuristic H = new Heuristic();
        
        R = H.DistanceHeuristic(new Point(0,0), new Point(3,4));
        N = H.PointToNode(new Point(1,1));
        P = H.NodeToPoint(N);
        H.FillTable();
        S = (H.Region.getSingleton().height) * (H.Region.getSingleton().width);
        
        System.out.println(R);
        System.out.println(N);
        System.out.println("(" + P.x + "," + P.y + ")");
        for (int j=1; j<=S; j++) {
            for (int i=1; i<=H.Region.getSingleton().getNVendor(); i++) {
                System.out.print(H.Table[i][j] + "               ");
            }
            System.out.println();
        }
    }
}