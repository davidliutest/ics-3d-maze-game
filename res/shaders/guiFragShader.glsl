#version 140
in vec2 texCoords;
out vec4 outColor;
uniform sampler2D guiTex;
void main(void) {
	outColor = texture(guiTex, texCoords);
}