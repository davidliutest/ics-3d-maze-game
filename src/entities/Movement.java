package entities;

import org.lwjgl.input.Keyboard;

public class Movement {
    public Movement(){}
    // changes position of player with keys
    public void move(Entity e){
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            e.increasePosition(0,0,-0.5f);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            e.increasePosition(-0.5f,0,0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            e.increasePosition(0,0,0.5f);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            e.increasePosition(0.5f,0,0);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            e.increasePosition(0, 0.5f, 0);
        }
    }
}
