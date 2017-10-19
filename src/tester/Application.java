package tester;

import entities.Camera;
import entities.Entity;
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

import java.io.FileNotFoundException;

public class Application {

	public static void main(String[] args) throws FileNotFoundException {

		//this is a test

		Displayer.create();
		
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);

		Model staticModel = OBJLoader.loadObjectModel("dragon", loader);
		Texture texture = new Texture(loader.loadTexture("white"));

		TextureModel textureModel = new TextureModel(staticModel, texture);

		Entity entity = new Entity(textureModel, new Vector3f(0,-5,-50),0,0,0,1);

		Camera camera = new Camera();

		while(!Display.isCloseRequested()) {
			entity.increaseRotation(0,1,0);
			camera.move();
			renderer.start();
			shader.start();
			shader.loadViewMatrix(camera);
			renderer.render(entity, shader);
			shader.stop();
			Displayer.update();
		}
		shader.close();
		loader.close();
		Displayer.close();
	}

}
