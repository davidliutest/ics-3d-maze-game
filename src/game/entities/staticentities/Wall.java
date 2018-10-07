package game.entities.staticentities;

import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

// Stores info of the walls of the maze
public class Wall extends StaticEntity {

    public Wall(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale) {
        super(
                handler, handler.getAssetManager().greyCube,
                position, rotx, roty, rotz, scale
        );
    }

}
