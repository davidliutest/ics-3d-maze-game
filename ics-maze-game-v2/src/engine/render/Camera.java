package engine.render;

import game.datastruct.RC;
import game.entities.mob.Collision;
import game.main.Handler;
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

    public static void update() {
        // Mouse movement
        pitch -= Mouse.getDY() * 0.03f;
        yaw += Mouse.getDX() * 0.03f;
        // Keyboard movement
        if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
            pos.z -= Math.sin(Math.toRadians(90-yaw))/4;
            pos.x += Math.cos(Math.toRadians(90-yaw))/4;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            pos.z += Math.sin(Math.toRadians(90-yaw))/4;
            pos.x -= Math.cos(Math.toRadians(90-yaw))/4;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            pos.z -= Math.sin(Math.toRadians(180-yaw))/4;
            pos.x += Math.cos(Math.toRadians(180-yaw))/4;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            pos.z += Math.sin(Math.toRadians(180-yaw))/4;
            pos.x -= Math.cos(Math.toRadians(180-yaw))/4;
        }
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
