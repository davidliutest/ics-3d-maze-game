package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

// Camera class that calculates the position of the camera
public class Camera {

    private Vector3f position = new Vector3f(20,50,25);

    private float pitch = 50;
    private float yaw;
    private float roll;

    public Camera(){}

    // Change position of the Camera based on key presses
    public void move(){
        if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                position.z -= 1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                position.x += 1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                position.x -= 1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                position.z += 1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                position.y += 1;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_X))
                position.y -= 1;
        }
    }

    // Getters and setters
    public Vector3f getPosition() {
        return position;
    }
    public float getPitch() {
        return pitch;
    }
    public float getYaw() {
        return yaw;
    }
    public float getRoll() {
        return roll;
    }
}
