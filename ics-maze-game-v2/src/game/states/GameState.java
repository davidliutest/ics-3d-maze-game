package game.states;

import editor.MapData1;
import engine.render.game.Camera;
import engine.render.gui.GuiObj;
import game.main.Handler;
import game.managers.EntityManager;
import game.managers.GameLogic;
import game.managers.GuiManager;
import game.map.Map;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class GameState extends State {

    private Map map;
    private EntityManager entityManager;
    private GuiObj hpBar;
    private GuiManager guiManager;
    private GameLogic gameLogic;


    public GameState(Handler handler) {
        super(handler);
        map = new Map(handler, 20, 20);
        entityManager = new EntityManager(handler);
        guiManager = new GuiManager(handler);
        gameLogic = new GameLogic(handler);
    }

    public void create() {
        map.create();
        entityManager.create();
        guiManager.addGuiObj(handler.getAssetManager().hpbarEmpty);
        guiManager.addGuiObj(hpBar = handler.getAssetManager().hpbar);
    }
    public void create(MapData1 mapData1){
        map.create(map.convert(mapData1,map.makeEntityList(mapData1)));
        entityManager.create(mapData1);
        guiManager.addGuiObj(handler.getAssetManager().hpbarEmpty);
        guiManager.addGuiObj(hpBar = handler.getAssetManager().hpbar);
    }
    public void update() {
        entityManager.update();

        guiManager.update();
        hpBar.setScale(Math.max(0, entityManager.getPlayer().getHealth()/100*0.9f), hpBar.getScale().y);

        Camera cam = handler.getRenderer().getCam();
        cam.update(entityManager.getPlayer().getPos());
        gameLogic.update();
    }

    public void reset() {
        entityManager.getEntityList().clear();
        map.create();
        entityManager.create();
    }

    public Map getMap() {
        return map;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
