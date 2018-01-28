// https://github.com/davidliutest/ics-maze-game
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

// Responsible for rendering the user's perspective of the 3D environment
// Stores information for the view matrix used to calculate how entities are rendered
public class Camera {

    private Vector3f pos;

    // Rotations of the camera in degrees (0 - 360 is a circle) along x, y, z axis
    private float pitch = 0;
    private float yaw = 0;
    private float roll = 0;

    // Initializes camera position to (0, 0, 0)
    public void create() {
        pos = new Vector3f(0,30,0);
    }

    public void update() {
        // Mouse movement
        pitch -= Mouse.getDY() * 0.3f;
        yaw += Mouse.getDX() * 0.3f;
        // Keyboard movement
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            pos.y += 1;
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            pos.z -= Math.sin(Math.toRadians(90 - yaw));
            pos.x += Math.cos(Math.toRadians(90 - yaw));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            pos.z += Math.sin(Math.toRadians(90 - yaw));
            pos.x -= Math.cos(Math.toRadians(90 - yaw));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            pos.z += Math.sin(Math.toRadians(180 - yaw));
            pos.x -= Math.cos(Math.toRadians(180 - yaw));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            pos.z -= Math.sin(Math.toRadians(180 - yaw));
            pos.x += Math.cos(Math.toRadians(180 - yaw));
        }
    }

    // Getters and setters

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public float getPitch() {
        return pitch;
    }

    public void changePitch(float dp) {
        this.pitch += dp;
    }

    public float getYaw() {
        return yaw;
    }

    public void changeYaw(float dy) {
        this.yaw += dy;
    }

    public float getRoll() {
        return roll;
    }

    public void changeRoll(float dr) {
        this.roll += dr;
    }

}
