package game.entities.mob;

import engine.render.Camera;
import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import java.security.Key;

public class Player extends Mob {

    private float yaw = 0;
    private int hp = 100;
    private float change =0;
    private float rot =0;

    public Player(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(
                handler, handler.getModelManager().redDragon,
                position, rotx, roty, rotz, .2f, mapPos
        );
    }

    public void act() {
        //float y = 0;
        yaw = Camera.yaw;
        changeRot(0,yaw,0);
        int x = 4;
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            x/=2;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            dz -= Math.sin(Math.toRadians(90 - yaw)) / x;
            move(dz < 0 ? 0 : 2);
            dx += Math.cos(Math.toRadians(90 - yaw)) / x;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            dz += Math.sin(Math.toRadians(90 - yaw)) / x;
            move(dz < 0 ? 0 : 2);
            dx -= Math.cos(Math.toRadians(90 - yaw)) / x;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            dz -= Math.sin(Math.toRadians(180 - yaw)) / x;
            move(dz < 0 ? 0 : 2);
            dx += Math.cos(Math.toRadians(180 - yaw)) / x;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            dz += Math.sin(Math.toRadians(180 - yaw)) / x;
            move(dz < 0 ? 0 : 2);
            dx -= Math.cos(Math.toRadians(180 - yaw)) / x;
            move(dx > 0 ? 1 : 3);
        }
    }
    public int getHp() {
        return hp;
    }
    public void damage(){ hp-=10; }

}