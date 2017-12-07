package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

// Camera class that calculates the position of the camera
public class Camera {

    private Vector3f position = new Vector3f(100,200,200);

    private float pitch = 50;
    private float yaw=0;
    private float roll=0;

    public Camera(){}

    // Change position of the Camera based on key presses
    public void move(){
        if(!Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
                position.z -= 10;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
                position.x += 10;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
                position.x -= 10;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
                position.z += 10;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
                position.y += 10;
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_X))
                position.y -= 10;
            if (Keyboard.isKeyDown(Keyboard.KEY_Q))
                pitch +=1;
            if (Keyboard.isKeyDown(Keyboard.KEY_E))
                pitch -=1;
            if (Keyboard.isKeyDown(Keyboard.KEY_1))
                yaw +=1;
            if (Keyboard.isKeyDown(Keyboard.KEY_2))
                yaw -=1;
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
