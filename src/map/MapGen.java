package map;

import editor.MapData;
import datastruct.RC;
import entities.Entity;
import models.Model;
import models.TextureModel;
import org.lwjgl.util.vector.Vector3f;
import render.Loader;
import render.OBJLoader;
import textures.Texture;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MapGen {

    private int mapr, mapc;
    int[][] map;
    private final int[] moveR = {0, -1, 0, 1};
    private final int[] moveC = {-1, 0, 1, 0};
    private final int[] moveRDia = {-1, 1, -1, 1};
    private final int[] moveCDia = {-1, -1, 1, 1};
    private boolean[][] visited;
    private List<RC> deque;

    public List<Entity> load(MapData mapData, Loader loader) throws FileNotFoundException {
        Model model = OBJLoader.loadObjectModel("cube", loader);
        TextureModel cube1 = new TextureModel(model, new Texture(loader.loadTexture("tile1")));
        TextureModel cube2 = new TextureModel(model, new Texture(loader.loadTexture("tile2")));
        TextureModel cube3 = new TextureModel(model, new Texture(loader.loadTexture("tile3")));
        TextureModel cube4 = new TextureModel(model, new Texture(loader.loadTexture("tile4")));
        TextureModel cube5 = new TextureModel(model, new Texture(loader.loadTexture("tile5")));
        List<Entity> entityList = new ArrayList<Entity>();
        for (int i = 0; i < mapData.mapr; i++) {
            for (int j = 0; j < mapData.mapc; j++) {
                if (mapData.data[i][j] == 1)
                    entityList.add(
                            new Entity(cube1, new Vector3f(j * 10, 0, (i - 10) * 10), 0, 0, 0, 5)
                    );
                else if (mapData.data[i][j] == 2)
                    entityList.add(
                            new Entity(cube2, new Vector3f(j * 10, 0, (i - 10) * 10), 0, 0, 0, 5)
                    );
                else if (mapData.data[i][j] == 3)
                    entityList.add(
                            new Entity(cube3, new Vector3f(j * 10, 0, (i - 10) * 10), 0, 0, 0, 5)
                    );
                else if (mapData.data[i][j] == 4)
                    entityList.add(
                            new Entity(cube4, new Vector3f(j * 10, 0, (i - 10) * 10), 0, 0, 0, 5)
                    );
                else if (mapData.data[i][j] == 5)
                    entityList.add(
                            new Entity(cube5, new Vector3f(j * 10, 0, (i - 10) * 10), 0, 0, 0, 5)
                    );
            }
        }
        return entityList;
    }

    public List<Entity> gen(int r, int c, Loader loader) throws FileNotFoundException {
        this.mapr = r;
        this.mapc = c;
        map = new int[mapr][mapc];
        RC start = new RC((int) (Math.random() * mapr), (int) (Math.random() * mapc));
        visited = new boolean[mapr][mapc];
        deque = new ArrayList<RC>();
        deque.add(start);
        while (!deque.isEmpty()) {
            dfs(deque.remove((int) (Math.random() * deque.size())));
        }
        Model model = OBJLoader.loadObjectModel("cube", loader);
        TextureModel staticModel = new TextureModel(model, new Texture(loader.loadTexture("tile1")));
        List<Entity> entityList = new ArrayList<Entity>();
        for (int i = 0; i < mapr; i++) {
            for (int j = 0; j < mapc; j++) {
                    System.out.print(map[i][j]);
                    if (map[i][j] == 0) {
                        entityList.add(
                                new Entity(
                                        staticModel,
                                        new Vector3f(j * 10, 0, (i - 10) * 10),
                                        0, 0, 0, 5
                                )
                        );
                    }
            }
            System.out.println();
        }
        return entityList;
    }
    //generates maze
    private void dfs(RC cur) {
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
                    newR > 0 && newR < mapr-1 &&
                            newC > 0 && newC < mapc-1 &&
                            !visited[newR][newC] &&
                            check(next, cur, moveR[i], moveC[i])
                    ) {
                dfs(next);
                return;
            }
        }
    }

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

    public static boolean chance(double c) {
        return Math.random() < c / 100.0;
    }

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
    public int[][] getMapArray(){
        return map;
    }
}