package game.entities.mob;

import engine.render.Camera;
import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Mob {

    private float yaw = 0;
    private int hp = 100;

    public Player(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(
                handler, handler.getModelManager().redDragon,
                position, rotx, roty, rotz, scale, mapPos
        );
    }

    public void act() {
        yaw = Camera.yaw;

        System.out.println(yaw);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            dz -= Math.sin(Math.toRadians(90-yaw))/4;
            move(dz<0?0:2);
            dx += Math.cos(Math.toRadians(90-yaw))/4;
            move(dx>0?1:3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            dz += Math.sin(Math.toRadians(90-yaw))/4;
            move(dz<0?0:2);
            dx -= Math.cos(Math.toRadians(90-yaw))/4;
            move(dx>0?1:3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            dz -= Math.sin(Math.toRadians(180-yaw))/4;
            move(dz<0?0:2);
            dx += Math.cos(Math.toRadians(180-yaw))/4;
            move(dx>0?1:3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            dz += Math.sin(Math.toRadians(180-yaw))/4;
            move(dz<0?0:2);
            dx -= Math.cos(Math.toRadians(180-yaw))/4;
            move(dx>0?1:3);
        }

    }
    public int getHp() {
        return hp;
    }
    public void damage(){

    }
}