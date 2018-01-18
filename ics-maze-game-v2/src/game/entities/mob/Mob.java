package game.entities.mob;

import engine.entities.Entity;
import engine.render.models.TextureModel;
import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

// Base class for entities who exhibits movement (Mobs)
public abstract class Mob extends Entity {

    protected Handler handler;
    protected RC mapPos;
    // Movement values to change position of entity
    protected float dx, dy, dz;
    private float health = 100f;

    public Mob(
            Handler handler, TextureModel model, Vector3f position,
            float rotx, float roty, float rotz, float scale, RC mapPos
    ) {
        super(model, position, rotx, roty, rotz, scale);
        this.handler = handler;
        this.mapPos = mapPos;
    }

    public void update() {
        act();
    }

    // Unique for each mob
    public abstract void act();

    // Automatic collision detection
    public void move(int dir) {
        handler.getEntityManager().getCollision().collideAll(
                this, handler.getEntityManager().getEntityList(), dir
        );
        changePos(dx, dy, dz);
        dx = 0;
        dy = 0;
        dz = 0;
        mapPos.r = (int)(pos.getZ() / handler.getMap().getWallLen());
        mapPos.c = (int)(pos.getX() / handler.getMap().getWallLen());
    }

    // Move mob to specified xz pos
    protected void goToPos(float xpos, float zpos) {
        float pdx = pos.x - xpos;
        float pdz = pos.z - zpos;
        if(Math.abs(pdx) >= 0.15f) {
            if (pdx > 0) {
                dx -= 0.15f;
                move(3);
            } else {
                dx += 0.15f;
                move(1);
            }
        }
        if(Math.abs(pdz) >= 0.15f) {
            if (pdz > 0) {
                dz -= 0.15f;
                move(0);
            } else {
                dz += 0.15f;
                move(2);
            }
        }
    }

    // Getters and setters
    public void setMapPos(RC mapPos) {
        this.mapPos = mapPos;
    }

    public RC getMapPos() {
        return mapPos;
    }

    public float getDX() {
        return dx;
    }

    public float getDY() {
        return dy;
    }

    public float getDZ() {
        return dz;
    }

    public void setDX(float dx) {
         this.dx = dx;
    }

    public void setDY(float dy) {
        this.dy = dy;
    }

    public void setDZ(float dz) {
        this.dz = dz;
    }

    public void changeHealth(float dh) {
        health = Math.max(0, health + dh);
    }

    public float getHealth() {
        return health;
    }

    public void setHealth() { health = 100; }

}
