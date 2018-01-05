package game.main;

import engine.render.Loader;
import engine.render.Renderer;
import game.managers.EntityManager;
import game.managers.ModelManager;
import game.map.Map;

public class Handler {

    private Loader loader;
    private Renderer renderer;
    private ModelManager modelManager;
    private EntityManager entityManager;
    private Map map;

    public void create(Loader l, Renderer r, ModelManager m, Map ma, EntityManager e) {
        loader = l;
        renderer = r;
        modelManager = m;
        map = ma;
        entityManager = e;
    }

    public Loader getLoader() {
        return loader;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public ModelManager getModelManager() {
        return modelManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public Map getMap() {
        return map;
    }

}
