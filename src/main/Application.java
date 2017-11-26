package main;

import editor.MapData;
import entities.Camera;
import entities.Entity;
import map.MapGen;
import org.lwjgl.opengl.Display;
import render.Displayer;
import render.Loader;
import render.Renderer;
import shader.StaticShader;

import java.util.List;

// Class that tests the code
public class Application {

	public static void start(MapData data) throws Exception {
		// Initialize all objects for rendering
		Displayer.create();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		/*
		// Actual model
		Model staticModel = OBJLoader.loadObjectModel("stall", loader);
		Texture texture = new Texture(loader.loadTexture("stallTexture"));
		TextureModel textureModel = new TextureModel(staticModel, texture);
		Entity entity = new Entity(textureModel, new Vector3f(5,-5,-25),0,0,0,1);

		Model mob1 = OBJLoader.loadObjectModel("dragon", loader);
		Texture mobTexture = new Texture(loader.loadTexture("white"));
		TextureModel mobTexModel = new TextureModel (mob1, mobTexture);
		// Creates a temp mob
		Mob dragon = new Mob (mobTexModel, new Vector3f(-8,-5,-25),0,0,0,1,100,100);
		*/
		MapGen mapGen = new MapGen();
		List<Entity> entityList;
		if(data == null)
			entityList = mapGen.gen(25, 25, loader);
		else
			entityList = mapGen.load(data, loader);

		//Light light = new Light(new Vector3f(0,0,-20),new Vector3f(1,1,1));
		// Creates the camera
		Camera camera = new Camera();

		// Main game loop
		while(!Display.isCloseRequested()) {
			//entity.increaseRotation(0,1,0);
			//dragon.increaseRotation(0,1,0);
			camera.move();
			renderer.start();
			shader.start();
			//shader.loadLight(light);
			shader.loadViewMatrix(camera);
			for(Entity e : entityList)
				renderer.render(e, shader);
			//renderer.render(dragon, shader);
			shader.stop();
			Displayer.update();
		}
		// Cleans up program on exit
		shader.close();
		loader.close();
		Displayer.close();
	}

}
