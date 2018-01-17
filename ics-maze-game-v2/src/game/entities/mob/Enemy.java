package game.entities.mob;

import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayDeque;

public class Enemy extends Mob {

    public Enemy(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(handler, handler.getModelManager().redCube, position, rotx, roty, rotz, .5f, mapPos);
    }

    public void act() {
        Player player = handler.getEntityManager().getPlayer();
        float distToPlayer = this.dist(player);
        if(distToPlayer <= 2f) {
            // Attack
        } else if(mapPos.equals(player.getMapPos())) {
            // Close
            float toX = player.getPosX();
            float toZ = player.getPosZ();
            goToPos(toX, toZ);
        } else if(distToPlayer < handler.getMap().getWallLen() * 3) {
            // Seek
            ArrayDeque<RC> path = handler.getEntityManager().getPathfinder().path(
                    this, player
            );
            if(path != null && !path.isEmpty()) {
                RC to = path.getFirst();
                float len = handler.getMap().getWallLen();
                float ranX = to.c * handler.getMap().getWallLen() + len/2;
                float ranZ = to.r * handler.getMap().getWallLen() + len/2;
                goToPos(ranX, ranZ);
            }
        }
    }

}
