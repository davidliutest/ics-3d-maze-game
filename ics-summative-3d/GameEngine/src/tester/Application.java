package tester;

import models.TextureModel;
import org.lwjgl.opengl.Display;

import models.Model;
import render.Displayer;
import render.Loader;
import render.Renderer;
import shader.StaticShader;
import textures.Texture;

public class Application {

	public static void main(String[] args) {
		Displayer.create();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		// Vertices of shape should be counterclockwise
		float[] vertices = {
				-0.5f, 0.5f, 0,
				-0.5f, -0.5f, 0,
				0.5f, -0.5f, 0,
				0.5f, 0.5f, 0
		}; // rectangle coords (x, y, z)
		int[] indices = {
				0, 1, 3, 
				3, 1, 2
		}; // triangles
		float[] texCoords = { // origin starts at top left
				0,0, // v1 counterclockwise
				0,1, // v2
				1,1, // v3
				1,0 // v4
		};
		Model model = loader.loadToVAO(vertices, texCoords, indices);
		Texture texture = new Texture(loader.loadTexture("texture"));
		TextureModel textureModel = new TextureModel(model, texture);
		while(!Display.isCloseRequested()) {
			renderer.start();
			shader.start();

			renderer.render(textureModel);
			shader.stop();
			Displayer.update();
		}
		shader.close();
		loader.close();
		Displayer.close();
	}

}
