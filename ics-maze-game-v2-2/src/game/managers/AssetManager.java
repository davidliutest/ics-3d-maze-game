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
    public ModelTexture grey, green, red, beige, skin, robot, cover, shutdown, win, lose, help;

    // RawModels
    public RawModel cube, square, character, charNoHead;

    // TextureModels
    public TextureModel greyCube, greenCube, redCube, beigeSquare, player, enemy, shutDownCube;

    // Gui
    public GuiObj coverGUI, winGUI, loseGUI, helpGUI, hpbar, hpbarEmpty;

    public AssetManager(Handler handler) {
        this.handler = handler;
    }

    public void create() {
        // Textures
        grey = loadTexture("grey");
        green = loadTexture("green");
        red = loadTexture("red");
        beige = loadTexture("beige");
        skin = loadTexture("person");
        robot = loadTexture("robot");
        cover = loadTexture("cover");
        shutdown = loadTexture("shutdown");
        win = loadTexture("win");
        lose = loadTexture("lose");
        help = loadTexture("instructions");

        // RawModels
        cube = loadRawModel("cube");
        square = loadRawModel("square");
        character = loadRawModel("character");
        charNoHead = loadRawModel("character2");

        // TextureModels
        beigeSquare = new TextureModel(square, beige);
        greyCube = new TextureModel(cube, grey);
        greenCube = new TextureModel(cube, green);
        redCube = new TextureModel(cube, red);
        player = new TextureModel(charNoHead, skin);
        enemy = new TextureModel(character, robot);
        shutDownCube = new TextureModel(cube, shutdown);
        coverGUI = new GuiObj(cover.getID(), new Vector2f(0f, 0f), new Vector2f(1f, 1f));
        winGUI = new GuiObj(win.getID(), new Vector2f(0f, 0f), new Vector2f(1f, 1f));
        loseGUI = new GuiObj(lose.getID(), new Vector2f(0f, 0f), new Vector2f(1f, 1f));
        helpGUI = new GuiObj(help.getID(), new Vector2f(0f, 0f), new Vector2f(1f, 1f));
        hpbar = new GuiObj(red.getID(), new Vector2f(0f, 0.9f), new Vector2f(0.9f, 0.05f));
        hpbarEmpty = new GuiObj(grey.getID(), new Vector2f(0f, 0.9f), new Vector2f(0.9f, 0.05f));
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
