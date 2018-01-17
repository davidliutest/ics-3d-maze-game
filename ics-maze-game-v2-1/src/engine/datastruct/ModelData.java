package engine.datastruct;

import org.lwjgl.util.vector.Vector3f;

public class ModelData {

    public float[] vertices, normals, textures, bounds;
    public int[] indices;
    public Vector3f len;

    public ModelData(float[] v, float[] t, float[] n, int[] i, float[] b) {
        vertices = v;
        textures = t;
        normals = n;
        indices = i;
        bounds = b;
        len = new Vector3f(bounds[3]-bounds[0], bounds[4]-bounds[1], bounds[5]-bounds[3]);
    }

}
