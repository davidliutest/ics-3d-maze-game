// https://github.com/davidliutest/ics-maze-game

import java.util.ArrayDeque;
import java.util.PriorityQueue;

// Calculates the shortest path a Mob should take to reach a destination
// Map obj is used as the graph
// A* algorithm is used
public class Pathfinder {

    private Map map;
    private RCNCompare rccCompare = new RCNCompare();
    private int[] moveR = {-1, 0, 1, 0};
    private int[] moveC = {0, -1, 0, 1};

    public Pathfinder(Map map) {
        this.map = map;
    }

    // Generates the path the Mob should take
    public ArrayDeque<RC> path(Mob a, Mob b) {
        // Uses a priority queue with heuristics for efficiency
        // (As opposed to BFS)
        PriorityQueue<RCN> open = new PriorityQueue<RCN>(10, rccCompare);
        ArrayDeque<RCN> closed = new ArrayDeque<RCN>();
        RC start = a.getMapPos();
        RC end = b.getMapPos();
        RCN[][] find = new RCN[map.getMapR()][map.getMapC()];
        for(int r = 0; r < map.getMapR(); r++) {
            for(int c = 0; c < map.getMapC(); c++) {
                find[r][c] = new RCN(r, c, 0, 0, null);
            }
        }
        closed.clear();
        open.clear();
        open.add(find[start.r][start.c]);
        find[start.r][start.c].visited = true;
        boolean found = false;

        while( !open.isEmpty() ) {
            RCN cur = open.poll();
            closed.add(cur);
            if(cur.r == end.r && cur.c == end.c) {
                found = true;
            }else{
                for(int i = 0; i < 4; i++) {
                    int newR = cur.r + moveR[i];
                    int newC = cur.c + moveC[i];
                    if(
                        newR >= 0 && newR < map.getMapR() && newC >= 0 && newC < map.getMapC() && !blocked(newR, newC)
                    ) {
                        int nextCost = cur.cost + 1;
                        find[newR][newC].visited = true;
                        RCN neighbour = find[newR][newC];
                        if(nextCost < neighbour.cost) {
                            if(open.contains(neighbour))
                                open.remove(neighbour);
                            if(closed.contains(neighbour))
                                closed.remove(neighbour);
                        }
                        if(!open.contains(neighbour) && !closed.contains(neighbour)) {
                            neighbour.cost = nextCost;
                            neighbour.heuristic = heuristics(end, new RC(neighbour.r, neighbour.c));
                            neighbour.parent = cur;
                            open.add(neighbour);
                        }
                    }
                }
            }
        }
        // No path is found
        if(!found)
            return null;
        ArrayDeque<RC> path = new ArrayDeque<RC>();
        RCN target = find[end.r][end.c];
        while(target != find[start.r][start.c]) {
            path.push(new RC(target.r, target.c));
            target = target.parent;
        }
        return path;
    }

    public int heuristics(RC end, RC cur) {
        return Math.abs(end.c - cur.c) + Math.abs(end.r - cur.r);
    }

    // Checks collision and if path is blocked
    private boolean blocked(int r, int c) {
        return map.getMapTile(r, c) == 0;
    }

}
