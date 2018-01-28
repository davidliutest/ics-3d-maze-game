// https://github.com/davidliutest/ics-maze-game

import java.util.List;

// Data structure that contains info for a map
public class MapData {

    public int mapr, mapc;
    public int[][] map;
    public List<Entity> entityList;
    public RC start, end;

    public MapData(int mr, int mc, int[][] m, List<Entity> el, RC s, RC e) {
        mapr = mr;
        mapc = mc;
        map = m;
        entityList = el;
        start = s;
        end = e;
    }

}
