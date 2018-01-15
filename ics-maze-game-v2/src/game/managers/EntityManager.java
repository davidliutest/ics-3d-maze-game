package game.managers;

import engine.entities.Entity;
import engine.gui.GuiRend;
import game.datastruct.RC;
import game.entities.mob.Collision;
import game.entities.mob.Player;
import game.entities.mob.ai.Pathfinder;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private Handler handler;
    private List<Entity> entityList;
    private Player player;
    private Collision collision;
    private Pathfinder pathfinder;
    private GuiRend guiRend;

    public EntityManager(Handler handler) {
        this.handler = handler;
        entityList = new ArrayList<Entity>();
        collision = new Collision();
        guiRend = new GuiRend(handler.getLoader());
    }

    public void create() {
        RC start = handler.getMap().getStart();
        float len = handler.getMap().getWallLen();
        player = new Player(
                handler, new Vector3f(start.c * len + len/2,0, start.r * len + len/2),
                0,0,0,1f, new RC(handler.getMap().getStart())
        );

        entityList.add(player);
        for(Entity e : entityList) {
            e.setPosY(e.getLenY()/2);
        }
        pathfinder = new Pathfinder(handler.getMap());
    }

    public void update() {
        // Renders entities
        handler.getRenderer().update(entityList, handler.getEntityManager().getPlayer().getPos());
        // Updates entities
        for(Entity e : entityList) {
            e.update();
        }
        guiRend.render(handler.getModelManager().getGui());
    }

    public void addEntities(List<Entity> toAdd) {
        for(Entity e : toAdd) {
            entityList.add(e);
        }
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public Player getPlayer() {
        return player;
    }

    public Collision getCollision() {
        return collision;
    }

    public Pathfinder getPathfinder() {
        return pathfinder;
    }

}
