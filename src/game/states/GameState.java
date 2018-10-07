package game.states;

import engine.render.game.Camera;
import engine.render.gui.GuiObj;
import game.main.Handler;
import game.managers.EntityManager;
import game.managers.GameLogic;
import game.managers.GuiManager;
import game.map.Map;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

// State responsible for the gameplay
public class GameState extends State {

    private Map map;
    private EntityManager entityManager;
    private GuiObj hpBar;
    private GuiManager guiManager;
    private GameLogic gameLogic;

    // Cheat
    private boolean freeCam = false;

    public GameState(Handler handler) {
        super(handler);
        map = new Map(handler, 16, 16);
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

    public void update() {
        entityManager.update();
        guiManager.update();

        // Updates health bar
        hpBar.setScale(Math.max(0, entityManager.getPlayer().getHealth()/100*0.9f), hpBar.getScale().y);

        // Cheats
        if(Keyboard.next() && Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
            freeCam = !freeCam;
        if(Keyboard.isKeyDown(Keyboard.KEY_H))
            entityManager.getPlayer().changeHealth(100);

        // Updates camera position from mouse movement
        // Pans camera
        Camera cam = handler.getRenderer().getCam();
        if(freeCam) {
            cam.update();
        } else {
            Vector3f camPos = new Vector3f(entityManager.getPlayer().getPos());
            camPos.y += 4f;
            float dpitch = -(Mouse.getDY() * 0.3f);
            if(cam.getPitch()+dpitch <= 90 && cam.getPitch()+dpitch >= -50) {
                cam.changePitch(dpitch);
            }
            cam.changeYaw(Mouse.getDX() * 0.3f);
            cam.setPos(camPos);
        }
        gameLogic.update();
    }

    // Resets variables for user to play maze again
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
