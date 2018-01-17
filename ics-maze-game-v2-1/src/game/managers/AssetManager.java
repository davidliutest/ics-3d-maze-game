package game.managers;

import engine.datastruct.ModelData;
import engine.render.gui.GuiObj;
import engine.render.models.ModelTexture;
import engine.render.models.RawModel;
import engine.render.models.TextureModel;
import engine.utils.OBJConverter;
import game.main.Handler;
import org.lwjgl.util.vector.Vector2f;

public class AssetManager {

    private Handler handler;

    // Textures
    public ModelTexture grey, green, red, beige, skin;

    // RawModels
    public RawModel cube, square, dragon;

    // TextureModels
    public TextureModel greyCube, greenCube, redCube, beigeSquare, redDragon;

    // Gui
    public GuiObj test;

    public AssetManager(Handler handler) {
        this.handler = handler;
    }

    public void create() {
        // Textures
        grey = loadTexture("grey");
        green = loadTexture("green");
        red = loadTexture("red");
        beige = loadTexture("beige");
        skin = loadTexture("skin_soldier");

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

        test = new GuiObj(
                handler.getLoader().loadTexture("red"),
                new Vector2f(0f, 0f), new Vector2f(0.25f, 0.25f)
        );
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
