package game.entities.staticentities;

import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

public class Shutdown extends StaticEntity {

    private RC mapPos;

    public Shutdown(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(
                handler, handler.getAssetManager().shutDownCube,
                position, rotx, roty, rotz, scale
        );
        this.mapPos = mapPos;
    }

}
