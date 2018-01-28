// https://github.com/davidliutest/ics-maze-game 


// Stores information of the maze
public class Map {

    private int mapr, mapc;
    private int[][] map;
    private RC start, end;
    private Handler handler;
    private MapGen mapGen;

    public Map(Handler handler, int mapr, int mapc) {
        this.handler = handler;
        mapGen = new MapGen(handler);
        this.mapr = mapr;
        this.mapc = mapc;
        this.map = new int[mapr][mapc];
    }

    // Generates the maze using MapGen
    public void create() {
        MapData mapData = mapGen.gen(mapr, mapc);
        map = mapData.map;
        start = mapData.start;
        end = mapData.end;
        handler.getEntityManager().addEntities(mapData.entityList);
    }

    // Getters and Setters

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
