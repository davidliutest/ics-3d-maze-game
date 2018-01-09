package game.managers;

import engine.datastruct.ModelData;
import engine.render.models.ModelTexture;
import engine.render.models.RawModel;
import engine.render.models.TextureModel;
import engine.utils.OBJConverter;
import game.main.Handler;

public class ModelManager {

    private Handler handler;

    // Textures
    public ModelTexture grey, green, red, beige, fire;

    // RawModels
    public RawModel cube, square, dragon;

    // TextureModels
    public TextureModel greyCube, greenCube, redCube, beigeSquare, redDragon;

    public ModelManager(Handler handler) {
        this.handler = handler;
    }

    public void create() {
        // Textures
        grey = loadTexture("grey");
        green = loadTexture("green");
        red = loadTexture("red");
        beige = loadTexture("beige");
        fire = loadTexture("fire");

        // RawModels
        cube = loadRawModel("cube");
        square = loadRawModel("square");
        dragon = loadRawModel("dragon");

        // TextureModels
        beigeSquare = new TextureModel(square, beige);
        greyCube = new TextureModel(cube, grey);
        greenCube = new TextureModel(cube, green);
        redCube = new TextureModel(cube, red);
        redDragon = new TextureModel(dragon, red);
    }

    public ModelTexture loadTexture(String file) {
        return new ModelTexture(handler.getLoader().loadTexture(file));
    }

    public RawModel loadRawModel(String file) {
        ModelData md = loadModelData(file);
        return handler.getLoader().loadVAO(md.vertices, md.textures, md.normals, md.indices, md.bounds);
    }

    public ModelData loadModelData(String file) {
        ModelData md = null;
        try {
            md = OBJConverter.convertOBJ(handler.getLoader(), file);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return md;
    }

}
