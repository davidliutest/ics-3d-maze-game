package game.managers;

import engine.entities.Entity;
import game.datastruct.RC;
import game.entities.mob.Collision;
import game.entities.mob.Player;
import game.entities.ai.Pathfinder;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayList;
import java.util.List;

// Manages the entities of the game
// Includes important tools used for entity interactions
public class EntityManager {

    private Handler handler;
    private List<Entity> entityList;
    private Player player;
    private Collision collision;
    private Pathfinder pathfinder;

    public EntityManager(Handler handler) {
        this.handler = handler;
        entityList = new ArrayList<Entity>();
        collision = new Collision();
    }

    public void create() {
        RC start = handler.getMap().getStart();
        float len = handler.getMap().getWallLen();
        // Creates player
        player = new Player(
                handler, new Vector3f(start.c * len + len/2,0, start.r * len + len/2),
                0,0,0,0.5f, new RC(handler.getMap().getStart())
        );
        player.setVisible(false);
        entityList.add(player);
        // Places all entities on top of floor
        for(Entity e : entityList) {
            e.setPosY(e.getLenY()/2);
        }
        pathfinder = new Pathfinder(handler.getMap());
    }

    public void update() {
        // Renders entities
        handler.getRenderer().update(entityList);
        // Updates entities
        for(Entity e : entityList) {
            e.update();
        }
    }

    public void addEntities(List<Entity> toAdd) {
        for(Entity e : toAdd) {
            entityList.add(e);
        }
    }

    // Getters and setters

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
