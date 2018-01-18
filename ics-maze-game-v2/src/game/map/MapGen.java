package game.map;

import engine.entities.Entity;
import game.datastruct.MapData;
import game.datastruct.RC;
import game.entities.mob.Enemy;
import game.entities.staticentities.Floor;
import game.entities.staticentities.Shutdown;
import game.entities.staticentities.Wall;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

// Generates the map using DFS with recusive backtracking
public class MapGen {

    private Handler handler;
    private final float wallLen = 20f;

    private int mapr, mapc;
    private int[][] map;
    private final int[] moveR = {0, -1, 0, 1};
    private final int[] moveC = {-1, 0, 1, 0};
    private final int[] moveRDia = {-1, 1, -1, 1};
    private final int[] moveCDia = {-1, -1, 1, 1};
    private boolean[][] visited, deadEnds;
    private List<RC> deque;
    private int[][] dist;

    public MapGen(Handler handler) {
        this.handler = handler;
    }

    public MapData gen(int r, int c) {
        // Initialize variables
        this.mapr = r;
        this.mapc = c;
        map = new int[mapr][mapc];
        visited = new boolean[mapr][mapc];
        RC begin = new RC((int)(Math.random()*(mapr-2))+1, (int)(Math.random()*(mapc-2))+1 );
        deque = new ArrayList<RC>();
        dist = new int[mapr][mapc];
        deadEnds = new boolean[mapr][mapc];
        // Generation
        deque.add(begin);
        while (!deque.isEmpty()) {
            dfsGen(deque.remove((int) (Math.random() * deque.size())));
        }
        // Generate block list
        List<Entity> entityList = new ArrayList<Entity>();
        for (int i = 0; i < mapr; i++) {
            for (int j = 0; j < mapc; j++) {
                if (map[i][j] == 0) {
                    Wall wall = new Wall(
                            handler,
                            new Vector3f(0, 0, 0),
                            0, 0, 0, 1f
                    );
                    wall.setScale(wallLen/wall.getLenX());
                    wall.setPos(new Vector3f(j*wallLen+wallLen/2, 0, i*wallLen+wallLen/2));
                    entityList.add(wall);
                }
            }
        }
        // Generates start and finish of maze using the start and end points of the
        // diameter of the "graph" (the longest path of the maze)
        for(int i = 0; i < mapr; i++)
            for(int j = 0; j < mapc; j++)
                dist[i][j] = -1;
        dist[begin.r][begin.c] = 0;
        dfs(begin, null);
        RC start = new RC(0, 0);
        int max = -1;
        for(int i = 0; i < mapr; i++) {
            for (int j = 0; j < mapc; j++) {
                if(dist[i][j] > max) {
                    max = dist[i][j];
                    start.r = i;
                    start.c = j;
                }
            }
        }
        for(int i = 0; i < mapr; i++)
            for(int j = 0; j < mapc; j++)
                dist[i][j] = -1;
        dist[start.r][start.c] = 0;
        dfs(start, null);
        max = -1;
        RC end = new RC(0, 0);
        for(int i = 0; i < mapr; i++) {
            for (int j = 0; j < mapc; j++) {
                if(dist[i][j] > max) {
                    max = dist[i][j];
                    end.r = i;
                    end.c = j;
                }
            }
        }
        System.out.println(start + " " + end);

        // Creates the floor of the map
        Floor floor = new Floor(
                handler,
                new Vector3f(0, 0, 0),
                0, 0, 0, 1f
        );
        float floorLen = mapr*wallLen;
        floor.setScale(floorLen/floor.getLenX());
        floor.setPos(new Vector3f(floorLen/2,0,floorLen/2));
        entityList.add(floor);

        // Checks for dead ends and spawns enemies
        int maxEnemies = (int)((mapr * mapc) * 0.03f);
        for(int i = 1; i < mapr-1; i++) {
            for(int j = 1; j < mapc-1; j++) {
                int count = 0;
                if(map[i][j] == 1 && !new RC(i,j).equals(start) && !new RC(i,j).equals(end)) {
                    for (int k = 0; k < 4; k++) {
                        if (map[i + moveR[k]][j + moveC[k]] == 0)
                            count++;
                    }
                }
                if(count == 3 && (new RC(i, j).dist(start) > 3)) {
                    Enemy enemy = new Enemy(
                            handler, new Vector3f(0, 0, 0), 0, 0, 0, 0.5f, new RC(i, j)
                    );
                    enemy.setPos(new Vector3f(j*wallLen+wallLen/2, 0, i*wallLen+wallLen/2));
                    entityList.add(enemy);
                }
                if(maxEnemies == 0)
                    break;
            }
        }

        Shutdown shutdown = new Shutdown(
                handler, new Vector3f(0, 0, 0),0, 0, 0, 3f, end
        );
        shutdown.setPos(new Vector3f(end.c*wallLen+wallLen/2, 0, end.r*wallLen+wallLen/2));
        entityList.add(shutdown);

        return new MapData(mapr, mapc, map, entityList, start, end);
    }

    // DFS to generate the maze
    private void dfsGen(RC cur) {
        if(!visited[cur.r][cur.c]) {
            deque.add(cur);
            visited[cur.r][cur.c] = true;
            map[cur.r][cur.c] = 1;
        }
        int[] order = genOrder();
        for(int i : order) {
            int newR = cur.r + moveR[i];
            int newC = cur.c + moveC[i];
            RC next = new RC(newR, newC);
            if(
                newR > 0 && newR < mapr-1 && newC > 0 && newC < mapc-1 &&
                !visited[newR][newC] && check(next, cur, moveR[i], moveC[i])
            ) {
                dfsGen(next);
                break;
            }
        }
    }

    // Core DFS method
    public void dfs(RC cur, RC par) {
        for(int i = 0; i < 4; i++) {
            int newR = cur.r + moveR[i];
            int newC = cur.c + moveC[i];
            RC next = new RC(newR, newC);
            if(
                newR > 0 && newR < mapr-1 && newC > 0 && newC < mapc-1 &&
                (par == null || (par != null && !next.equals(par))) && map[newR][newC] == 1
            ) {
                dist[newR][newC] = dist[cur.r][cur.c]+1;
                dfs(next, cur);
            }
        }
    }

    // Checks if current location is suitable to be empty
    private boolean check(RC child, RC parent, int dr, int dc) {
        RC a, b;
        if(dr != 0) {
            a = new RC(parent.r, parent.c-1);
            b = new RC(parent.r, parent.c+1);
        }else{
            a = new RC(parent.r-1, parent.c);
            b = new RC(parent.r+1, parent.c);
        }
        for(int i = 0; i < 4; i++) {
            RC newrc = new RC(child.r + moveR[i], child.c + moveC[i]);
            if(!newrc.equals(parent) && map[newrc.r][newrc.c] == 1)
                return false;
            newrc = new RC(child.r + moveRDia[i], child.c + moveCDia[i]);
            if(!newrc.equals(a) && !newrc.equals(b) && map[newrc.r][newrc.c] == 1)
                return false;
        }
        return true;
    }

    // Generates an outcome based on probability
    public static boolean chance(double c) {
        return Math.random() < c / 100.0;
    }

    // Generates a permutation of {0, 1, 2, 3}
    private int[] genOrder() {
        int[] order = {0, 1, 2, 3};
        for(int i = 3; i >= 1; i--) {
            int swap = (int)(Math.random() * (i+1));
            int temp = order[i];
            order[i] = order[swap];
            order[swap] = temp;
        }
        return order;
    }

    public float getWallLen() {
        return  wallLen;
    }

}
