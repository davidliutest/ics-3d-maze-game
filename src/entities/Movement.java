package entities;

import org.lwjgl.input.Keyboard;

public class Movement {
    public Movement(){}

    public void move(Entity e){
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                e.increasePosition(0,0,-0.1f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                e.increasePosition(-0.1f,0,0);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                e.increasePosition(0,0,0.1f);
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                e.increasePosition(0.1f,0,0);
            }
        }
    }
}
