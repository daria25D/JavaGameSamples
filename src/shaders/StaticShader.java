package shaders;

public class StaticShader extends ShaderProgram {
    private static final String VERTEX_FILE = "src/shaders/vertex.glsl";
    private static final String FRAGMENT_FILE = "src/shaders/fragment.glsl";
    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes(0, "position");
        super.bindAttributes(1, "uv");
    }
}
