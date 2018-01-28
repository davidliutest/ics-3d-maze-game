// https://github.com/davidliutest/ics-maze-game
// Main class of the application
// Contains the main game loop
// All components of game (excluding launcher) is contained here

// Common methods: default_contructor, create(), start(), update(), stop(), close(), run() (SPECIAL)
// default_contructor -> initializes variables
// create() -> sets variables to certain values
// start() -> starts function of class
// update() -> updates state of class
// stop() -> temporarily stop function of class
// close() -> closing operation when program exits

// Stucture
// common methods is first in specified order, then helper methods in order of usage

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;

public class App {

    private Handler handler;
    private StateManager stateManager;
    private Loader loader;
    private Renderer renderer;
    private GuiRenderer guiRenderer;
    private AssetManager modelManager;

    // Initializes variables using only constructors
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

    // Creates various data
    public void create() {
        Window.create();
        renderer.create();
        guiRenderer.create(loader);
        modelManager.create();
        stateManager.create();
    }

    // Main game loop
    public void run() {
        create();
        while(checkStop()) {
            stateManager.update();
            Window.update();
        }
        close();
    }

    // Checks conditions that closes the window
    public boolean checkStop() {
        return !Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE);
    }

    // Safely exits program and prevents mem leaks
    public void close() {
        loader.close();
        renderer.close();
        guiRenderer.close();
        Window.close();
    }

}
