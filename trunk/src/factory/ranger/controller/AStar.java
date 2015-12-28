// Nama         : Bagus Rahman Aryabima
// NIM          : 13509044
// Nama File    : AStar.java
// Tanggal      : 21 Februari 2012
// Deskripsi    : Algoritma AStar pada pencarian jalur

package factory.ranger.controller;
import factory.ranger.model.Factory;
import factory.ranger.model.Map;
import factory.ranger.model.ListVendor;
import factory.ranger.model.Heuristic;
import factory.ranger.model.Vendor;
import factory.ranger.utility.Input;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

public class AStar {
    public Heuristic Stats;
    public Map Region;
    public ListVendor Love;
    public Truck MyTruck;
    public final int INIT_X = 0;
    public final int INIT_Y = 0;
    public final int RIGHT = 1;
    public final int DOWN = 2;
    public final int LEFT = 3;
    public final int UP = 4;
    public ArrayList<Point> OpenSet;
    public ArrayList<Point> ClosedSet;
    public ArrayList<Point> Path;
    public ArrayList<Point> HPath;
    public HashMap<Point, Point> FRoute;
    public HashMap<Point, Point> HRoute;
    
    public AStar(Vendor[] love) {
        Stats = new Heuristic();
        Stats.FillTable();
        Region = Map.getSingleton();
        //Love = ListVendor.getSingleton();
        Love = new ListVendor();
        Love.setListVendor(love);
        MyTruck = new Truck(Region.getSingleton().getTruckPos(), Love.getSingleton().getNameVendors());
        OpenSet = new ArrayList<Point>();
        OpenSet.clear();
        OpenSet.add(Region.getTruckPos());
        ClosedSet = new ArrayList<Point>();
        ClosedSet.clear();
        Path = new ArrayList<Point>();
        Path.clear();
        HPath = new ArrayList<Point>();
        HPath.clear();
        FRoute = new HashMap<Point, Point>();
        FRoute.clear();
        HRoute = new HashMap<Point, Point>();
        HRoute.clear();
    }
    
//    Menghasilkan cost dari suatu titik ke suatu vendor tertentu
    public double GenerateCost(Point T, Point V) {
        double TCost = 9999;
        int Cost = 0;
        
        if (Region.getTile(V.x, V.y) > 0) {
            //System.out.println(Region.getTile(V.x, V.y));
            TCost = Cost + Stats.Table[Region.getTile(V.x, V.y) - 64][Stats.PointToNode(T)];
        }
        return TCost;
    }
    
//    Mengembalikan tujuan (vendor) terbaik dari suatu titik
    public Point BestDestination(Point T) {
        Point V = new Point();
        double Now, Best;
        int Min = 0;
        
        Best = GenerateCost(T, Love.getListVendor().get(0).getPosition());
        for (int i=1; i<=Love.getListVendor().size(); i++) {
            Now = GenerateCost(T, Love.getListVendor().get(i-1).getPosition());
            if (Now < Best) {
                Best = Now;
                Min = i-1;
            }
        }
        
        V = Love.getListVendor().get(Min).getPosition();
        return V;
    }
    
//    Mengembalikan cost dari suatu titik ke tujuan (vendor) terbaik
    public double CostToBD(Point T) {
        return (GenerateCost(T,BestDestination(T)));
    }
    
////    Memilih simpul mana dari OpenSet yang akan dibangkitkan selanjutnya (based on designated destination)
//    public Point DS(Point T) {
//        double Now, Best;
//        Point Opt = new Point();
//        
////        Bingung inisialisasi bestnya nih... T_T
//        Best = 0;
//        for (Point P : OpenSet) {
//            Now = GenerateCost(P, T);
//            if ((Best == 0) || (Now < Best)) {
//                Best = Now;
//                Opt = P;
//            }
//        }
//        
//        return Opt;
//    }
    
////    Memilih simpul mana dari OpenSet yang akan dibangkitkan selanjutnya (based on optimal destination)
//    public Point HS() {
//        double Now, Best;
//        Point Opt = new Point();
//        
////        Bingung inisialisasi bestnya nih... T_T
//        Best = 9999;
//        for (Point P : OpenSet) {
//            Now = GenerateCost(P, Region.getFactoryPos());
//            if ((Best == 9999) || (Now < Best)) {
//                Best = Now;
//                Opt = P;
//            }
//        }
//        
//        return Opt;
//    }

    
//    Memilih simpul mana dari OpenSet yang akan dibangkitkan selanjutnya (based on optimal destination)
    public Point OS() {
        double Now, Best;
        Point Opt = new Point();
        
//        Bingung inisialisasi bestnya nih... T_T
        Best = 9999;
        for (Point P : OpenSet) {
            Now = CostToBD(P);
            if ((Best == 9999) || (Now < Best)) {
                Best = Now;
                Opt = P;
            }
        }
        
        return Opt;
    }
    
////    Meninjau sekeliling simpul yang akan dibangkitkan (based on designated destination)
//    public Point DD(Point P, Point Q) {
//        double Now, Best;
//        Point Comp = new Point(); 
//        Point Opt = new Point();
//        
////    Bingung inisialisasi bestnya nih... T_T
//        Best = -9999;
//        Now = 0;
//        for (int A=1; A<5; A++) {
//            switch(A) {
//                case RIGHT:
//                    if (P.x != Region.getWidth()-1) {
//                        Comp = new Point((P.x+1),P.y);
//                    }
//                    break;
//                case DOWN:
//                    if (P.y != Region.getHeight()-1) {
//                        Comp = new Point(P.x,(P.y+1));
//                    }
//                    break;
//                case LEFT:
//                    if (P.x != 0) {
//                        Comp = new Point((P.x-1),P.y);
//                    }
//                    break;
//                case UP:
//                    if (P.y != 0) {
//                        Comp = new Point(P.x,(P.y-1));
//                    }
//                    break;
//            }
//            if (Region.getTile(Comp.x, Comp.y) != -1) {
//                Now = GenerateCost(Comp, Q);
//            }
//            if ((Best == -9999) || (Now < Best)) {
//                Best = Now;
//                Opt = Comp;
//            }
//        }
//        return Opt;
//    }
    
    
////    Meninjau sekeliling simpul yang akan dibangkitkan (based on optimal destination)
//    public Point OD(Point P) {
//        double Now, Best;
//        Point Comp = new Point(); 
//        Point Opt = new Point();
//        
////    Bingung inisialisasi bestnya nih... T_T
//        Best = -9999;
//        Now = 0;
//        for (int A=1; A<5; A++) {
//            switch(A) {
//                case RIGHT:
//                    if (P.x != Region.getWidth()-1) {
//                        Comp = new Point((P.x+1),P.y);
//                    }
//                    break;
//                case DOWN:
//                    if (P.y != Region.getHeight()-1) {
//                        Comp = new Point(P.x,(P.y+1));
//                    }
//                    break;
//                case LEFT:
//                    if (P.x != 0) {
//                        Comp = new Point((P.x-1),P.y);
//                    }
//                    break;
//                case UP:
//                    if (P.y != 0) {
//                        Comp = new Point(P.x,(P.y-1));
//                    }
//                    break;
//            }
//            if (Region.getTile(Comp.x, Comp.y) != -1) {
//                Now = CostToBD(Comp);
//            }
//            if ((Best == -9999) || (Now < Best)) {
//                Best = Now;
//                Opt = Comp;
//            }
//        }
//        return Opt;
//    }

//////    Bergerak ke arah yang paling optimal
////    public void OM(Point P) {
////        int A;
////        A = OD(P);
////        
////        switch(A) {
////            case RIGHT:
////                P.x++;
////                break;
////            case DOWN:
////                P.y++;
////                break;
////            case LEFT:
////                P.x--;
////                break;
////            case UP:
////                P.y--;
////                break;
////        }
////    }
    
////    Prosedur utama (based on designated destination)
//    public void DSolve(Point T) {
//        Point Current = new Point();
//        Point Neighbour = new Point();
//        
//        do {
//            Current = DS(T);
//            if (Region.getTile(Current.x, Current.y) != -1) {
//                OpenSet.remove(Current);
//                ClosedSet.add(Current);
//                Path.add(Current);
//                Neighbour = DD(Current, T);
//                
//                if((!ClosedSet.contains(Neighbour)) && (!OpenSet.contains(Neighbour))) {
//                    OpenSet.add(Neighbour);
//                }
//            }
//        } while (Region.getTile(Current.x, Current.y) != Region.getTile(T.x, T.y));
//    }

////    Prosedur utama (based on optimal destination)
//    public void HSolve() {
//        Point Current = new Point();
//        Point Neighbour = new Point();
//
//        do {
//            Current = HS();
//            System.out.println();
//            System.out.println("(" + Current.x + "," + Current.y + ")");
//            if (Region.getTile(Current.x, Current.y) != -1) {
//                OpenSet.remove(Current);
//                ClosedSet.add(Current);
//                
//                for (int A=1; A<5; A++) {
//                    switch(A) {
//                        case RIGHT:
//                            if ((Current.x != Region.getWidth()-1) && (Region.getTile(Current.x+1, Current.y) != -1)) {
//                                Neighbour = new Point((Current.x+1),Current.y);
//                            }
//                            System.out.println("Kanan");
//                            break;
//                        case DOWN:
//                            if ((Current.y != Region.getHeight()-1) && (Region.getTile(Current.x, Current.y+1) != -1)) {
//                                Neighbour = new Point(Current.x,(Current.y+1));
//                            }
//                            System.out.println("Bawah");
//                            break;
//                        case LEFT:
//                            if ((Current.x != 0) && (Region.getTile(Current.x-1, Current.y) != -1)) {
//                                Neighbour = new Point((Current.x-1),Current.y);
//                            }
//                            System.out.println("Kiri");
//                            break;
//                        case UP:
//                            if ((Current.y != 0) && (Region.getTile(Current.x, Current.y-1) != -1)) {
//                                Neighbour = new Point(Current.x,(Current.y-1));
//                            }
//                            System.out.println("Atas");
//                            break;
//                    }
//                    
//                    if((!ClosedSet.contains(Neighbour)) && (!OpenSet.contains(Neighbour))) {
//                        OpenSet.add(Neighbour);
//                        HRoute.put(new Point (Neighbour.x, Neighbour.y), new Point (Current.x, Current.y));
//                        System.out.println("Tambah");
//                    }
//                }
//            }
//            System.out.println();            
//        } while (Region.getTile(Current.x, Current.y) != 80);
//    }
        
//    Prosedur utama (based on optimal destination)
    public void OSolve() {
        Point Current = new Point();
        Point Neighbour = new Point();

        do {
            Current = OS();
            System.out.println();
            System.out.println("(" + Current.x + "," + Current.y + ")");
            if (Region.getTile(Current.x, Current.y) != -1) {
                OpenSet.remove(Current);
                ClosedSet.add(Current);
                
                for (int A=1; A<5; A++) {
                    switch(A) {
                        case RIGHT:
                            if ((Current.x != Region.getWidth()-1) && (Region.getTile(Current.x+1, Current.y) != -1)) {
                                Neighbour = new Point((Current.x+1),Current.y);
                            }
                            System.out.println("Kanan");
                            break;
                        case DOWN:
                            if ((Current.y != Region.getHeight()-1) && (Region.getTile(Current.x, Current.y+1) != -1)) {
                                Neighbour = new Point(Current.x,(Current.y+1));
                            }
                            System.out.println("Bawah");
                            break;
                        case LEFT:
                            if ((Current.x != 0) && (Region.getTile(Current.x-1, Current.y) != -1)) {
                                Neighbour = new Point((Current.x-1),Current.y);
                            }
                            System.out.println("Kiri");
                            break;
                        case UP:
                            if ((Current.y != 0) && (Region.getTile(Current.x, Current.y-1) != -1)) {
                                Neighbour = new Point(Current.x,(Current.y-1));
                            }
                            System.out.println("Atas");
                            break;
                    }
                    
                    if((!ClosedSet.contains(Neighbour)) && (!OpenSet.contains(Neighbour))) {
                        OpenSet.add(Neighbour);
                        FRoute.put(new Point (Neighbour.x, Neighbour.y), new Point (Current.x, Current.y));
                        System.out.println("Tambah");
                    }
                }
            }
            System.out.println();            
        } while (!Love.isExist(Current));
    }
    
////    Merekonstruksi Path pergi
//    public void Home() {
//        Point F, G;
//        int HelpMe = 1;
//        
//        F = ClosedSet.get(ClosedSet.size() - 1);
//        if (HPath.size() == 0) {
//            HPath.add(F);
//            for (int I=0; I<(ClosedSet.size() - 1); I++) {
//                if (HRoute.containsKey(F)) {
//                    Path.add(HRoute.get(F));
//                    F = HRoute.get(F);
//                }
//            }
//        } else {
//            HPath.add(0,F);
//            for (int I=0; I<(ClosedSet.size() - 1); I++) {
//                if (HRoute.containsKey(F)) {
//                    HPath.add(HelpMe, HRoute.get(F));
//                    HelpMe++;
//                    F = HRoute.get(F);
//                }
//            }
//        }
//    }
    
//    Merekonstruksi Path pergi
    public void Go() {
        Point F, G;
        int HelpMe = 1;
        
        F = ClosedSet.get(ClosedSet.size() - 1);
        if (Path.size() == 0) {
            Path.add(F);
            for (int I=0; I<(ClosedSet.size() - 1); I++) {
                if (FRoute.containsKey(F)) {
                    Path.add(FRoute.get(F));
                    F = FRoute.get(F);
                }
            }
        } else {
            Path.add(0,F);
            for (int I=0; I<(ClosedSet.size() - 1); I++) {
                if (FRoute.containsKey(F)) {
                    Path.add(HelpMe, FRoute.get(F));
                    HelpMe++;
                    F = FRoute.get(F);
                }
            }
        }
    }
    
//    Prosedur terakhir (amiin... ^^)
    public void MS() {
        Point T = new Point();
        int Temp = Factory.getSingleton().getY();
        
        for (int N=0; N<=Love.getListVendor().size(); N++) {
            System.out.println("Let's do this thing!");
            
            if (Temp > 0) {
                if (Path.size() == 0) {
                    T = Region.getTruckPos();
                } else {
                    T.x = Path.get(0).x;
                    T.y = Path.get(0).y;
                }
                ClosedSet.clear();
                OpenSet.clear();
                OpenSet.add(new Point(T.x, T.y));
                OSolve();
                Go();
                
                Love.EraseVendor(Path.get(0));
                System.out.println(Love.getListVendor().size());
                Temp--;
            }
        }
    }
    
////    Mencetak jalur pulang
//    public void PrintHPath() {
//        Point H;
//        
//        for (int N=HPath.size()-1; N>=0; N--) {
//            H = HPath.get(N);
//            HPath.remove(N);
//            System.out.println("(" + H.x + "," + H.y + ")");
//        }
//    }
    
//    Mencetak jalur pergi
    public void PrintPath() {
        Point H;
        
        for (int N=Path.size()-1; N>=0; N--) {
            H = Path.get(N);
            Path.remove(N);
            System.out.println("(" + H.x + "," + H.y + ")");
        }
    }
    
