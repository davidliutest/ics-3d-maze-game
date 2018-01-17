package editor;

import engine.entities.Entity;
import game.datastruct.RC;
import game.entities.staticentities.Wall;
import org.lwjgl.util.vector.Vector3f;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapData1 implements Serializable {

    public int mapr, mapc;
    public int[][] data;
    public RC start, end;

    //takes points and stores it in data array
    public MapData1(int mapr, int mapc) {
        this.mapr = mapr;
        this.mapc = mapc;
        data = new int[mapr][mapc];
        for(int i = 0; i < data.length; i++){
            for(int j =0; j<data[i].length;j++) {
                if(data[i][j] == 2)
                    start = new RC(i, j);
                if(data[i][j] == 3)
                    end = new RC(i,j);
            }
        }
   }
}
