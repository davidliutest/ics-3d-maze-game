package engine.render;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

    public static Vector3f pos;

    // In 360 degrees
    public static float pitch = 0;
    public static float yaw = 0;
    public static float roll = 0;

    public void create(Vector3f start) {
        pos = new Vector3f(start.x, start.y+5f, start.z);
    }

    public static void update(Vector3f r) {
        // Mouse movement
        pitch -= Mouse.getDY() * 0.03f;
        yaw += Mouse.getDX() * 0.03f;
        pos.x =r.x;
        pos.z =r.z;
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            pos.y += 1;
        if (Keyboard.isKeyDown(Keyboard.KEY_X))
            pos.y -= 1;
    }

    // Getters and setters
    public Vector3f getPos() {
        return pos;
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
