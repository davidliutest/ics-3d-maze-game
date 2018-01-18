package game;

import editor.MapData1;
import game.main.App;

// Contains main method that launches game
public class Launcher {

    public static void run(MapData1 mapData1){
        new App().run(mapData1);
    }
    public static void run() { new App().run();}
}
