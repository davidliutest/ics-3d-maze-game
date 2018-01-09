package editor;

import java.io.Serializable;

public class MapData implements Serializable {

    public int mapr, mapc;
    public int[][] data;
    //takes points and stores it in data array
    public MapData(int mapr, int mapc) {
        this.mapr = mapr;
        this.mapc = mapc;
        data = new int[mapr][mapc];
    }

}
