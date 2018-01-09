package engine.utils;

import engine.datastruct.ModelData;
import engine.render.Loader;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
INSTRUCTIONS FOR BLENDER:
Load BLENDER OBJ WAVEFRONT FILES
    USE UV EDITING TO TEXTURE MODELS
    DO NOT USE TRIANGULAR FACES, DO USE RECTANGULAR FACES
    INCLUDE NORMALS
    # of "v" lines should equal total number of vertices of the model
    # of "vt" lines vary
    # of "vn" lines should equal total number of vertices of the model
    # of "f" lines should be total number of faces of the model, with 3*4=12 values per line
TEXTURES
    Make sure images have dimension that are POWERS OF 2
 */

public class OBJConverter {

    private static final long MAX_LINES = 1000000;

    public static ModelData convertOBJ(Loader loader, String fileName) throws Exception {
        BufferedReader br = null;
        String ln = "";
        long cnt = 0;
        List<Vector3f> vertList = new ArrayList<Vector3f>();
        List<Vector2f> texList = new ArrayList<Vector2f>();
        List<Vector3f> normList = new ArrayList<Vector3f>();
        float[] vertices = null;
        float[] normals = null;
        float[] textures = null;
        int[] indices = null;
        float[] bounds = null;
        boolean face3 = true;
        try {
            String file = "/obj/" + fileName + ".obj";
            br = new BufferedReader(new InputStreamReader(OBJConverter.class.getResourceAsStream(file)));
            while (cnt < MAX_LINES) {
                ln = br.readLine();
                if (ln != null) {
                    String[] tok = ln.split("\\s+");
                    if (tok[0].equals("v")) {
                        Vector3f ver = new Vector3f(
                                Float.parseFloat(tok[1]), Float.parseFloat(tok[2]), Float.parseFloat(tok[3])
                        );
                        vertList.add(ver);
                    } else if (tok[0].equals("vt")) {
                        Vector2f tex = new Vector2f(Float.parseFloat(tok[1]), Float.parseFloat(tok[2]));
                        texList.add(tex);
                    } else if (tok[0].equals("vn")) {
                        Vector3f norm = new Vector3f(
                                Float.parseFloat(tok[1]), Float.parseFloat(tok[2]), Float.parseFloat(tok[3])
                        );
                        normList.add(norm);
                    } else if (tok[0].equals("f")) {
                        if (tok.length - 1 == 4)
                            face3 = false;
                        break;
                    }
                }
                cnt++;
            }
            //System.out.println(vertList);
            //System.out.println(texList);
            //System.out.println(normList);
            cnt = 0;
            List<String> faces = new ArrayList<String>();
            while (ln != null && cnt < MAX_LINES) {
                if (ln.startsWith("f "))
                    faces.add(ln);
                ln = br.readLine();
                cnt++;
            }
            if (face3) {
                vertices = new float[faces.size() * 3 * 3];
                textures = new float[faces.size() * 3 * 2];
                normals = new float[faces.size() * 3 * 3];
                indices = new int[faces.size() * 3];
                for (int i = 0; i < faces.size(); i++) {
                    String[] cur = faces.get(i).split("\\s+");
                    for (int j = 0; j < 3; j++) {
                        String[] v = cur[j + 1].split("/");
                        Vector3f vertex = vertList.get(Integer.parseInt(v[0]) - 1);
                        vertices[i * 9 + j * 3] = vertex.x;
                        vertices[i * 9 + j * 3 + 1] = vertex.y;
                        vertices[i * 9 + j * 3 + 2] = vertex.z;
                        Vector2f tex = texList.get(Integer.parseInt(v[1]) - 1);
                        textures[i * 6 + j * 2] = tex.x;
                        textures[i * 6 + j * 2 + 1] = tex.y;
                        Vector3f norm = vertList.get(Integer.parseInt(v[2]) - 1);
                        normals[i * 9 + j * 3] = norm.x;
                        normals[i * 9 + j * 3 + 1] = norm.y;
                        normals[i * 9 + j * 3 + 2] = norm.z;
                    }
                    indices[i * 3] = i * 3;
                    indices[i * 3 + 1] = i * 3 + 1;
                    indices[i * 3 + 2] = i * 3 + 2;
                }
                System.out.println(indices);
            } else {
                vertices = new float[faces.size() * 4 * 3];
                textures = new float[faces.size() * 4 * 2];
                normals = new float[faces.size() * 4 * 3];
                indices = new int[faces.size() * 2 * 3];
                for (int i = 0; i < faces.size(); i++) {
                    String[] cur = faces.get(i).split("\\s+");
                    for (int j = 0; j < 4; j++) {
                        String[] v = cur[j + 1].split("/");
                        Vector3f vertex = vertList.get(Integer.parseInt(v[0]) - 1);
                        vertices[i * 12 + j * 3] = vertex.x;
                        vertices[i * 12 + j * 3 + 1] = vertex.y;
                        vertices[i * 12 + j * 3 + 2] = vertex.z;
                        Vector2f tex = texList.get(Integer.parseInt(v[1]) - 1);
                        textures[i * 8 + j * 2] = tex.x;
                        textures[i * 8 + j * 2 + 1] = tex.y;
                        Vector3f norm = vertList.get(Integer.parseInt(v[2]) - 1);
                        normals[i * 12 + j * 3] = norm.x;
                        normals[i * 12 + j * 3 + 1] = norm.y;
                        normals[i * 12 + j * 3 + 2] = norm.z;
                    }
                    indices[i * 6] = i * 4;
                    indices[i * 6 + 1] = i * 4 + 1;
                    indices[i * 6 + 2] = i * 4 + 3;
                    indices[i * 6 + 3] = i * 4 + 3;
                    indices[i * 6 + 4] = i * 4 + 1;
                    indices[i * 6 + 5] = i * 4 + 2;
                }
            }
            // Determines min and max x, y, z coordinates for collision from vertices list
            // float[] = {minx, miny, minz, maxx, maxy, maxz}
            Vector3f cur = vertList.get(0);
            bounds = new float[6];
            bounds[0] = cur.x;
            bounds[1] = cur.y;
            bounds[2] = cur.z;
            bounds[3] = cur.x;
            bounds[4] = cur.y;
            bounds[5] = cur.z;
            for (int i = 1; i < vertList.size(); i++) {
                cur = vertList.get(i);
                bounds[0] = Math.min(bounds[0], cur.x);
                bounds[1] = Math.min(bounds[1], cur.y);
                bounds[2] = Math.min(bounds[2], cur.z);
                bounds[3] = Math.max(bounds[3], cur.x);
                bounds[4] = Math.max(bounds[4], cur.y);
                bounds[5] = Math.max(bounds[5], cur.z);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        //System.out.println(Arrays.toString(modelBounds));
        return new ModelData(vertices, textures, normals, indices, bounds);
    }

}