    public static void main(String[] args) {
        Input input = new Input("coba.txt");
        Vendor[] a = new Vendor[3];
        a[0] = ListVendor.getSingleton().getListVendor().get(2);
        a[1] = ListVendor.getSingleton().getListVendor().get(0);
        a[2] = ListVendor.getSingleton().getListVendor().get(3);
        AStar A = new AStar(a);
        double R, S;
        Point D, E, F, G;
        
//        A.OpenSet.add(new Point (0,1));
//        A.OpenSet.add(new Point (0,2));
//                
//        R = A.GenerateCost(new Point (0,0), new Point (4,0));
//        D = A.BestDestination(new Point (1,2));
//        S = A.CostToBD(new Point (4,2));
//        E = A.OS();
//        F = A.OD(new Point (5,2));
//        
//        System.out.println(R);
//        System.out.println("(" + D.x + "," + D.y + ")");
//        System.out.println(S);
//        System.out.println("(" + E.x + "," + E.y + ")");
//        System.out.println("(" + F.x + "," + F.y + ")");
        
//        A.OSolve();
//        A.Go();
        A.MS();
        
        System.out.println("=======================");
        System.out.println(A.Path.get(0));
        System.out.println("=======================");
        
        System.out.println();
        while (!A.ClosedSet.isEmpty()) {
            G = A.ClosedSet.get(0);
            A.ClosedSet.remove(0);
            System.out.println("(" + G.x + "," + G.y + ")");
        }
        
        System.out.println();
        A.PrintPath();
    }
}