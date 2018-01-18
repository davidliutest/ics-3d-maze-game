package game.entities.mob;

import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Mob {

    public Player(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(
                handler, handler.getAssetManager().player,
                position, rotx, roty, rotz, scale, mapPos
        );
    }

    public void act() {
        float yaw = handler.getRenderer().getCam().getYaw();
        setRotY(-yaw);
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
        /*if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            dz -= Math.sin(Math.toRadians(90 - yaw)) / 3;
            move(dz < 0 ? 0 : 2);
            dx += Math.cos(Math.toRadians(90 - yaw)) / 3;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            dz += Math.sin(Math.toRadians(90 - yaw)) / 3;
            move(dz < 0 ? 0 : 2);
            dx -= Math.cos(Math.toRadians(90 - yaw)) / 3;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            dz -= Math.sin(Math.toRadians(180 - yaw)) / 3;
            move(dz < 0 ? 0 : 2);
            dx += Math.cos(Math.toRadians(180 - yaw)) / 3;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            dz += Math.sin(Math.toRadians(180 - yaw)) / 3;
            move(dz < 0 ? 0 : 2);
            dx -= Math.cos(Math.toRadians(180 - yaw)) / 3;
            move(dx > 0 ? 1 : 3);
        }*/
        /*
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            dz -= 0.2f;
            move(0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            dz += 0.2f;
            move(2);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            dx -= 0.2f;
            move(3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            dx += 0.2f;
            move(1);
        }
        */
    }

}
