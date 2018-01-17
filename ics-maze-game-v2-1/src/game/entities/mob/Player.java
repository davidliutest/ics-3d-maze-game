package game.entities.mob;

import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Player extends Mob {

    public Player(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(
                handler, handler.getAssetManager().redDragon,
                position, rotx, roty, rotz, scale, mapPos
        );
    }

    public void act() {
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
    }

}
