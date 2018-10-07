package game.entities.staticentities;

import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

// Stores info of the floor of the map
public class Floor extends StaticEntity {

    public Floor(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale) {
        super(
                handler, handler.getAssetManager().beigeSquare,
                position, rotx, roty, rotz, scale
        );
    }

}
