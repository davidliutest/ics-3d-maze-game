package editor;

public class Map {

    private transient EditorApplication app;
    private transient Tile[][] map;
    private int mapr, mapc;

    public Map(EditorApplication app, int mapr, int mapc) {
        this.app = app;
        this.mapr = mapr;
        this.mapc = mapc;
        map = new Tile[mapr][mapc];
    }

    public void create() {
        for(int i = 0; i < mapr; i++) {
            for(int j = 0; j < mapc; j++) {
                map[i][j] = new Tile(app, app.getEditor().getImg(0), 0, 0, 0, 0, 0);
            }
        }
    }

    public Tile tile(int r, int c) {
        return map[r][c];
    }

    public int height() {
        return  mapr;
    }

    public int width() {
        return mapc;
    }

    public MapData save() {
        MapData mapSave = new MapData(mapr, mapc);
        for(int i = 0; i < mapr; i++)
            for(int j = 0; j < mapc; j++)
                mapSave.data[i][j] = map[i][j].id();
        return mapSave;
    }

    public void load(MapData mapLoad) {
        map = new Tile[mapLoad.mapr][mapLoad.mapc];
        for(int i = 0; i < mapr; i++)
            for(int j = 0; j < mapc; j++)
                map[i][j] = new Tile(
                        app,
                        app.getEditor().getImg(mapLoad.data[i][j]),
                        0, 0, 0, 0,
                        mapLoad.data[i][j]
                );
    }

}
