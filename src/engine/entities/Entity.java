package engine.entities;

import engine.render.models.TextureModel;
import org.lwjgl.util.vector.Vector3f;

// Base class for all entities of the game
// Contains information that defines an entity
public class Entity {

    protected TextureModel model;
    // Position of the entity is the center of the entity in the 3D coordinate system
    // Len is the maximum x, y, z length of the original model
    protected Vector3f pos, len;
    protected float rotx, roty, rotz, scale;
    protected boolean visible = true;
    protected boolean remove = false;

    public Entity(
            TextureModel model, Vector3f position, float rotx, float roty, float rotz, float scale
    ) {
        this.model = model;
        setPos(position);
        setRotX(rotx);
        setRotY(roty);
        setRotZ(rotz);
        len = new Vector3f();
        setScale(scale);
    }

    public void update() {}

    public void setVisible(boolean b) {
        this.visible = b;
    }

    public boolean getVisible() {
        return visible;
    }

    public void remove() {
        this.remove = true;
    }

    public void changePos(float dx, float dy, float dz){
        this.pos.x += dx;
        this.pos.y += dy;
        this.pos.z += dz;
    }

    public void changeRot(float dx, float dy, float dz){
        this.rotx += dx;
        this.roty += dy;
        this.rotz += dz;
    }

    // Returns the shortest straight line distance between cur entity and another
    public float dist(Entity e) {
        return (float)Math.sqrt(
                Math.pow(pos.x - e.getPosX(),2) +
                Math.pow(pos.y - e.getPosY(),2) +
                Math.pow(pos.z - e.getPosZ(),2)
        );
    }

    // Getters and setters

    public TextureModel getTextureModel() {
        return model;
    }

    public float getPosX() {
        return pos.x;
    }

    public float getPosY() {
        return pos.y;
    }

    public float getPosZ() {
        return pos.z;
    }

    public void setPosX(float x) {
        pos.x = x;
    }

    public void setPosY(float y) {
        pos.y = y;
    }

    public void setPosZ(float z) {
        pos.z = z;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public float getRotX() {
        return rotx;
    }

    public void setRotX(float rotx) {
        this.rotx = rotx;
    }

    public float getRotY() {
        return roty;
    }

    public void setRotY(float roty) {
        this.roty = roty;
    }

    public float getRotZ() {
        return rotz;
    }

    public void setRotZ(float rotz) {
        this.rotz = rotz;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
        len.x = (model.getBounds(3) - model.getBounds(0)) * scale;
        len.y = (model.getBounds(4) - model.getBounds(1)) * scale;
        len.z = (model.getBounds(5) - model.getBounds(2)) * scale;
    }

    public float getLenX() {
        return len.x;
    }

    public float getLenY() {
        return len.y;
    }

    public float getLenZ() {
        return len.z;
    }

}
