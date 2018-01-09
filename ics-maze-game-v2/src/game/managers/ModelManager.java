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
    public ModelTexture grey, green, red, beige;

    // RawModels
    public RawModel cube, square;

    // TextureModels
    public TextureModel greyCube, greenCube, redCube, beigeSquare;

    public ModelManager(Handler handler) {
        this.handler = handler;
    }

    public void create() {
        // Textures
        grey = loadTexture("grey");
        green = loadTexture("sinon");
        red = loadTexture("red");
        beige = loadTexture("beige");

        // RawModels
        cube = loadRawModel("cube");
        square = loadRawModel("square");

        // TextureModels
        beigeSquare = new TextureModel(square, beige);
        greyCube = new TextureModel(cube, grey);
        greenCube = new TextureModel(cube, green);
        redCube = new TextureModel(cube, red);
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
