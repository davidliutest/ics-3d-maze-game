package shader;

public class StaticShader extends Shader {

	private static final String VERTEX_FILE = "/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "/shaders/fragmentShader.txt";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttribs() {
		super.bindAttrib(0, "position");
		super.bindAttrib(1, "texCoords");
	}
	
}
