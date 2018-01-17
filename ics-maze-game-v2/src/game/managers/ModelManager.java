package game.managers;

import engine.datastruct.ModelData;
import engine.gui.GuiRend;
import engine.gui.GuiTex;
import engine.render.models.ModelTexture;
import engine.render.models.RawModel;
import engine.render.models.TextureModel;
import engine.utils.OBJConverter;
import game.main.Handler;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class ModelManager {

    private Handler handler;

    // Textures
    public ModelTexture grey, green, red, beige, fire;

    // RawModels
    public RawModel cube, square, dragon, guardian;

    // TextureModels
    public TextureModel greyCube, greenCube, redCube, beigeSquare, redDragon;
    // Gui
    private List<GuiTex> guis;

    public ModelManager(Handler handler) {
        this.handler = handler;
    }

    public void create() {
        // Textures
        grey = loadTexture("grey");
        green = loadTexture("green");
        red = loadTexture("red");
        beige = loadTexture("beige");
        fire = loadTexture("p");

        // RawModels
        cube = loadRawModel("cube");
        square = loadRawModel("square");
        guardian = loadRawModel("guardian");
        dragon = loadRawModel("dragon");

        // TextureModels
        beigeSquare = new TextureModel(square, beige);
        greyCube = new TextureModel(cube, grey);
        greenCube = new TextureModel(cube, green);
        redCube = new TextureModel(dragon, red);
        redDragon = new TextureModel(guardian, fire);

        // Gui
        guis = new ArrayList<GuiTex>();
        GuiTex gui = new GuiTex(handler.getLoader().loadTexture("stallTexture"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
        guis.add(gui);
    }

    public List<GuiTex> getGui(){
        return guis;
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
