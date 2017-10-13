package shader;

public class StaticShader extends Shader {

	private static final String VERTEX_FILE = "/vertexShader.txt";
	private static final String FRAGMENT_FILE = "/fragmentShader.txt";
	
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttribs() {
		super.bindAttrib(0, "position");
	}
	
}
