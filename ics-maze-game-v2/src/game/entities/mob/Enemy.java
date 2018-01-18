package game.entities.mob;

import game.datastruct.RC;
import game.main.Handler;
import org.lwjgl.util.vector.Vector3f;

import java.util.ArrayDeque;

public class Enemy extends Mob {

    private Vector3f lastPos;

    public Enemy(Handler handler, Vector3f position, float rotx, float roty, float rotz, float scale, RC mapPos) {
        super(handler, handler.getAssetManager().enemy, position, rotx, roty, rotz, scale, mapPos);
        lastPos = new Vector3f(pos);
    }

    public void act() {
        Player player = handler.getEntityManager().getPlayer();
        float distToPlayer = this.dist(player);
        if(distToPlayer <= 5f) {
            // Attack
            player.changeHealth(-0.25f);
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
        Vector3f dir = new Vector3f();
        Vector3f.sub(pos, lastPos, dir);
        float rotY =
                (float)Math.toDegrees(
                        Math.PI/2 -
                        Math.atan2(dir.z, dir.x)
                );
        setRotY(rotY);
        // System.out.println(rotY);
        lastPos = new Vector3f(pos);
    }

}
