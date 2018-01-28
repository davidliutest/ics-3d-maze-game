// https://github.com/davidliutest/ics-maze-game 

import org.lwjgl.util.vector.Vector3f;

// Base class for entities that do not move
public class StaticEntity extends Entity {

    protected Handler handler;

    public StaticEntity(
            Handler handler, TextureModel model, Vector3f position,
            float rotx, float roty, float rotz, float scale
    ) {
        super(model, position, rotx, roty, rotz, scale);
        this.handler = handler;
    }
}
