// https://github.com/davidliutest/ics-maze-game
import org.lwjgl.util.vector.Vector3f;

// Stores info of the objective of the maze (the shutdown controller entity)
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
