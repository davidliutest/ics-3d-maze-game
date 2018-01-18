package game.entities.mob;

import engine.entities.Entity;
import game.entities.staticentities.Floor;

import java.util.List;

// Collision detection using AABB vs AABB in 3D
// Uses bounding boxes for models determined by the models bounds float arr
public class Collision {

    // Detects collision of mob with all valid entities
    protected void collideAll(Mob cur, List<Entity> entityList, int dir) {
        for(Entity e : entityList) {
            if(cur != e && !(e instanceof Floor)) {
                collideOne(cur, e, dir);
            }
        }
    }

    // Requires direction the mob is travelling in
    // Direction:
    // 0 -> -z dir
    // 1 -> +x dir
    // 2 -> +z dir
    // 3 -> -x dir

    protected void collideOne(Mob m1, Entity m2, int dir) {
        // Calibrate bounding box
        float[] a = new float[6];
        a[0] = m1.getPos().x + m1.getDX() - m1.getLenX()/2;
        a[1] = m1.getPos().y + m1.getDY() - m1.getLenY()/2;
        a[2] = m1.getPos().z + m1.getDZ() - m1.getLenZ()/2;
        a[3] = m1.getPos().x + m1.getDX() + m1.getLenX()/2;
        a[4] = m1.getPos().y + m1.getDY() + m1.getLenY()/2;
        a[5] = m1.getPos().z + m1.getDZ() + m1.getLenZ()/2;
        float[] b = new float[6];
        b[0] = m2.getPos().x - m2.getLenX()/2;
        b[1] = m2.getPos().y - m2.getLenY()/2;
        b[2] = m2.getPos().z - m2.getLenZ()/2;
        b[3] = m2.getPos().x + m2.getLenX()/2;
        b[4] = m2.getPos().y + m2.getLenY()/2;
        b[5] = m2.getPos().z + m2.getLenZ()/2;
        // Collision detection
        boolean collide =
                overlap(a, b) || overlap(b, a);
                ;
        // Post-Collision adjustments
        if(collide) {
            if(dir == 3) {
                m1.setDX(m1.getDX() + (b[3] - a[0]) + 0.01f);
            } else if(dir == 1) {
                m1.setDX(m1.getDX() - (a[3] - b[0]) - 0.01f);
            } else if(dir == 0) {
                m1.setDZ(m1.getDZ() + (b[5] - a[2]) + 0.01f);
            } else if(dir == 2) {
                m1.setDZ(m1.getDZ() - (a[5] - b[2]) - 0.01f);
            }
        }
    }

    // Checks if line segments overlap for 2 model bounds
    public boolean overlap(float[] a, float[] b) {
        return ( (b[0] <= a[0] && a[0] <= b[3]) || (b[0] <= a[3] && a[3] <= b[3]) ) &&
                ( (b[1] <= a[1] && a[1] <= b[4]) || (b[1] <= a[4] && a[4] <= b[4]) ) &&
                ( (b[2] <= a[2] && a[2] <= b[5]) || (b[2] <= a[5] && a[5] <= b[5]) );
    }

}
