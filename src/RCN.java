// https://github.com/davidliutest/ics-maze-game 

// Data structure that stores a position of an array used as a tree
// R - index of the row of array, C - index of the column of array
// Contains additional variables to hold graph-related information
public class RCN {

    public int r, c, cost, heuristic;
    public boolean visited;
    public RCN parent;

    public RCN(int r, int c, int h, int co, RCN par) {
        this.r = r;
        this.c = c;
        this.heuristic = h;
        this.cost = co;
        this.parent = par;
    }

    public double dist(RCN rcn) {
        return Math.sqrt(Math.pow(this.c - rcn.c, 2) + Math.pow(this.r - rcn.r, 2));
    }

    public boolean isNeighbor(RCN rcn) {
        int dc = Math.abs(this.c - rcn.c);
        int dr = Math.abs(this.r - rcn.r);
        return (dc == 1 && dr == 0)||(dc == 0 && dr == 1);
    }

    public void setRC(int r, int c) {
        this.r = r;
        this.c = c;
    }

}
