// https://github.com/davidliutest/ics-maze-game 

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

// Stores info of the player
public class Player extends Mob {

    public Player(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(
                handler, handler.getAssetManager().player,
                position, rotx, roty, rotz, scale, mapPos
        );
    }

    // Updates player position with keyboard presses (to move player) and camera yaw
    public void act() {
        float yaw = handler.getRenderer().getCam().getYaw();
        setRotY(-yaw);
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            dz -= Math.sin(Math.toRadians(90 - yaw)) / 3.5f;
            move(dz < 0 ? 0 : 2);
            dx += Math.cos(Math.toRadians(90 - yaw)) / 3.5f;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            dz += Math.sin(Math.toRadians(90 - yaw)) / 3.5f;
            move(dz < 0 ? 0 : 2);
            dx -= Math.cos(Math.toRadians(90 - yaw)) / 3.5f;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            dz -= Math.sin(Math.toRadians(180 - yaw)) / 3.5f;
            move(dz < 0 ? 0 : 2);
            dx += Math.cos(Math.toRadians(180 - yaw)) / 3.5f;
            move(dx > 0 ? 1 : 3);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            dz += Math.sin(Math.toRadians(180 - yaw)) / 3;
            move(dz < 0 ? 0 : 2);
            dx -= Math.cos(Math.toRadians(180 - yaw)) / 3;
            move(dx > 0 ? 1 : 3);
        }
    }

}
