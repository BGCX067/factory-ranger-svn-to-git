package factory.ranger.controller; 

import java.util.ArrayList;

public class Map {
    
    public static final int DIR_UP = 0;
    public static final int DIR_RIGHT = 1;
    public static final int DIR_DOWN = 2;
    public static final int DIR_LEFT = 3;
    private int level;
    public char[][] cell = new char[5][5];
    public Map upChild;
    public Map rightChild;
    public Map downChild;
    public Map leftChild;
    public int jumlahVendor = 0;
    
    public Map() {
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 5; j++) {
//                cell[i][j] = '.';
//            }
//        }
        cell[0][0] = 'A'; cell[0][1] = '#'; cell[0][2] = '.'; cell[0][3] = '.'; cell[0][4] = 'B';
        cell[1][0] = '.'; cell[1][1] = '.'; cell[1][2] = '#'; cell[1][3] = '.'; cell[1][4] = '#';
        cell[2][0] = '.'; cell[2][1] = '.'; cell[2][2] = '.'; cell[2][3] = '.'; cell[2][4] = 'P';
        cell[3][0] = '.'; cell[3][1] = '#'; cell[3][2] = '.'; cell[3][3] = '#'; cell[3][4] = '.';
        cell[4][0] = '.'; cell[4][1] = 'D'; cell[4][2] = '.'; cell[4][3] = '#'; cell[4][4] = 'C';
        level = 0;
        upChild = null;
        rightChild = null;
        downChild = null;
        leftChild = null;
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cell[i][j] != '.' && cell[i][j] != '#' && cell[i][j] != 'P')
                    jumlahVendor++;
            }
        }
    }

    public Map(Map _b) {
        for (int i = 0; i < 5; i++) {
            System.arraycopy(_b.cell[i], 0, cell[i], 0, 5);
        }
        level = _b.level;
        upChild = null;
        rightChild = null;
        downChild = null;
        leftChild = null;
        jumlahVendor = _b.jumlahVendor;
    }
    
    public int getLevel() {
        return level;
    }

    public void setLevel(int _level) {
       level = _level;
    }

    public int getChildCount() {
        int count = 0;
        if (upChild != null) {
            count++;
        }
        if (rightChild != null) {
            count++;
        }
        if (downChild != null) {
            count++;
        }
        if (leftChild != null) {
            count++;
        }

        return count;
    }
    
    public void addChild(Map child, int dir) {
        switch (dir) {
            case DIR_UP:
                upChild = child;
                break;
            case DIR_RIGHT:
                rightChild = child;
                break;
            case DIR_DOWN:
                downChild = child;
                break;
            case DIR_LEFT:
                leftChild = child;
                break;
        }
    }

    public String getBoardString() {
        String s = "";

        for (int i = 0; i < 5; i++) {
            s += getBoardString(i);
            s += "\n";
        }
        return s;
    }

    public String getBoardString(int i) {
        String s = "";

        for (int j = 0; j < 5; j++) {
            if (cell[i][j] == '#') {
                s += "  #";
            } else if (cell[i][j] == '.') {
                s += "  .";
            } else {
                if (cell[i][j] == 'P') {
                    s += " P";
                } else {
                    s += " ";
                    s += cell[i][j];
                }
            }
        }
        return s;
    }

    public void printBoard() {
        System.out.println(getBoardString());
    }
    
    public int getWidth() {
        if ((upChild == null) && (rightChild == null)
                && (downChild == null) && (leftChild == null)) {
            return 1;
        }

        int width = 0;

        if (upChild != null) {
            width += upChild.getWidth();
        } else {
        }
        if (rightChild != null) {
            width += rightChild.getWidth();
        } else {
        }
        if (downChild != null) {
            width += downChild.getWidth();
        } else {
        }
        if (leftChild != null) {
            width += leftChild.getWidth();
        } else {
        }

        return width;
    }

    public int getPX() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cell[i][j] == 'P') {
                    return i;
                }
            }
        }
        return 4;
    }

    public int getPY() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cell[i][j] == 'P') {
                    return j;
                }
            }
        }
        return 4;
    }

    public boolean hasChild() {
        return ((upChild != null) || (rightChild != null) || (downChild != null) || (leftChild != null));
    }
    
    public ArrayList<Map> getChildren() {
        ArrayList<Map> _board = new ArrayList<Map>();
        Map temp;
        for (int i = 0; i < 5; i++) {
            temp = getChild(i);
            if (temp != null) {
                _board.add(getChild(i));
            }
        }

        return _board;
    }

    public Map getChild(int dir) {
        Map child;
        int x = getPX();
        int y = getPY();
        char val;

        switch (dir) {
            case DIR_UP:
                if (x == 0) {
                    child = null;
                } else {
                    val = cell[x - 1][y];
                    if (val == '#') {
                        child = null;
                    } else if (val != '.') {
                        child = new Map(this);
                        child.cell[x][y] = '.';
                        child.cell[x - 1][y] = 'P';
                    } else {
                        child = new Map(this);
                        child.cell[x][y] = val;
                        child.cell[x - 1][y] = 'P';
                    }
                }
                break;
            case DIR_RIGHT:
                if (y == 4) {
                    child = null;
                } else {
                    val = cell[x][y + 1];
                    if (val == '#') {
                        child = null;
                    } else if (val != '.') {
                        child = new Map(this);
                        child.cell[x][y] = '.';
                        child.cell[x][y + 1] = 'P';
                    } else {
                        child = new Map(this);
                        child.cell[x][y] = val;
                        child.cell[x][y + 1] = 'P';
                    }
                }
                break;
            case DIR_DOWN:
                if (x == 4) {
                    child = null;
                } else {
                    val = cell[x + 1][y];
                    if (val == '#') {
                        child = null;
                    } else if (val != '.') {
                        child = new Map(this);
                        child.cell[x][y] = '.';
                        child.cell[x + 1][y] = 'P';
                    } else {
                        child = new Map(this);
                        child.cell[x][y] = val;
                        child.cell[x + 1][y] = 'P';
                    }
                }
                break;
            case DIR_LEFT:
                if (y == 0) {
                    child = null;
                } else {
                    val = cell[x][y - 1];
                    if (val == '#') {
                        child = null;
                    } else if (val != '.') {
                        child = new Map(this);
                        child.cell[x][y] = '.';
                        child.cell[x][y - 1] = 'P';
                    } else {
                        child = new Map(this);
                        child.cell[x][y] = val;
                        child.cell[x][y - 1] = 'P';
                    }
                }
                break;
            default:
                child = null;
        }

        if (child != null) {
            child.level = level + 1;
        }
        return child;
    }

    public boolean isSolution() {
        int jmlVendorBaru = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cell[i][j] != '.' && cell[i][j] != '#' && cell[i][j] != 'P')
                    jmlVendorBaru++;
            }
        }
        
        if (jmlVendorBaru < jumlahVendor) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSame(Map _b) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (cell[i][j] != _b.cell[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
