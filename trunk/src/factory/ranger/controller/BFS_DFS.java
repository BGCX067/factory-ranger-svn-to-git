package factory.ranger.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class BFS_DFS {

    LinkedList<Map> queue;
    ArrayList<Map> visited;
    public static String buffer = "";

    public BFS_DFS() {
        queue = new LinkedList<Map>();
        visited = new ArrayList<Map>();
    }

    public boolean isVisited(Map b) {
        for (Map _b : visited) {
            if (b.isSame(_b)) {
                return true;
            }
        }
        return false;
    }

    public void BFS(Map root) {
        queue.add(root);
        visited.add(root);

        if (root.isSolution()) {
            //printTree();
            queue.clear();
            //visited.clear();
            return;
        }

        Map parent, child;
        while (!queue.isEmpty()) {
            parent = queue.removeFirst();
            for (int i = Map.DIR_UP; i <= Map.DIR_LEFT; i++) {
                child = parent.getChild(i);
                if (child != null) {
                    
                    if (child.isSolution()) {
                        parent.addChild(child, i);
                        visited.add(child);
                        //printTree();
                        queue.clear();
                        //visited.clear();
                        return;
                    }
                    if (!isVisited(child)) {
                        parent.addChild(child, i);
                        visited.add(child);
                        queue.add(child);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        BFS_DFS m = new BFS_DFS();
        Map root = new Map();
        //initTable(root, "input3.in");

        m.BFS(root);
        root.printBoard();
        for(int i=0; i<m.visited.size(); i++){
            System.out.println(m.visited.get(i).getPX()+", "+m.visited.get(i).getPY());
        }
        
//        printtoFile("Hasil_BFS.txt");
//
//        root = new Board();
//        initTable(root, "input3.in");
//        cp.DFS(root);
//        printtoFile("Hasil_DFS.txt");

    }
}
