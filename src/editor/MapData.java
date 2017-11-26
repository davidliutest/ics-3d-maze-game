package editor;

import java.io.Serializable;

public class MapData implements Serializable {

    public int mapr, mapc;
    public int[][] data;

    public MapData(int mapr, int mapc) {
        this.mapr = mapr;
        this.mapc = mapc;
        data = new int[mapr][mapc];
    }

}
