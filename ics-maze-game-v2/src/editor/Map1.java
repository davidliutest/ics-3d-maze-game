package editor;

// Stores information of the maze
public class Map1 {

    private transient EditorApplication app;
    private transient Tile[][] map;
    private int mapr, mapc;

    // Map constructor
    public Map1(EditorApplication app, int mapr, int mapc) {
        this.app = app;
        this.mapr = mapr;
        this.mapc = mapc;
        map = new Tile[mapr][mapc];
    }
    // Creates blank map
    public void create() {
        for(int i = 0; i < mapr; i++) {
            for(int j = 0; j < mapc; j++) {
                map[i][j] = new Tile(app, app.getEditor().getImg(0), 0, 0, 0, 0, 0);
            }
        }
    }
    // Tile getter
    public Tile tile(int r, int c) {
        return map[r][c];
    }
    // Height getter
    public int height() {
        return  mapr;
    }
    // Width getter
    public int width() {
        return mapc;
    }
    // Save map
    public MapData1 save() {
        MapData1 mapSave = new MapData1(mapr, mapc);
        for(int i = 0; i < mapr; i++)
            for(int j = 0; j < mapc; j++)
                if(map[i][j].id() == 0){
                    mapSave.data[i][j]=1;
                }
                else if(map[i][j].id() == 1){
                    mapSave.data[i][j]=0;
                }
                else {
                    mapSave.data[i][j] = map[i][j].id();
                }
        return mapSave;
    }
    // Load map
    public void load(MapData1 mapLoad) {
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
