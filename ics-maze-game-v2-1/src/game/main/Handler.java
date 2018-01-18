package game.main;

import engine.render.gui.GuiRenderer;
import engine.render.Loader;
import engine.render.game.Renderer;
import game.managers.AssetManager;
import game.managers.EntityManager;
import game.managers.StateManager;
import game.map.Map;
import game.states.GameState;

// Contains references of instances of important classes
// Used for classes to access important external variables from other classes
public class Handler {

    private Loader loader;
    private Renderer renderer;
    private GuiRenderer guiRenderer;
    private AssetManager assetManager;
    private StateManager stateManager;

    public Loader getLoader() {
        return loader;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        this.renderer = renderer;
    }

    public GuiRenderer getGuiRenderer() {
        return guiRenderer;
    }

    public void setGuiRenderer(GuiRenderer guiRenderer) {
        this.guiRenderer = guiRenderer;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    public StateManager getStateManager() {
        return stateManager;
    }

    public void setStateManager(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public Map getMap() {
        return ((GameState)stateManager.getGameState()).getMap();
    }

    public EntityManager getEntityManager() {
        return ((GameState)stateManager.getGameState()).getEntityManager();
    }

}
