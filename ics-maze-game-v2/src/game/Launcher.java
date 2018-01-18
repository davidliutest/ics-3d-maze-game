package game;

import editor.MapData1;
import game.main.App;

public class Launcher {

    public static void run(MapData1 mapData1){
        new App(mapData1).run();
    }
    public static void run() { new App().run();}
}
