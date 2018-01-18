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
import engine.render.gui.GuiRenderer;
import engine.render.Loader;
import engine.render.game.Renderer;
import engine.render.Window;
import game.managers.AssetManager;
import game.managers.StateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class App {

    private Handler handler;
    private StateManager stateManager;
    private Loader loader;
    private Renderer renderer;
    private GuiRenderer guiRenderer;
    private AssetManager modelManager;

    public App(MapData1 mapData1) {
        handler = new Handler();
        loader = new Loader();
        handler.setLoader(loader);
        renderer = new Renderer();
        handler.setRenderer(renderer);
        guiRenderer = new GuiRenderer();
        handler.setGuiRenderer(guiRenderer);
        modelManager = new AssetManager(handler);
        handler.setAssetManager(modelManager);
        stateManager = new StateManager(handler, mapData1);
        handler.setStateManager(stateManager);
    }
    public App() {
        handler = new Handler();
        loader = new Loader();
        handler.setLoader(loader);
        renderer = new Renderer();
        handler.setRenderer(renderer);
        guiRenderer = new GuiRenderer();
        handler.setGuiRenderer(guiRenderer);
        modelManager = new AssetManager(handler);
        handler.setAssetManager(modelManager);
        stateManager = new StateManager(handler);
        handler.setStateManager(stateManager);
    }

    public void create() {
        Window.create();
        renderer.create();
        guiRenderer.create(loader);
        modelManager.create();
        stateManager.create();
    }
    public void run() {
        create();
        // Main game loop
        while(checkStop()) {
            stateManager.update();
            Window.update();
        }
        close();
    }
    public void run(MapData1 mapData1) {
        create();
        // Main game loop
        while(checkStop()) {
            stateManager.update();
            Window.update();
        }
    }

    public boolean checkStop() {
        return !Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
    }

    public void close() {
        loader.close();
        renderer.close();
        guiRenderer.close();
        Window.close();
    }

}