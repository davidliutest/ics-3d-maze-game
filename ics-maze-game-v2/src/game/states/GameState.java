package game.states;

import game.main.Handler;
import game.managers.EntityManager;
import game.managers.GuiManager;
import game.map.Map;

public class GameState extends State {

    private Map map;
    private EntityManager entityManager;
    private GuiManager guiManager;

    public GameState(Handler handler) {
        super(handler);
        map = new Map(handler, 20, 20);
        entityManager = new EntityManager(handler);
        guiManager = new GuiManager(handler);
    }

    public void create() {
        map.create();
        entityManager.create();
        guiManager.addGuiObj(handler.getAssetManager().test);
    }

    public void update() {
        entityManager.update();
        guiManager.update();
    }

    public Map getMap() {
        return map;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
