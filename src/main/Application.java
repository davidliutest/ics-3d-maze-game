package main;

import editor.MapData;
import entities.Camera;
import entities.Entity;
import entities.Movement;
import map.MapGen;
import models.Model;
import models.TextureModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import render.Displayer;
import render.Loader;
import render.OBJLoader;
import render.Renderer;
import shader.StaticShader;
import textures.Texture;

import java.util.List;

// Class that tests the code
public class Application {

	public static void start(MapData data) throws Exception {
		// Initialize all objects for rendering
		Displayer.create();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		// Actual model
		Model staticModel = OBJLoader.loadObjectModel("stall", loader);
		Texture texture = new Texture(loader.loadTexture("stallTexture"));
		TextureModel textureModel = new TextureModel(staticModel, texture);
		Entity entity = new Entity(textureModel, new Vector3f(0,0,0),0,0,0,.75f);

		MapGen mapGen = new MapGen();
		List<Entity> entityList;
		if(data == null)
			entityList = mapGen.gen(25, 25, loader);
		else
			entityList = mapGen.load(data, loader);

		//Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));
		// Creates the camera
		Camera camera = new Camera();
		Movement movement = new Movement();
		// Main game loop loads movements camera entities
		while(!Display.isCloseRequested()) {
			camera.move();
			movement.move(entity);
			renderer.start();
			shader.start();
			renderer.render(entity, shader);
			//shader.loadLight(light);
			shader.loadViewMatrix(camera);
			for(Entity e : entityList)
				renderer.render(e, shader);
			shader.stop();
			Displayer.update();
		}
		// Cleans up program on exit
		shader.close();
		loader.close();
		Displayer.close();
		Start.f.dispose();
	}

}
