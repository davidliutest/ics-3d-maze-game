package game.main;

// game.Launcher class for the game
// Contains the main game loop

// Common methods: default_contructor, create(), start(), update(), stop(), close(), run() (SPECIAL)
// default_contructor -> initializes variables
// create() -> sets variables to certain values
// start() -> starts function of class
// update() -> updates state of class
// stop() -> temporarily stop function of class
// close() -> closing operation whe program exits

// Stucture
// common methods is first in specified order, then helper methods in order of usage

import editor.MapData1;
import engine.render.Loader;
import engine.render.Renderer;
import engine.render.Window;
import game.datastruct.MapData;
import game.managers.EntityManager;
import game.managers.ModelManager;
import game.map.Map;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

import java.util.Arrays;

public class App {

    private Loader loader;
    private Renderer renderer;
    private ModelManager modelManager;
    private Map map;
    private EntityManager entityManager;
    private Handler handler;
    public static boolean con;

    public App() {
        handler = new Handler();
        loader = new Loader();
        renderer = new Renderer();
        modelManager = new ModelManager(handler);
        entityManager = new EntityManager(handler);
        map = new Map(handler, 20, 20);
        con = true;
    }

    public void create(MapData1 mapData1) {
        handler.create(loader, renderer, modelManager,map , entityManager);
        Window.create();
        modelManager.create();
        map.create(map.convert(mapData1,map.makeEntityList(mapData1)));
        entityManager.create();
        renderer.create(handler.getEntityManager().getPlayer().getPos());
    }
    public void create() {
        handler.create(loader, renderer, modelManager, map, entityManager);
        Window.create();
        modelManager.create();
        map.create();
        entityManager.create();
        renderer.create(handler.getEntityManager().getPlayer().getPos());
    }

    public void run(MapData1 mapData1) {
        create(mapData1);
        // Main game loop
        while(checkStop()) {
            entityManager.update();
            Window.update();
        }
    }

    public void run() {
        create();
        // Main game loop
        while(checkStop()&&con) {
            entityManager.update();
            Window.update();
        }
    }

    public boolean checkStop() {
        return !Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
    }
    public void close() {
        loader.close();
        renderer.close();
        Window.close();
    }

}