package game.map;

import editor.MapData1;
import engine.entities.Entity;
import game.datastruct.MapData;
import game.datastruct.RC;
import game.entities.mob.Enemy;
import game.entities.staticentities.Floor;
import game.entities.staticentities.Wall;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class Map {

    private int mapr, mapc;
    private int[][] map;
    private RC start, end;
    private Handler handler;
    private MapGen mapGen;
    private List<Entity> eList;
    private final float wallLen = 20f;

    public Map(Handler handler, int mapr, int mapc) {
        this.handler = handler;
        mapGen = new MapGen(handler);
        this.mapr = mapr;
        this.mapc = mapc;
        this.map = new int[mapr][mapc];
    }

    public void create() {
        MapData mapData = mapGen.gen(mapr, mapc);
        map = mapData.map;
        start = mapData.start;
        end = mapData.end;
        handler.getEntityManager().addEntities(mapData.entityList);
    }

    public void create(MapData mapData){
        for(int i = 0; i < mapData.map[1].length; i++){
            for(int j =0; j<mapData.map[i].length;j++) {
                if(mapData.map[i][j] == 2)
                    start = new RC(i, j);
                if(mapData.map[i][j] == 3)
                    end = new RC(i,j);
            }
        }
        map = mapData.map;
        handler.getEntityManager().addEntities(mapData.entityList);
    }
    public MapData convert(MapData1 mapData1, List<Entity> entity){
        return new MapData(mapData1.mapr,mapData1.mapc, mapData1.data, entity, mapData1.start, mapData1.end);
    }
    public List<Entity> makeEntityList(MapData1 mapdata1){
        eList = new ArrayList<>();
        for(int i = 0; i < mapdata1.data.length; i++){
            for(int j =0; j<mapdata1.data[i].length;j++) {
                if (mapdata1.data[i][j] == 0) {
                    Wall wall = new Wall(
                            handler,
                            new Vector3f(0, 0, 0),
                            0, 0, 0, 1f
                    );
                    wall.setScale(wallLen / wall.getLenX());
                    wall.setPos(new Vector3f(j * wallLen + wallLen / 2, 0, i * wallLen + wallLen / 2));
                    eList.add(wall);
                }
                if(mapdata1.data[i][j] == 4){
                    Enemy enemy = new Enemy(
                            handler, new Vector3f(0, 0, 0), 0, 0, 0, 1f, new RC(i, j)
                    );
                    enemy.setPos(new Vector3f(j*wallLen+wallLen/2, 0, i*wallLen+wallLen/2));
                    eList.add(enemy);
                }
            }
        }
        Floor floor = new Floor(
                handler,
                new Vector3f(0, 0, 0),
                0, 0, 0, 1f
        );
        float floorLen = mapr*wallLen;
        floor.setScale(floorLen/floor.getLenX());
        floor.setPos(new Vector3f(floorLen/2,0,floorLen/2));
        eList.add(floor);
        return eList;
    }
    public int getMapR() {
        return mapr;
    }

    public int getMapC() {
        return mapc;
    }

    public int getMapTile(int r, int c) {
        return map[r][c];
    }

    public RC getStart() {
        return start;
    }

    public RC getEnd() {
        return end;
    }

    public float getWallLen() {
        return mapGen.getWallLen();
    }

}
